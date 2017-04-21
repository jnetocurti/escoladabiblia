package br.com.escoladabiblia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escoladabiblia.model.AtividadeEstudo;
import br.com.escoladabiblia.repository.custom.AtividadeEstudoRepositoryCustom;

public interface AtividadeEstudoRepository extends JpaRepository<AtividadeEstudo, Long>, AtividadeEstudoRepositoryCustom {

}
