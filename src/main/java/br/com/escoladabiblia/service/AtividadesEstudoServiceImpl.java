package br.com.escoladabiblia.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.AtividadeEstudo;
import br.com.escoladabiblia.model.BibliaEnviada;
import br.com.escoladabiblia.model.CertificadoEnviado;
import br.com.escoladabiblia.model.Postagem;
import br.com.escoladabiblia.repository.AlunoRepository;
import br.com.escoladabiblia.repository.AtividadeEstudoRepository;
import br.com.escoladabiblia.repository.CertificadoEnviadoRepository;
import br.com.escoladabiblia.repository.MaterialEstudoRepository;
import br.com.escoladabiblia.repository.PostagemRepository;
import br.com.escoladabiblia.util.dto.AtividadeEstudoEdicaoDTO;
import br.com.escoladabiblia.util.dto.AtividadeEstudoHistoricoDTO;
import br.com.escoladabiblia.util.dto.AtividadesEstudoEdicaoDTO;
import br.com.escoladabiblia.util.dto.AtividadesEstudoHistoricoDTO;
import br.com.escoladabiblia.util.exception.BusinessException;

@Service
@Transactional(readOnly = true)
public class AtividadesEstudoServiceImpl implements AtividadesEstudoService {

	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private MaterialEstudoRepository materialEstudoRepository;

	@Autowired
	private AtividadeEstudoRepository atividadeEstudoRepository;
	
	@Autowired
	private CertificadoEnviadoRepository certificadoEnviadoRepository;
	
	@Override
	public AtividadesEstudoEdicaoDTO obterAtividadesEstudoAlunoParaEdicao(Long id) throws BusinessException {

		final Postagem postagem = postagemRepository.findLastOpen();

		if (postagem == null) {

			throw new BusinessException("erro.atividade.estudo.sem.postagem.aberta");
		}

		final Aluno aluno = alunoRepository.findOne(id);

		final AtividadesEstudoEdicaoDTO edicao = new AtividadesEstudoEdicaoDTO(aluno, postagem);

		return edicao;
	}

	@Override
	@Transactional(readOnly = false)
	public void adicionarAtividade(Long idAluno, Long idPostagem, Long idMaterial, boolean enviarBiblia) {

		final Postagem postagem = postagemRepository.findOne(idPostagem);

		final AtividadeEstudo atividadeEstudo = AtividadeEstudo.builder()
				.withPostagem(postagem)
				.withAluno(Aluno.builder().withId(idAluno).build())
				.withMaterial(materialEstudoRepository.findOne(idMaterial)).build();

		this.proccessBiblia(idAluno, enviarBiblia, postagem, atividadeEstudo);

		atividadeEstudoRepository.save(atividadeEstudo);
	}

	/**
	 * 
	 * Se {@code enviarBiblia} igual à {@code true}, cria uma instancia de
	 * {@code BibliaEnviada}, associa à atividade de estudo e postagem recebidas
	 * e realiza o update da informação {@code possuiBiblia} da entidade
	 * {@code Aluno}.
	 * 
	 * @param idAluno
	 * @param enviarBiblia
	 * @param postagem
	 * @param atividadeEstudo
	 */
	private void proccessBiblia(Long idAluno, boolean enviarBiblia, final Postagem postagem,
			final AtividadeEstudo atividadeEstudo) {
		
		if (enviarBiblia) {

			alunoRepository.updateBibliaStatus(idAluno, true);

			atividadeEstudo.setBiblia(
					BibliaEnviada.builder().withPostagem(postagem).withAtividadeEstudo(atividadeEstudo).build());
		}
	}
	
	@Override
	public AtividadeEstudo obterAtividadePorId(Long id) {

		return atividadeEstudoRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = false)
	public AtividadeEstudo atualizarAtividade(AtividadeEstudoEdicaoDTO atividade) {

		final AtividadeEstudo atividadeEstudo = this.obterAtividadePorId(atividade.getId());

		atividadeEstudo.setDataRetornoEstudo(atividade.getDataRetornoEstudo());

		atividadeEstudo.setNota(atividade.getNota());
		
		atividadeEstudo.setAtividadeEncerrada(atividade.isAtividadeEncerrada());

		this.proccessCertificado(atividade.isCertificado(), atividadeEstudo);

		return atividadeEstudoRepository.save(atividadeEstudo);
	}
	
