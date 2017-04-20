package br.com.escoladabiblia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escoladabiblia.model.MaterialEstudo;
import br.com.escoladabiblia.repository.custom.MaterialEstudoRepositoryCustom;

public interface MaterialEstudoRepository extends JpaRepository<MaterialEstudo, Long>, MaterialEstudoRepositoryCustom {

}
