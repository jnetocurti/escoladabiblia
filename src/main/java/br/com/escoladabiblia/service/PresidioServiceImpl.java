package br.com.escoladabiblia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escoladabiblia.model.Presidio;
import br.com.escoladabiblia.repository.PresidioRepository;
import br.com.escoladabiblia.util.pagination.BootgridRequest;
import br.com.escoladabiblia.util.pagination.BootgridResponse;

@Service
@Transactional(readOnly = true)
public class PresidioServiceImpl implements PresidioService {

	@Autowired
	private PresidioRepository presidioRepository;

	@Override
	public List<Presidio> findAll() {

		return presidioRepository.findAll(new Sort("nome"));
	}

	@Override
	public Presidio findById(Long id) {

		return presidioRepository.findOne(id);
	}

	@Override
	public BootgridResponse<Presidio> findPresidiosByNome(BootgridRequest bootgridRequest) {

		return new BootgridResponse<Presidio>(presidioRepository.findByNome(
				"%" + bootgridRequest.getSearchPhrase().toUpperCase() + "%", bootgridRequest.getPageRequest()));
	}

	@Override
	@Transactional(readOnly = false)
	public void salvar(Presidio presidio) {
		
		presidioRepository.save(presidio);
	}

}
