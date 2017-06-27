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
import br.com.escoladabiblia.repository.BibliaEnviadaRepository;
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
	
	@Autowired
	private BibliaEnviadaRepository bibliaEnviadaRepository;

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

		if (enviarBiblia) {
			atividadeEstudo.setBiblia(
					BibliaEnviada.builder().withPostagem(postagem).withAtividadeEstudo(atividadeEstudo).build());
		}

		atividadeEstudoRepository.save(atividadeEstudo);
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

		proccessCertificado(atividade.isCertificado(), atividadeEstudo);

		proccessBiblia(atividade.isBiblia(), atividadeEstudo);
		
		return atividadeEstudoRepository.save(atividadeEstudo);
	}
	
	private void proccessCertificado(boolean isCertificado, AtividadeEstudo atividadeEstudo) {

		if (isCertificado) {

			if (!certificadoEnviadoRepository.existsByAtividadeEstudo_Id(atividadeEstudo.getId())) {

				Postagem postagem = postagemRepository.findLastOpen();
				Assert.notNull(postagem, "postagem não pode ser null");
				
				atividadeEstudo.setCertificado(CertificadoEnviado.builder()
						.withAtividadeEstudo(atividadeEstudo)
						.withPostagem(postagem).build());
			}

		} else {

			certificadoEnviadoRepository.deleteByAtividadeEstudoId(atividadeEstudo.getId());
		}
	}

	private void proccessBiblia(boolean isBiblia, AtividadeEstudo atividadeEstudo) {

		if (isBiblia) {

			if (!bibliaEnviadaRepository.existsByAtividadeEstudo_Id(atividadeEstudo.getId())) {
				
				Postagem postagem = postagemRepository.findLastOpen();
				Assert.notNull(postagem, "postagem não pode ser null");
				
				atividadeEstudo.setBiblia(BibliaEnviada.builder()
						.withAtividadeEstudo(atividadeEstudo)
						.withPostagem(postagem).build());
			}
			
		} else {

			bibliaEnviadaRepository.deleteByAtividadeEstudoId(atividadeEstudo.getId());
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deletarAtividade(AtividadeEstudo atividadeEstudo) throws BusinessException {

		if (atividadeEstudo.isPostagemEncerrada()) {

			throw new BusinessException("erro.atividade.estudo.deletar.postagem.encerrada");
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
		
		Postagem postagemAtividade = obterPostagem(atividadeHistorico.getDataEnvioEstudo());
		
		AtividadeEstudo atividadeEstudo = AtividadeEstudo.builder()
				.withPostagem(postagemAtividade)
				.withAluno(alunoRepository.findOne(atividadeHistorico.getAluno()))
				.withMaterial(materialEstudoRepository.findOne(atividadeHistorico.getMaterial())).build();
		
		atividadeEstudo.setAtividadeEncerrada(true);
		atividadeEstudo.setNota(atividadeHistorico.getNota());
		atividadeEstudo.setDataRetornoEstudo(atividadeHistorico.getDataRetornoEstudo());
		
		atividadeEstudo.setCertificado(obterCertificado(atividadeHistorico.getCertificado(), atividadeEstudo));
		atividadeEstudo.setBiblia(obterBiblia(atividadeHistorico.getBiblia(), atividadeEstudo));
		
		atividadeEstudoRepository.save(atividadeEstudo);

	}

	private Postagem obterPostagem(Calendar dataPostagem) {
		
		Postagem postagem = postagemRepository.findByDataPrevistaEnvio(dataPostagem);

		if (postagem == null) {

			postagem = Postagem.builder().withDataPrevistaEnvio(dataPostagem).build();

			postagem.setDataEfetivaEnvio(dataPostagem);

			postagemRepository.save(postagem);
		}
		
		return postagem;
	}
	
	private CertificadoEnviado obterCertificado(Calendar certificado, AtividadeEstudo atividadeEstudo) {
		
		if (certificado == null) {
			return null;
		}
			
		Postagem postagemCertificado = obterPostagem(certificado);
		
		return CertificadoEnviado.builder()
				.withAtividadeEstudo(atividadeEstudo)
				.withPostagem(postagemCertificado)
				.build();
	}

	private BibliaEnviada obterBiblia(Calendar biblia, AtividadeEstudo atividadeEstudo) {

		if (biblia == null) {
			return null;
		}
			
		Postagem postagemBiblia = obterPostagem(biblia);
		
		return BibliaEnviada.builder()
				.withAtividadeEstudo(atividadeEstudo)
				.withPostagem(postagemBiblia)
				.build();
	}

}
