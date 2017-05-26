package br.com.escoladabiblia.util.impressao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
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
			throws JRException {

		final InputStream jasper = Thread.currentThread().getContextClassLoader().getResourceAsStream(jasperFile);

		return JasperFillManager.fillReport(jasper, null, new JRBeanCollectionDataSource(destinatarios));
	}
	
	public static JasperPrint getJasperPrintAtividadesPostagem(final List<MateriaisPostagem> materiaisPostagem,
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

}
