package br.com.escoladabiblia.util.impressao;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Endereco;
import br.com.escoladabiblia.model.Estado;
import br.com.escoladabiblia.model.Presidiario;
import br.com.escoladabiblia.model.Presidio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperEnvelopeOficioFrenteTest {

	@Test
	public void jasperDadosCompletosTest() throws JRException {

		Aluno aluno = Aluno.builder().withNome("JOICE CRISTINA DO CARMO FERREIRA DA SILVA").build();

		aluno.getCaracterizacoes()
				.add(Presidiario.builder()
						.withMatricula("1.022.849")
						.withRaio(8)
						.withCela(363)
						.withPresidio(Presidio.builder()
								.withEndereco(Endereco.builder()
										.withLogradouro("Rodovia Comandante João Ribeiro de Barros")
										.withComplemento("Km 634 + 240 m - Via Acesso ao município Caiuá - Km 1")
										.withNumero(10000)
										.withBairro("Jardim Novo Rio Claro")
										.withCidade("Presidente Venceslau")
										.withCep("16360-000")
										.withEstado(Estado.builder()
												.withUf("SP")
												.build())
										.build())
								.build())
						.build());

		InputStream jasper = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jasper/envelope-oficio-frente.jasper");
		
		Map<String, Object> parameters = getParameters();

		JasperPrint jasperPrintPDF = JasperFillManager.fillReport(jasper, parameters,
				new JRBeanCollectionDataSource(Arrays.asList(new PresidiarioDestinatario(aluno))));

		JasperExportManager.exportReportToPdfFile(jasperPrintPDF, "target/envelope-oficio-dados-completos-frente.pdf");

	}

	@Test
	public void jasperDadosIncompletosTest() throws JRException {

		Aluno aluno = Aluno.builder().withNome("JOICE CRISTINA DO CARMO FERREIRA DA SILVA").build();

		aluno.getCaracterizacoes()
				.add(Presidiario.builder()
						.withPresidio(Presidio.builder()
								.withEndereco(Endereco.builder()
										.withLogradouro("Rodovia Comandante João Ribeiro de Barros")
										.withNumero(10000)
										.withCidade("Presidente Venceslau")
										.withEstado(Estado.builder()
												.withUf("SP")
												.build())
										.build())
								.build())
						.build());

		InputStream jasper = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jasper/envelope-oficio-frente.jasper");
		
		Map<String, Object> parameters = getParameters();

		JasperPrint jasperPrintPDF = JasperFillManager.fillReport(jasper, parameters,
				new JRBeanCollectionDataSource(Arrays.asList(new PresidiarioDestinatario(aluno))));

		JasperExportManager.exportReportToPdfFile(jasperPrintPDF, "target/envelope-oficio-dados-incompletos-frente.pdf");

	}
	
	private Map<String, Object> getParameters() {
		
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("carimboImg",
				Thread.currentThread().getContextClassLoader().getResource("images/carimbo-impresso.png").getPath());

		parameters.put("remetenteImg",
				Thread.currentThread().getContextClassLoader().getResource("images/remetente.png").getPath());
		
		return parameters;
	}

}
