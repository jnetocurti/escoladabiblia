package br.com.escoladabiblia.util.impressao;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioPostagemTest {

	@Test
	public void subRelatorioCertificadosPostagemTest() throws JRException {

		InputStream jasper = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jasper/sub-certificados-postagem.jasper");

		JasperPrint jasperPrintPDF = JasperFillManager.fillReport(jasper, null,
				new JRBeanCollectionDataSource(getCertificados()));

		JasperExportManager.exportReportToPdfFile(jasperPrintPDF, "target/sub-certificados-postagem.pdf");
	}

	@Test
	public void relatorioPostagemTest() throws JRException {

		InputStream jasper = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jasper/relatorio-postagem.jasper");

		String subReportPath = Thread.currentThread().getContextClassLoader()
				.getResource("jasper/sub-certificados-postagem.jasper").getPath();

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("dataPostagem", new Date());

		parameters.put("subReportPath", subReportPath);

		parameters.put("certificados", getCertificados());
		
		parameters.put("presidiariosNov", 20L);
		
		parameters.put("presidiariosSeq", 60L);
		
		parameters.put("comunsNov", 2L);
		
		parameters.put("comunsSeq", 12L);

		JasperPrint jasperPrintPDF = JasperFillManager.fillReport(jasper, parameters,
				new JRBeanCollectionDataSource(Arrays.asList(new MateriaisPostagem("INTRODUTÓRIA", 1L),
						new MateriaisPostagem("CONHECENDO JESUS", 10L),
						new MateriaisPostagem("NASCIDOS DA ÁGUA E DO ESPÍRITO", 10L),
						new MateriaisPostagem("DEUS TEM FALADO", 50L),
						new MateriaisPostagem("ESTAS SÃO AS BOAS NOVAS", 70L),
						new MateriaisPostagem("O QUE A BÍBLIA DIZ 11", 10L),
						new MateriaisPostagem("A IGREJA BÍBLICA 13", 80L),
						new MateriaisPostagem("CRISTIANISMO EM AÇÃO 13", 100L))));

		JasperExportManager.exportReportToPdfFile(jasperPrintPDF, "target/relatorio_postagem.pdf");

	}

	private List<CertificadosPostagemVO> getCertificados() {

		CertificadosPostagemVO certificado1 = new CertificadosPostagemVO("DEUS TEM FALADO", 2L);

		certificado1.getAlunos()
				.addAll(Arrays.asList(new AlunosCertificadosPostagemVO("ADALBERTO DE SOUZA FERREIRA"),
						new AlunosCertificadosPostagemVO("ADALTO DOS SANTOS"),
						new AlunosCertificadosPostagemVO("GUSTAVO EDUARDO MARTINS FRANÇA"),
						new AlunosCertificadosPostagemVO("ISABELA CRISTINA GONÇALVES DE OLIVEIRA")));

		CertificadosPostagemVO certificado2 = new CertificadosPostagemVO("NASCIDOS DA ÁGUA E DO ESPÍRITO", 12L);

		certificado2.getAlunos()
				.addAll(Arrays.asList(new AlunosCertificadosPostagemVO("ADRIANA CRISTINA ROSA ZACHARIAS"),
						new AlunosCertificadosPostagemVO("ADRIANA HENRIQUE MIRANDA")));

		CertificadosPostagemVO certificado3 = new CertificadosPostagemVO("O QUE A BÍBLIA DIZ", 5L);

		certificado3.getAlunos().addAll(Arrays.asList(new AlunosCertificadosPostagemVO("CÉLIO FERREIRA DE MATOS"),
				new AlunosCertificadosPostagemVO("CÍCERO DE ASSIS VIEIRA DA SILVA")));

		CertificadosPostagemVO certificado4 = new CertificadosPostagemVO("CRISTIANISMO EM AÇÃO", 25L);

		certificado4.getAlunos()
				.addAll(Arrays.asList(new AlunosCertificadosPostagemVO("EWERTON FELIX DA PAIXÃO MARYAMA"),
						new AlunosCertificadosPostagemVO("FERNANDO CONCEIÇÃO FERNANDES")));

		return Arrays.asList(certificado1, certificado2, certificado3, certificado4);
	}

}
