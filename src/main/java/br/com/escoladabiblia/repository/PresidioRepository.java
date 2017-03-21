package br.com.escoladabiblia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escoladabiblia.model.Presidio;

public interface PresidioRepository extends JpaRepository<Presidio, Long> {
	
	public Presidio findByNome(String nome);

}
