package br.com.escoladabiblia.util.impressao;

import java.io.InputStream;

import org.junit.Test;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class JasperUtilTest {

	@Test
	public void test() throws JRException {

		InputStream jasper = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jasper/envelope-medio.jasper");

		JasperPrint jasperPrintPDF = JasperFillManager.fillReport(jasper, null, new JREmptyDataSource());

		JasperExportManager.exportReportToPdfFile(jasperPrintPDF, "target/teste.pdf");

	}

}
