package br.com.escoladabiblia.util.impressao;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Endereco;
import br.com.escoladabiblia.model.Estado;
import br.com.escoladabiblia.model.Presidiario;
import br.com.escoladabiblia.model.Presidio;

public class DestinatarioTest {

	@Test
	public void destinatarioPresidiarioCompletoTest() {

		Aluno aluno = buildAluno();
		Presidiario presidiario = buildPresidiario();
		
		presidiario.setMatricula("111.111.111");
		presidiario.setRaio(1);
		presidiario.setCela(10);
		
		presidiario.setPresidio(buildPresidio());
		presidiario.getPresidio().getEndereco().setNumero(100);
		presidiario.getPresidio().getEndereco().setCep("11111-111");
		presidiario.getPresidio().getEndereco().setBairro("Vila da Imutabilidade");
		presidiario.getPresidio().getEndereco().setComplemento("Estrada da Corrupção");

		aluno.getCaracterizacoes().add(presidiario);

		DestinatarioPresidio destinatario = new DestinatarioPresidio(aluno);
		
		assertEquals("José Cataldo Curti Neto", destinatario.getNome());
		assertEquals("11111-111", destinatario.getCep());
		assertEquals("Bairro: Vila da Imutabilidade", destinatario.getBairro());
		assertEquals("Rua dos Testes Unitarios, 100", destinatario.getLocalidade());
		assertEquals("Estrada da Corrupção", destinatario.getComplemento());
		assertEquals("São Junit do Norte - SP", destinatario.getCidadeEstado());
		assertEquals("Mt: 111.111.111 - Raio: 1 - Cela: 10", destinatario.getIdentificacao());
		assertEquals("Penitenciária Orientada ao descaso público", destinatario.getInstituicao());
	}
	
	@Test
	public void destinatarioPresidiarioSemMatriculaTest() {

		Aluno aluno = buildAluno();
		Presidiario presidiario = buildPresidiario();
		
		presidiario.setRaio(1);
		presidiario.setCela(10);
		aluno.getCaracterizacoes().add(presidiario);

		DestinatarioPresidio destinatario = new DestinatarioPresidio(aluno);
		
		assertEquals("Raio: 1 - Cela: 10", destinatario.getIdentificacao());
	}
	
	@Test
	public void destinatarioPresidiarioSemRaioTest() {

		Aluno aluno = buildAluno();
		Presidiario presidiario = buildPresidiario();
		
		presidiario.setMatricula("111.111.111");
		presidiario.setCela(10);
		aluno.getCaracterizacoes().add(presidiario);

		DestinatarioPresidio destinatario = new DestinatarioPresidio(aluno);
		
		assertEquals("Mt: 111.111.111 - Cela: 10", destinatario.getIdentificacao());
	}
	
	@Test
	public void destinatarioPresidiarioSemCelaTest() {

		Aluno aluno = buildAluno();
		Presidiario presidiario = buildPresidiario();
		
		presidiario.setMatricula("111.111.111");
		presidiario.setRaio(1);
		aluno.getCaracterizacoes().add(presidiario);

		DestinatarioPresidio destinatario = new DestinatarioPresidio(aluno);
		
		assertEquals("Mt: 111.111.111 - Raio: 1", destinatario.getIdentificacao());
	}
	
	@Test
	public void destinatarioPresidiarioSemIdentificacaoTest() {

		Aluno aluno = buildAluno();

		Presidiario presidiario = buildPresidiario();
		aluno.getCaracterizacoes().add(presidiario);

		DestinatarioPresidio destinatario = new DestinatarioPresidio(aluno);
		
		assertEquals(null, destinatario.getIdentificacao());
	}
	
	@Test
	public void destinatarioPresidiarioSemNumeroTest() {

		Aluno aluno = buildAluno();
		Presidiario presidiario = buildPresidiario();

		presidiario.setPresidio(buildPresidio());
		aluno.getCaracterizacoes().add(presidiario);

		DestinatarioPresidio destinatario = new DestinatarioPresidio(aluno);
		
		assertEquals("Rua dos Testes Unitarios", destinatario.getLocalidade());
	}
	
	@Test
	public void destinatarioPresidiarioSemCEPTest() {

		Aluno aluno = buildAluno();
		Presidiario presidiario = buildPresidiario();

		presidiario.setPresidio(buildPresidio());
		aluno.getCaracterizacoes().add(presidiario);

		DestinatarioPresidio destinatario = new DestinatarioPresidio(aluno);
		
		assertEquals(null, destinatario.getCep());
	}
	
	@Test
	public void destinatarioPresidiarioSemBairroTest() {

		Aluno aluno = buildAluno();
		Presidiario presidiario = buildPresidiario();

		presidiario.setPresidio(buildPresidio());
		aluno.getCaracterizacoes().add(presidiario);

		DestinatarioPresidio destinatario = new DestinatarioPresidio(aluno);
		
		assertEquals(null, destinatario.getBairro());
	}
	
	@Test
	public void destinatarioPresidiarioSemComplementoTest() {

		Aluno aluno = buildAluno();
		Presidiario presidiario = buildPresidiario();

		presidiario.setPresidio(buildPresidio());
		aluno.getCaracterizacoes().add(presidiario);

		DestinatarioPresidio destinatario = new DestinatarioPresidio(aluno);
		
		assertEquals(null, destinatario.getComplemento());
	}

	private Aluno buildAluno() {
		return Aluno.builder().withNome("José Cataldo Curti Neto").build();
	}

	private Presidiario buildPresidiario() {

		return Presidiario.builder().withPresidio(buildPresidio()).build();
	}

	private Presidio buildPresidio() {

		return Presidio.builder().withNome("Penitenciária Orientada ao descaso público").withEndereco(buildEndereco())
				.build();
	}

	private Endereco buildEndereco() {
		return Endereco.builder().withLogradouro("Rua dos Testes Unitarios").withCidade("São Junit do Norte")
				.withEstado(buildEstado()).build();
	}

	private Estado buildEstado() {
		return Estado.builder().withUf("SP").build();
	}

}
