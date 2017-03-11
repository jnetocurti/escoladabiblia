package br.com.escoladabiblia.service;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.escoladabiblia.EscoladabibliaApplicationTests;
import br.com.escoladabiblia.model.Estado;
import br.com.escoladabiblia.repository.EstadoRepository;

public class ImportacaoPresidiosServiceTest extends EscoladabibliaApplicationTests {

	private static final String FILE_PATH = "importaveis/Endereços Penitenciárias.xlsx";

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ImportacaoPresidiosService importacaoPresidiosService;

	@org.junit.Before
	public void init() {
		inserirEstados();
	}

	@Test
	public void importPresidiosFromXLSXFileTest() throws IOException {

		importacaoPresidiosService.importPresidiosFromXLSXFile(
				Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_PATH));

	}

	private void inserirEstados() {
		estadoRepository.save(createEstado("SP", "São Paulo"));
	}

	private Estado createEstado(String uf, String descricao) {
		Estado estado = new Estado();
		estado.setUf(uf);
		estado.setDescricao(descricao);
		return estado;
	}

}
