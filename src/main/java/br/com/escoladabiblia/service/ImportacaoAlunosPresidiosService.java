package br.com.escoladabiblia.service;

import java.io.IOException;
import java.io.InputStream;

import br.com.escoladabiblia.util.exception.BusinessException;

/**
 * @deprecated escoladabiblia 1.0 - O processo de importação dos dados legados
 *             atualmente salvos em planílhas do Excel não mais existirá nas
 *             próximas versões do sistema, tendo apenas o propósito específico
 *             de facilitar o cadastro/setup destas informações.
 */
public interface ImportacaoAlunosPresidiosService {

	void importAlunosPresidiosFromXLSXFile(InputStream stream, String fileName) throws IOException, BusinessException;

}
