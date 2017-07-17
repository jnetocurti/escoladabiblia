package br.com.escoladabiblia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Endereco;
import br.com.escoladabiblia.model.Presidiario;
import br.com.escoladabiblia.repository.AlunoRepository;
import br.com.escoladabiblia.util.dto.AlunoComumDTO;
import br.com.escoladabiblia.util.dto.AlunoPresidioDTO;
import br.com.escoladabiblia.util.pagination.BootgridRequest;
import br.com.escoladabiblia.util.pagination.BootgridResponse;

@Service
@Transactional(readOnly = true)
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public BootgridResponse<AlunoPresidioDTO> findAlunosPresidiosByNomeOrMatriculaOrNomePresidio(
			BootgridRequest bootgridRequest) {

		return new BootgridResponse<AlunoPresidioDTO>(
				alunoRepository.findAlunosPresidiosByNomeOrMatriculaOrNomePresidio(
						"%" + bootgridRequest.getSearchPhrase().toUpperCase() + "%", bootgridRequest.getPageRequest()));
	}

	@Override
	@Transactional(readOnly = false)
	public Aluno salvarAlunoPresidio(Aluno aluno, Presidiario presidiario) {

		presidiario.setAluno(aluno);

		aluno.getCaracterizacoes().add(presidiario);

		return alunoRepository.save(aluno);
	}
	
	@Override
	public BootgridResponse<AlunoComumDTO> findAlunosComunsByNome(BootgridRequest bootgridRequest) {

		return new BootgridResponse<AlunoComumDTO>(alunoRepository.findAlunosComunsByName(
				"%" + bootgridRequest.getSearchPhrase().toUpperCase() + "%", bootgridRequest.getPageRequest()));
	}

	@Override
	@Transactional(readOnly = false)
	public Aluno salvarAlunoComum(Aluno aluno) {
		
		return alunoRepository.save(aluno);
	}

	@Override
	public Aluno editar(Long id) {

		return alunoRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void adicionarEnderecoAluno(Long id, Endereco endereco) {

		Aluno aluno = alunoRepository.findOne(id);

		if (aluno.getCaracterizacao() != null) {
			
			aluno.getCaracterizacao().setAtiva(false);
		}

		aluno.setEndereco(endereco);

		alunoRepository.save(aluno);
	}

}
