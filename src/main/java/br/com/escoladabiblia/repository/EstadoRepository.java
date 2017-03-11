package br.com.escoladabiblia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escoladabiblia.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	Estado findByUf(String uf);

}
