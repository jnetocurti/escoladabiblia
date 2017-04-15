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
import br.com.escoladabiblia.util.dto.EdicaoAtividadesEstudoDTO;
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
	public EdicaoAtividadesEstudoDTO obterAtividadesEstudoAlunoParaEdicao(Long id) throws BusinessException {

		final Postagem postagem = postagemRepository.findLastOpenPostagem();

		if (postagem == null) {
			
			throw new BusinessException("erro.atividade.estudo.sem.postagem.aberta");
		}

		final Aluno aluno = alunoRepository.findOne(id);

		final EdicaoAtividadesEstudoDTO edicao = new EdicaoAtividadesEstudoDTO(aluno, postagem);

		edicao.getMateriais().addAll(materialEstudoRepository.findByIdIsNotIn(getIdsMateriaisEstudados(aluno)));

		return edicao;
	}

	@Override
	public void adicionarAtividade(Long idAluno, Long idPostagem, Long idMaterial) {

		final AtividadeEstudo atividadeEstudo = new AtividadeEstudo();

		atividadeEstudo.setAluno(alunoRepository.findOne(idAluno));
		
		atividadeEstudo.setPostagem(postagemRepository.findOne(idPostagem));

		atividadeEstudo.setMaterial(materialEstudoRepository.findOne(idMaterial));

		atividadeEstudoRepository.save(atividadeEstudo);
	}

	private List<Long> getIdsMateriaisEstudados(final Aluno aluno) {

		List<Long> idsMateriais = aluno.getAtividadesEstudo().stream().map(a -> a.getMaterial().getId())
				.collect(Collectors.toList());

		// workaround :(

		// Consulta NOT IN não retorna todos resultados quando a lista de ids enviada estiver vazia.
		// O zero foi acrescentado para garantir que todos registros fossem retornados nesse caso.
		idsMateriais.add(0L);

		return idsMateriais;
	}

}
