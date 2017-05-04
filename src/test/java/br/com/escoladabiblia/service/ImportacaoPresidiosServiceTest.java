package br.com.escoladabiblia.service;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.escoladabiblia.EscoladabibliaApplicationTests;
import br.com.escoladabiblia.util.exception.BusinessException;

/**
 * @deprecated escoladabiblia 1.0 - O processo de importação dos dados legados
 *             atualmente salvos em planílhas do Excel não mais existirá nas
 *             próximas versões do sistema, tendo apenas o propósito específico
 *             de facilitar o cadastro/setup destas informações.
 */
public class ImportacaoPresidiosServiceTest extends EscoladabibliaApplicationTests {

	private static final String FILE_PATH = "importaveis/penitenciarias.xlsx";

	@Autowired
	private ImportacaoPresidiosService importacaoPresidiosService;

	@Test
	public void importPresidiosFromXLSXFileTest() throws IOException, BusinessException {

		importacaoPresidiosService.importPresidiosFromXLSXFile(
				Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_PATH));

	}

}
