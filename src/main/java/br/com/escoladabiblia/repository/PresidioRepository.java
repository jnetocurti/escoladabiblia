package br.com.escoladabiblia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.escoladabiblia.model.Presidio;

public interface PresidioRepository extends JpaRepository<Presidio, Long> {

	public Presidio findByNome(String nome);

	@Query(" select presidio from Presidio presidio where upper(presidio.nome) like :nome order by presidio.nome ")
	public Page<Presidio> findByNome(@Param("nome") String nome, Pageable pageable);

}
