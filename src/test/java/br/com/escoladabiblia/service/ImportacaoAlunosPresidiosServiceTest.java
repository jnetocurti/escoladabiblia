package br.com.escoladabiblia.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.escoladabiblia.EscoladabibliaApplicationTests;
import br.com.escoladabiblia.model.Endereco;
import br.com.escoladabiblia.model.Estado;
import br.com.escoladabiblia.model.Presidio;
import br.com.escoladabiblia.repository.AlunoRepository;
import br.com.escoladabiblia.repository.EstadoRepository;
import br.com.escoladabiblia.repository.PresidioRepository;

public class ImportacaoAlunosPresidiosServiceTest extends EscoladabibliaApplicationTests {

	private static final String FILE_PATH_HOMENS = "importaveis/alunos-presidios.xlsx";

	private static final String FILE_PATH_MULHERES = "importaveis/alunas-presidios.xlsx";

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private PresidioRepository presidioRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private ImportacaoAlunosPresidiosService importacaoAlunosPresidiosService;

	private Estado estado;

	@Before
	public void init() {
		inserirEstados();
		inserirPenitenciarias();
	}

	@Test
	public void importPresidiosFromXLSXFileTest() throws IOException {

		importacaoAlunosPresidiosService.importAlunosPresidiosFromXLSXFile(
				Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_PATH_HOMENS));

		importacaoAlunosPresidiosService.importAlunosPresidiosFromXLSXFile(
				Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_PATH_MULHERES));

		assertEquals(701, alunoRepository.count());
	}

	private void inserirPenitenciarias() {
		presidioRepository.save(createPresidio("CR Feminino de Araraquara"));
		presidioRepository.save(createPresidio("CR Feminino de São José do Rio Preto"));
		presidioRepository.save(createPresidio("Penitenciária Feminina São Paulo Butantã"));
		presidioRepository.save(createPresidio("Penitenciária Feminina da Capital"));
		presidioRepository.save(createPresidio("Penitenciária Feminina de Ribeirão Preto"));
		presidioRepository.save(createPresidio("Penitenciária Feminina Sant'Ana São Paulo"));
		presidioRepository.save(createPresidio("Penitenciária Feminina de Tupi Paulista"));
		presidioRepository.save(createPresidio("CDP Masculino de Caiuá"));
		presidioRepository.save(createPresidio("CDP Masculino Pinheiros 1"));
		presidioRepository.save(createPresidio("CPP Masculino Jardinópolis"));
		presidioRepository.save(createPresidio("CPP 1 Masculino de Bauru"));
		presidioRepository.save(createPresidio("CPP 2 Masculino de Bauru"));
		presidioRepository.save(createPresidio("CR Masculino de Araraquara"));
		presidioRepository.save(createPresidio("CR Masculino de Rio Claro"));
		presidioRepository.save(createPresidio("Penitenciária Masculina de Araraquara"));
		presidioRepository.save(createPresidio("Penitenciária Masculina de Avanhandava"));
		presidioRepository.save(createPresidio("Penitenciária Masculina de Itapetininga"));
		presidioRepository.save(createPresidio("Penitenciária Masculina de Potim"));
		presidioRepository.save(createPresidio("Penitenciária Masculina de Presidente Venceslau I"));
		presidioRepository.save(createPresidio("Penitenciária Masculina de Presidente Venceslau II"));
		presidioRepository.save(createPresidio("Penitenciária Masculina de Reginopolis II"));
		presidioRepository.save(createPresidio("Penitenciária Masculina de Serra Azul II"));
		presidioRepository.save(createPresidio("Penitenciária Masculina de Marabá Paulista"));
		presidioRepository.save(createPresidio("Penitenciária Masculina de Paraguaçu Paulista"));
		presidioRepository.save(createPresidio("Penitenciária Masculina de Pracinha"));
		presidioRepository.save(createPresidio("Penitenciária Masculina de Presidente Prudente"));
	}

	private Presidio createPresidio(String nome) {
		Presidio presidio = new Presidio();
		presidio.setNome(nome);
		presidio.setEndereco(createEndereco());
		return presidio;
	}

	private Endereco createEndereco() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro("logradouro");
		endereco.setCidade("cidade");
		endereco.setEstado(estado);
		endereco.setNumero(0);
		return endereco;
	}

	private void inserirEstados() {
		estado = new Estado();
		estado.setUf("SP");
		estado.setDescricao("São Paulo");
		estadoRepository.save(estado);
	}

}
