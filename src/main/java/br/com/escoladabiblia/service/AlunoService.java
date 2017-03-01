package br.com.escoladabiblia.service;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.util.pagination.BootgridRequest;
import br.com.escoladabiblia.util.pagination.BootgridResponse;

public interface AlunoService {

	BootgridResponse<Aluno> findByName(BootgridRequest bootgridRequest);

	Aluno salvar(Aluno aluno);

}
