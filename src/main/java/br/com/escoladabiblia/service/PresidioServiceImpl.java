package br.com.escoladabiblia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escoladabiblia.model.Presidio;
import br.com.escoladabiblia.repository.PresidioRepository;

@Service
public class PresidioServiceImpl implements PresidioService {

	@Autowired
	private PresidioRepository presidioRepository;

	@Override
	public List<Presidio> findAll() {

		return presidioRepository.findAll();
	}

	@Override
	public Presidio findById(Long id) {

		return presidioRepository.findOne(id);
	}

}
