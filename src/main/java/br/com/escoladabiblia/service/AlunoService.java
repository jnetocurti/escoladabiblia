package br.com.escoladabiblia.service;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Presidiario;
import br.com.escoladabiblia.util.dto.AlunoComumDTO;
import br.com.escoladabiblia.util.dto.AlunoPresidioDTO;
import br.com.escoladabiblia.util.pagination.BootgridRequest;
import br.com.escoladabiblia.util.pagination.BootgridResponse;

public interface AlunoService {

	BootgridResponse<AlunoPresidioDTO> findAlunosPresidiosByNomeOrMatriculaOrNomePresidio(
			BootgridRequest bootgridRequest);

	Aluno salvarAlunoPresidio(Aluno aluno, Presidiario presidiario);

	BootgridResponse<AlunoComumDTO> findAlunosComunsByNome(BootgridRequest bootgridRequest);

	Aluno salvarAlunoComum(Aluno aluno);

	Aluno editar(Long id);

}
