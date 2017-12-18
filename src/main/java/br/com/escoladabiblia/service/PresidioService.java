package br.com.escoladabiblia.service;

import java.util.List;

import br.com.escoladabiblia.model.Presidio;
import br.com.escoladabiblia.util.pagination.BootgridRequest;
import br.com.escoladabiblia.util.pagination.BootgridResponse;

public interface PresidioService {

	List<Presidio> findAll();

	Presidio findById(Long id);

	BootgridResponse<Presidio> findPresidiosByNome(BootgridRequest bootgridRequest);

	void salvar(Presidio presidio);

}
