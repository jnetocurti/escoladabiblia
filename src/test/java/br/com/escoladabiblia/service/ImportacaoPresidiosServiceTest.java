package br.com.escoladabiblia.service;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.escoladabiblia.EscoladabibliaApplicationTests;

public class ImportacaoPresidiosServiceTest extends EscoladabibliaApplicationTests {

	private static final String FILE_PATH = "importaveis/penitenciarias.xlsx";

	@Autowired
	private ImportacaoPresidiosService importacaoPresidiosService;

	@Test
	public void importPresidiosFromXLSXFileTest() throws IOException {

		importacaoPresidiosService.importPresidiosFromXLSXFile(
				Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_PATH));

	}

}
