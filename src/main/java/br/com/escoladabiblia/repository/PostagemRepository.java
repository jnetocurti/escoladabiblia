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

	@Query("select p from Postagem p where p.dataPrevistaEnvio is not null and p.dataEfetivaEnvio is null")
	Postagem findLastOpen();

	@Query("select p from Postagem p where p.dataPrevistaEnvio between :dataDe and :dataAte")
	List<Postagem> findByPeriod(@Param("dataDe") Calendar dataDe, @Param("dataAte") Calendar dataAte);
	
	Postagem findByDataPrevistaEnvio(@Param("dataPostagem") Calendar dataPostagem);

}
