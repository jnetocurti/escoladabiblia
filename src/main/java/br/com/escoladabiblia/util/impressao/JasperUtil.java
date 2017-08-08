package br.com.escoladabiblia.util.impressao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JasperUtil {

	private JasperUtil() {
	}

	public static byte[] exportReport(final List<JasperPrint> jasperPrints) throws JRException {

		final ByteArrayOutputStream out = new ByteArrayOutputStream();

		final JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));

		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));

		exporter.exportReport();

		return out.toByteArray();
	}

	public static byte[] exportReport(final JasperPrint jasperPrint) throws JRException {

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	public static String getFilePath(String file) throws IOException {

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(
				Thread.currentThread().getContextClassLoader());

		Resource resource = resolver.getResource(file);

		return resource.getURI().toString();
	}

}
