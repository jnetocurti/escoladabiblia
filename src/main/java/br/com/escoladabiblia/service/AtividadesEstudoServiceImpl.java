package br.com.escoladabiblia.service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.AtividadeEstudo;
import br.com.escoladabiblia.repository.AlunoRepository;
import br.com.escoladabiblia.repository.AtividadeEstudoRepository;
import br.com.escoladabiblia.repository.MaterialEstudoRepository;
import br.com.escoladabiblia.util.dto.EdicaoAtividadesEstudoDTO;

@Service
public class AtividadesEstudoServiceImpl implements AtividadesEstudoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private MaterialEstudoRepository materialEstudoRepository;

	@Autowired
	private AtividadeEstudoRepository atividadeEstudoRepository;

	@Override
	public EdicaoAtividadesEstudoDTO obterAtividadesEstudoAlunoParaEdicao(Long id) {

		final Aluno aluno = alunoRepository.findOne(id);

		final EdicaoAtividadesEstudoDTO edicao = new EdicaoAtividadesEstudoDTO(aluno, Calendar.getInstance());

		edicao.getMateriais().addAll(materialEstudoRepository.findByIdIsNotIn(getIdsMateriais(aluno)));

		return edicao;
	}

	@Override
	public void adicionarAtividade(Long idAluno, Calendar dataProximoEnvio, Long idMaterial) {

		final AtividadeEstudo atividadeEstudo = new AtividadeEstudo();

		atividadeEstudo.setAluno(alunoRepository.findOne(idAluno));

		atividadeEstudo.setDataEnvioEstudo(dataProximoEnvio);

		atividadeEstudo.setMaterial(materialEstudoRepository.findOne(idMaterial));

		atividadeEstudoRepository.save(atividadeEstudo);
	}
	
	private List<Long> getIdsMateriais(final Aluno aluno) {

		List<Long> idsMateriais =aluno.getAtividadesEstudo().stream().map(a -> a.getMaterial().getId()).collect(Collectors.toList());

		// workaround :(
		
		// Consulta NOT IN não retorna todos resultados quando a lista de ids enviada estiver vazia.
		// O zero foi acrescentado para que todos registros fossem retornados nesse caso.
		idsMateriais.add(0L);

		return idsMateriais;
	}

}
