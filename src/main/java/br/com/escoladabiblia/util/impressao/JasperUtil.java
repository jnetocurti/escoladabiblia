package br.com.escoladabiblia.util.impressao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JasperUtil {

	private JasperUtil() {
	}

	public static JasperPrint getJasperPrintEnvelopes(final List<Destinatario> destinatarios, String jasperFile)
			throws JRException, IOException {

		final InputStream jasper = Thread.currentThread().getContextClassLoader().getResourceAsStream(jasperFile);

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("carimboImg", getFilePath("images/carimbo.png"));

		parameters.put("remetenteImg", getFilePath("images/remetente.png"));

		return JasperFillManager.fillReport(jasper, parameters, new JRBeanCollectionDataSource(destinatarios));
	}
	
	public static JasperPrint getJasperPrintRelatorioPostagem(final List<MateriaisPostagem> materiaisPostagem,
			Map<String, Object> parameters, String jasperFile) throws JRException {

		final InputStream jasper = Thread.currentThread().getContextClassLoader().getResourceAsStream(jasperFile);

		return JasperFillManager.fillReport(jasper, parameters, new JRBeanCollectionDataSource(materiaisPostagem));
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
