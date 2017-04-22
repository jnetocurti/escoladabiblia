package br.com.escoladabiblia.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.AtividadeEstudo;
import br.com.escoladabiblia.model.Postagem;
import br.com.escoladabiblia.repository.AlunoRepository;
import br.com.escoladabiblia.repository.AtividadeEstudoRepository;
import br.com.escoladabiblia.repository.MaterialEstudoRepository;
import br.com.escoladabiblia.repository.PostagemRepository;
import br.com.escoladabiblia.util.dto.AtividadesEstudoEdicaoDTO;
import br.com.escoladabiblia.util.exception.BusinessException;

@Service
public class AtividadesEstudoServiceImpl implements AtividadesEstudoService {

	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private MaterialEstudoRepository materialEstudoRepository;

	@Autowired
	private AtividadeEstudoRepository atividadeEstudoRepository;

	@Override
	public AtividadesEstudoEdicaoDTO obterAtividadesEstudoAlunoParaEdicao(Long id) throws BusinessException {

		final Postagem postagem = postagemRepository.findLastOpen();

		if (postagem == null) {

			throw new BusinessException("erro.atividade.estudo.sem.postagem.aberta");
		}

		final Aluno aluno = alunoRepository.findOne(id);

		final AtividadesEstudoEdicaoDTO edicao = new AtividadesEstudoEdicaoDTO(aluno, postagem);

		edicao.getMateriais()
				.addAll(materialEstudoRepository.obterMateriaisNaoEstudados(getIdsMateriaisEstudados(aluno)));

		return edicao;
	}

	@Override
	public void adicionarAtividade(Long idAluno, Long idPostagem, Long idMaterial) {

		final AtividadeEstudo atividadeEstudo = new AtividadeEstudo();

		atividadeEstudo.setAluno(Aluno.builder().withId(idAluno).build());

		atividadeEstudo.setPostagem(postagemRepository.findOne(idPostagem));

		atividadeEstudo.setMaterial(materialEstudoRepository.findOne(idMaterial));

		atividadeEstudoRepository.save(atividadeEstudo);
	}

	private List<Long> getIdsMateriaisEstudados(final Aluno aluno) {
		return aluno.getAtividadesEstudo().stream().map(a -> a.getMaterial().getId()).collect(Collectors.toList());
	}

}
