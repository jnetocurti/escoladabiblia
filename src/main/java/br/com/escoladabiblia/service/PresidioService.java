package br.com.escoladabiblia.service;

import java.util.List;

import br.com.escoladabiblia.model.Presidio;

public interface PresidioService {

	List<Presidio> findAll();

	Presidio findById(Long id);

}
