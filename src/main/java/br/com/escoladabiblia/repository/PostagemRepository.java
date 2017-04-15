package br.com.escoladabiblia.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.escoladabiblia.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	@Query("SELECT p FROM Postagem p WHERE p.dataPrevistaEnvio IS NOT NULL AND p.dataEfetivaEnvio IS NULL")
	Postagem findLastOpenPostagem();

	@Query("SELECT p FROM Postagem p WHERE p.dataPrevistaEnvio BETWEEN :dataDe AND :dataAte")
	List<Postagem> findByPeriod(@Param("dataDe") Calendar dataDe, @Param("dataAte") Calendar dataAte);

}
