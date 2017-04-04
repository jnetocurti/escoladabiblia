package br.com.escoladabiblia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Presidiario;
import br.com.escoladabiblia.repository.AlunoRepository;
import br.com.escoladabiblia.util.dto.AlunoPresidioDTO;
import br.com.escoladabiblia.util.pagination.BootgridRequest;
import br.com.escoladabiblia.util.pagination.BootgridResponse;

@Service
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public BootgridResponse<AlunoPresidioDTO> findAlunosPresidiosByNomeOrMatriculaOrNomePresidio(
			BootgridRequest bootgridRequest) {

		return new BootgridResponse<AlunoPresidioDTO>(
				alunoRepository.findAlunosPresidiosByNomeOrMatriculaOrNomePresidio(
						"%" + bootgridRequest.getSearchPhrase() + "%", bootgridRequest.getPageRequest()));
	}

	@Override
	public Aluno salvar(Aluno aluno, Presidiario presidiario) {

		presidiario.setAluno(aluno);

		aluno.getCaracterizacoes().add(presidiario);

		return alunoRepository.save(aluno);
	}

	@Override
	public Aluno editar(Long id) {

		return alunoRepository.findOne(id);
	}

}
