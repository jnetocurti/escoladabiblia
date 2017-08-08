package br.com.escoladabiblia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escoladabiblia.model.Parametros;

@Repository
public interface ParametrosRepository extends JpaRepository<Parametros, Long> {

}
