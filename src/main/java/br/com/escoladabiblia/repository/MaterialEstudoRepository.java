package br.com.escoladabiblia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escoladabiblia.model.MaterialEstudo;

public interface MaterialEstudoRepository extends JpaRepository<MaterialEstudo, Long> {

	List<MaterialEstudo> findByIdIsNotIn(List<Long> ids);

}
