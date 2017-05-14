package br.com.escoladabiblia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.escoladabiblia.model.BibliaEnviada;

public interface BibliaEnviadaRepository extends JpaRepository<BibliaEnviada, Long> {

	boolean existsByAtividadeEstudo_Id(Long id);

	@Modifying
	@Query("delete from BibliaEnviada biblia where biblia.atividadeEstudo.id = :id")
	void deleteByAtividadeEstudoId(@Param("id") Long id);


}
