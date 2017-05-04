package br.com.escoladabiblia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escoladabiblia.model.ControleImportacao;
import br.com.escoladabiblia.model.TipoImportacao;

/**
 * @deprecated escoladabiblia 1.0 - O processo de importação dos dados legados
 *             atualmente salvos em planílhas do Excel não mais existirá nas
 *             próximas versões do sistema, tendo apenas o propósito específico
 *             de facilitar o cadastro/setup destas informações.
 */
@Repository
public interface ControleImportacaoRepository extends JpaRepository<ControleImportacao, Long> {

	boolean existsByTipoImportacao(TipoImportacao tipoImportacao);

}
