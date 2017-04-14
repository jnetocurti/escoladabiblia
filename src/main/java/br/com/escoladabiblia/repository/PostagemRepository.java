package br.com.escoladabiblia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.escoladabiblia.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	@Query("SELECT p FROM Postagem p WHERE p.dataPrevistaEnvio IS NOT NULL AND p.dataEfetivaEnvio IS NULL")
	Postagem findLastOpenPostagem();

}
