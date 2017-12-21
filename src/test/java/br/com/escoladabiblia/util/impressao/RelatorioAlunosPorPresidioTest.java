package br.com.escoladabiblia.util.impressao;

import java.io.InputStream;
import java.util.Arrays;

import org.junit.Test;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioAlunosPorPresidioTest {

	@Test
	public void relatorioAlunosPorPresidioTest() throws JRException {

		InputStream jasper = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jasper/relatorio-alunos-por-presidio.jasper");

		JasperPrint jasperPrintPDF = JasperFillManager.fillReport(jasper, null,
				new JRBeanCollectionDataSource(Arrays.asList(new AlunosPorPresidioVO("PRESÍDIO 1", 10L),
						new AlunosPorPresidioVO("PRESÍDIO 2", 10L),
						new AlunosPorPresidioVO("PRESÍDIO 3", 10L),
						new AlunosPorPresidioVO("PRESÍDIO 4", 10L),
						new AlunosPorPresidioVO("PRESÍDIO 5", 10L))));
		
		JasperExportManager.exportReportToPdfFile(jasperPrintPDF, "target/relatorio-alunos-por-presidio.pdf");
	}

}