	/**
	 * 
	 * Se {@code isCertificado} igual à {@code true}, cria uma instância de
	 * {@code CertificadoEnviado}, associa à atividade de estudo recebida e à
	 * postagem em aberto. Senão remove qualquer registro de
	 * {@code CertificadoEnviado} associado à atividade de estudo recebida.
	 * 
	 * @param isCertificado
	 * @param atividadeEstudo
	 * 
	 * @throws IllegalArgumentException
	 *             se não houver uma postagem em aberto
	 */
	private void proccessCertificado(boolean isCertificado, AtividadeEstudo atividadeEstudo) {

		if (isCertificado) {

			if (!certificadoEnviadoRepository.existsByAtividadeEstudo_Id(atividadeEstudo.getId())) {

				Postagem postagem = postagemRepository.findLastOpen();
				Assert.notNull(postagem, "postagem não pode ser null");
				
				atividadeEstudo.setCertificado(CertificadoEnviado.builder()
						.withAtividadeEstudo(atividadeEstudo)
						.withPostagem(postagem)
						.build());
			}

		} else {

			atividadeEstudo.setCertificado(null);
			
			certificadoEnviadoRepository.deleteByAtividadeEstudoId(atividadeEstudo.getId());
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deletarAtividade(AtividadeEstudo atividadeEstudo) throws BusinessException {

		if (atividadeEstudo.isPostagemEncerrada()) {

			throw new BusinessException("erro.atividade.estudo.deletar.postagem.encerrada");
		}

		if (atividadeEstudo.getBiblia() != null) {

			alunoRepository.updateBibliaStatus(atividadeEstudo.getAluno().getId(), false);
		}

		atividadeEstudoRepository.delete(atividadeEstudo);
	}

	@Override
	public AtividadesEstudoHistoricoDTO obterHistoricoAtividadesEstudoAluno(Long id) {

		final Aluno aluno = alunoRepository.findOne(id);

		AtividadesEstudoHistoricoDTO historico = new AtividadesEstudoHistoricoDTO(aluno.getId(), aluno.getNome());

		historico.getHistorico().addAll(atividadeEstudoRepository.obterHistoricoAtividadesEstudoAluno(id));

		return historico;
	}

	@Override
	@Transactional(readOnly = false)
	public void adicionarAtividadeHistorico(AtividadeEstudoHistoricoDTO atividadeHistorico) {

		final Postagem postagemAtividade = this.obterPostagemHistorico(atividadeHistorico.getDataEnvioEstudo());

		AtividadeEstudo atividadeEstudo = AtividadeEstudo.builder()
				.withPostagem(postagemAtividade)
				.withAluno(alunoRepository.findOne(atividadeHistorico.getAluno()))
				.withMaterial(materialEstudoRepository.findOne(atividadeHistorico.getMaterial()))
				.build();

		atividadeEstudo.setAtividadeEncerrada(true);
		atividadeEstudo.setNota(atividadeHistorico.getNota());
		atividadeEstudo.setDataRetornoEstudo(atividadeHistorico.getDataRetornoEstudo());

		atividadeEstudo.setCertificado(
				this.obterCertificadoHistorico(atividadeHistorico.getDataEnvioCertificado(), atividadeEstudo));

		atividadeEstudo.setBiblia(
				this.obterBibliaHistorico(atividadeHistorico.getDataEnvioBiblia(), atividadeEstudo));

		if (atividadeEstudo.getBiblia() != null) {

			alunoRepository.updateBibliaStatus(atividadeHistorico.getAluno(), true);
		}

		atividadeEstudoRepository.save(atividadeEstudo);

	}

	/**
	 * Busca uma {@code Postagem} pela data prevista de envio. Caso não exista
	 * cria uma nova com data efetiva de envio igual à data prevista, ou seja,
	 * uma postagem já encerrada e retorna.
	 * 
	 * @param dataPostagem
	 * @return {@code Postagem} da data informada.
	 */
	private Postagem obterPostagemHistorico(Calendar dataPostagem) {
		
		Postagem postagem = postagemRepository.findByDataPrevistaEnvio(dataPostagem);

		if (postagem == null) {

			postagem = Postagem.builder().withDataPrevistaEnvio(dataPostagem).build();

			postagem.setDataEfetivaEnvio(dataPostagem);

			postagemRepository.save(postagem);
		}
		
		return postagem;
	}
	
	/**
	 * Cria e retorna uma instância de {@code CertificadoEnviado} associada à
	 * atividade de estudo recebida e à postagem com data de envio igual à data
	 * de envio do certificado recebida.
	 * 
	 * @param dataEnvioCertificado
	 * @param atividadeEstudo
	 * @return Se dataEnvioCertificado diferente de {@code null} uma nova
	 *         instância de {@code CertificadoEnviado}, senão {@code null}.
	 */
	private CertificadoEnviado obterCertificadoHistorico(Calendar dataEnvioCertificado, AtividadeEstudo atividadeEstudo) {
		
		if (dataEnvioCertificado == null) {
			return null;
		}
			
		Postagem postagemCertificado = this.obterPostagemHistorico(dataEnvioCertificado);
		
		return CertificadoEnviado.builder()
				.withAtividadeEstudo(atividadeEstudo)
				.withPostagem(postagemCertificado)
				.build();
	}

	/**
	 * Cria e retorna uma instância de {@code BibliaEnviada} associada à
	 * atividade de estudo recebida e à postagem com data de envio igual à data
	 * de envio da bíblia recebida.
	 * 
	 * @param dataEnvioBiblia
	 * @param atividadeEstudo
	 * @return Se dataEnvioBiblia diferente de {@code null} uma nova instância
	 *         de {@code BibliaEnviada}, senão {@code null}.
	 */
	private BibliaEnviada obterBibliaHistorico(Calendar dataEnvioBiblia, AtividadeEstudo atividadeEstudo) {

		if (dataEnvioBiblia == null) {
			return null;
		}
			
		Postagem postagemBiblia = this.obterPostagemHistorico(dataEnvioBiblia);
		
		return BibliaEnviada.builder()
				.withAtividadeEstudo(atividadeEstudo)
				.withPostagem(postagemBiblia)
				.build();
	}

}
