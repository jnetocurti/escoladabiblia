package br.com.escoladabiblia.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escoladabiblia.model.Postagem;
import br.com.escoladabiblia.model.TipoEnvelope;
import br.com.escoladabiblia.repository.AtividadeEstudoRepository;
import br.com.escoladabiblia.repository.CertificadoEnviadoRepository;
import br.com.escoladabiblia.repository.PostagemRepository;
import br.com.escoladabiblia.util.dto.AtividadeEstudoImpressaoDTO;
import br.com.escoladabiblia.util.dto.PeriodoDTO;
import br.com.escoladabiblia.util.exception.BusinessException;
import br.com.escoladabiblia.util.impressao.CertificadosPostagemVO;
import br.com.escoladabiblia.util.impressao.Destinatario;
import br.com.escoladabiblia.util.impressao.DestinatarioFactory;
import br.com.escoladabiblia.util.impressao.JasperUtil;
import br.com.escoladabiblia.util.impressao.MateriaisPostagem;

@Service
public class PostagemServiceImpl implements PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private AtividadeEstudoRepository atividadeEstudoRepository;
	
	@Autowired
	private CertificadoEnviadoRepository certificadoEnviadoRepository;
	
	@Override
	public void salvar(Postagem postagem) throws BusinessException {

		if (this.obterPostagemEmAberto() != null) {

			throw new BusinessException("erro.postagem.ja.existe.aberta");
		}

		postagemRepository.save(postagem);
	}

	@Override
	public Postagem obterPostagemEmAberto() {

		return postagemRepository.findLastOpen();
	}

	@Override
	public List<Postagem> listarPorPeriodo(PeriodoDTO periodo) {

		return postagemRepository.findByPeriod(periodo.getDataDe(), periodo.getDataAte());
	}

	@Override
	public byte[] processarPostagem(Long id, boolean encerrar) throws JRException {

		final List<JasperPrint> jasperPrints = new ArrayList<>();

		final List<AtividadeEstudoImpressaoDTO> atividadesEstudoPostagem = atividadeEstudoRepository
				.obterAtividadesEstudoAlunosParaImpressao(id);

		for (TipoEnvelope tipoEnvelope : TipoEnvelope.values()) {

			List<Destinatario> destinatarios = obterDestinatariosPorTipoEnvelope(atividadesEstudoPostagem,
					tipoEnvelope);

			if (!destinatarios.isEmpty()) {

				jasperPrints.add(JasperUtil.getJasperPrintEnvelopes(destinatarios, tipoEnvelope.jasperFile));
			}
		}

		if (encerrar) {
			this.finalizarPostagem(id);
		}

		return JasperUtil.exportReport(jasperPrints);
	}

	private List<Destinatario> obterDestinatariosPorTipoEnvelope(
			final List<AtividadeEstudoImpressaoDTO> atividadesEstudoPostagem, final TipoEnvelope tipoEnvelope) {

		final List<Destinatario> destinatarios = new ArrayList<>();

		atividadesEstudoPostagem.stream().filter(a -> a.getTipoEnvelope().equals(tipoEnvelope))
				.forEach(a -> destinatarios.add(DestinatarioFactory.getDestinatario(a.getAluno())));

		return destinatarios;
	}

	private void finalizarPostagem(Long id) {

		Postagem postagem = postagemRepository.findOne(id);

		postagem.setDataEfetivaEnvio(Calendar.getInstance());

		postagemRepository.save(postagem);
	}

	@Override
	public byte[] gerarRelatorio(Long id) throws JRException {

		final Postagem postagem = postagemRepository.findOne(id);

		final List<MateriaisPostagem> materiaisPostagem = 
				atividadeEstudoRepository.obterRelatorioAtividadesPostagem(id);
		
		obterBiliasPostagem(postagem, materiaisPostagem);

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("dataPostagem", postagem.getDataPrevistaEnvio().getTime());
		
		parameters.put("subReportPath", Thread.currentThread().getContextClassLoader()
				.getResource("jasper/sub-certificados-postagem.jasper").getPath());
		
		parameters.put("certificados", obterCertificadosDaPostagem(id));

		JasperPrint jasperPrint = JasperUtil.getJasperPrintRelatorioPostagem(materiaisPostagem, 
				parameters, "jasper/relatorio-postagem.jasper");

		return JasperUtil.exportReport(jasperPrint);
	}

	private void obterBiliasPostagem(final Postagem postagem, final List<MateriaisPostagem> materiaisPostagem) {

		if (postagem.getBibliasEnviadas().size() > 0) {

			materiaisPostagem.add(new MateriaisPostagem("BÍBLIA", Long
					.valueOf(postagem.getCertificadosEnviados().size())));
		}
	}
	
	private List<CertificadosPostagemVO> obterCertificadosDaPostagem(Long idPostagem) {
		
		final List<CertificadosPostagemVO> certificadosPostagem = certificadoEnviadoRepository
				.findCertificadosPostagem(idPostagem);
		
		for (CertificadosPostagemVO certificadoPostagem : certificadosPostagem) {
			
			certificadoPostagem.getAlunos().addAll(certificadoEnviadoRepository.
					findAlunosCertificados(idPostagem, certificadoPostagem.getCertificado()));
		}

		return certificadosPostagem;
	}

}
