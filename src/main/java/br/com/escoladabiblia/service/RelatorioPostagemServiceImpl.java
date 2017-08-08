package br.com.escoladabiblia.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Postagem;
import br.com.escoladabiblia.model.TipoCaracterizacao;
import br.com.escoladabiblia.repository.AlunoRepository;
import br.com.escoladabiblia.repository.AtividadeEstudoRepository;
import br.com.escoladabiblia.repository.CertificadoEnviadoRepository;
import br.com.escoladabiblia.repository.PostagemRepository;
import br.com.escoladabiblia.util.impressao.CertificadosPostagemVO;
import br.com.escoladabiblia.util.impressao.JasperUtil;
import br.com.escoladabiblia.util.impressao.MateriaisPostagem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Transactional(readOnly = true)
public class RelatorioPostagemServiceImpl implements RelatorioPostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private AtividadeEstudoRepository atividadeEstudoRepository;

	@Autowired
	private CertificadoEnviadoRepository certificadoEnviadoRepository;

	@Override
	public byte[] obterRelatorioPostagem(Long idPostagem) throws JRException, IOException {

		final Postagem postagem = postagemRepository.findOne(idPostagem);

		final List<MateriaisPostagem> materiaisPostagem = atividadeEstudoRepository
				.obterRelatorioAtividadesPostagem(postagem.getId());

		this.addBibliasPostagem(postagem, materiaisPostagem);

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("dataPostagem", postagem.getDataPrevistaEnvio().getTime());

		this.addTotalAlunosNovosParametros(postagem, parameters);

		this.addTotalAlunosSequenciaisParametros(postagem, parameters);

		this.addSubReportParametros(postagem, parameters);

		JasperPrint jasperPrint = this.getJasperPrintRelatorioPostagem(materiaisPostagem, parameters);

		return JasperUtil.exportReport(jasperPrint);
	}

	/**
	 * 
	 * Caso existam bíblias inclusas na postagem, adiciona ao datasource de
	 * materiais enviados ({@code List<MateriaisPostagem>})
	 * 
	 * @param postagem
	 * @param materiaisPostagem
	 */
	private void addBibliasPostagem(final Postagem postagem, final List<MateriaisPostagem> materiaisPostagem) {

		if (postagem.getBibliasEnviadas().size() > 0) {

			materiaisPostagem.add(new MateriaisPostagem("BÍBLIA", Long.valueOf(postagem.getBibliasEnviadas().size())));
		}
	}

	/**
	 * 
	 * Adiciona os totais de alunos novos (presidiários e comuns) ao {@code Map}
	 * de parâmetros do relatório. Alunos novos são todos que estiverem
	 * recebendo a lição introdutória
	 * 
	 * @param postagem
	 * @param parameters
	 */
	private void addTotalAlunosNovosParametros(Postagem postagem, Map<String, Object> parameters) {

		List<Aluno> alunosNovos = alunoRepository.findAlunosNovosByPostagem(postagem.getId());

		parameters.put("presidiariosNov",
				obterTotalAlunosPorCaracterizacao(alunosNovos, TipoCaracterizacao.PRESIDIARIO));

		parameters.put("comunsNov", obterTotalAlunosPorCaracterizacao(alunosNovos, TipoCaracterizacao.NONE));
	}

	/**
	 *
	 * Adiciona os totais de alunos sequenciais (presidiários e comuns) ao
	 * {@code Map} de parâmetros do relatório. Alunos sequenciais são todos que
	 * estiverem recebendo a lições posteriores à introdutória
	 * 
	 * @param postagem
	 * @param parameters
	 */
	private void addTotalAlunosSequenciaisParametros(Postagem postagem, Map<String, Object> parameters) {

		List<Aluno> alunosSequenciais = alunoRepository.findAlunosSequenciaisByPostagem(postagem.getId());

		parameters.put("presidiariosSeq",
				obterTotalAlunosPorCaracterizacao(alunosSequenciais, TipoCaracterizacao.PRESIDIARIO));

		parameters.put("comunsSeq", obterTotalAlunosPorCaracterizacao(alunosSequenciais, TipoCaracterizacao.NONE));
	}

	private long obterTotalAlunosPorCaracterizacao(List<Aluno> alunos, TipoCaracterizacao caracterizacao) {

		return alunos.stream().filter(a -> a.getTipoCaracterizacao() == caracterizacao).count();
	}

	/**
	 * 
	 * Adiciona o path do subreport e seu datasource ao {@code Map} de
	 * parâmetros do relatório
	 * 
	 * @param postagem
	 * @param parameters
	 * @throws IOException
	 */
	private void addSubReportParametros(Postagem postagem, Map<String, Object> parameters) throws IOException {

		parameters.put("subReportPath", JasperUtil.getFilePath("jasper/sub-certificados-postagem.jasper"));

		parameters.put("certificados", this.obterCertificadosDaPostagem(postagem.getId()));
	}

	/**
	 * 
	 * Cria e retorna uma lista de {@code CertificadosPostagemVO} a ser
	 * utilizada como datasource para o subreport do relatório
	 * 
	 * @param idPostagem
	 * @return Lista de {@code CertificadosPostagemVO}
	 */
	private List<CertificadosPostagemVO> obterCertificadosDaPostagem(Long idPostagem) {

		final List<CertificadosPostagemVO> certificadosPostagem = certificadoEnviadoRepository
				.findCertificadosPostagem(idPostagem);

		for (CertificadosPostagemVO certificadoPostagem : certificadosPostagem) {

			certificadoPostagem.getAlunos().addAll(certificadoEnviadoRepository.findAlunosCertificados(idPostagem,
					certificadoPostagem.getCertificado()));
		}

		return certificadosPostagem;
	}

	/**
	 * 
	 * Cria e retorna uma instância de {@code JasperPrint} para a geração do
	 * relatório da postagem
	 * 
	 * @param materiaisPostagem
	 *            - datasource do relatório a ser gerado
	 * @param parameters
	 *            - parâmetros para relatório a ser gerado
	 * @return {@code JasperPrint} do relatório da postagem
	 * @throws JRException
	 */
	private JasperPrint getJasperPrintRelatorioPostagem(final List<MateriaisPostagem> materiaisPostagem,
			Map<String, Object> parameters) throws JRException {

		final InputStream jasper = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jasper/relatorio-postagem.jasper");

		return JasperFillManager.fillReport(jasper, parameters, new JRBeanCollectionDataSource(materiaisPostagem));
	}

}
