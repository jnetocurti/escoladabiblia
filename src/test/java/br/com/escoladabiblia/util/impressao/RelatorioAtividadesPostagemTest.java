package br.com.escoladabiblia.util.impressao;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioAtividadesPostagemTest {

	@Test
	public void relatorioAtividadesPostagemTest() throws JRException {

		InputStream jasper = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jasper/relatorio_postagem.jasper");

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("DATA_POSTAGEM", new Date());
		
		parameters.put("QTD_BIBLIAS", 12L);

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

}
