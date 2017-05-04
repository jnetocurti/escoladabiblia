package br.com.escoladabiblia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escoladabiblia.model.ControleImportacao;
import br.com.escoladabiblia.model.TipoImportacao;

@Repository
public interface ControleImportacaoRepository extends JpaRepository<ControleImportacao, Long> {

	boolean existsByTipoImportacao(TipoImportacao tipoImportacao);
	
}
