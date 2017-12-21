package br.com.escoladabiblia.util.impressao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.escoladabiblia.util.impressao.AlunosStatusPorPeriodoVO.Periodo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioAlunosStatusPorPeriodoTest {
	
	@Test
	public void relatorioAlunosAtivosPorPeriodo() throws JRException {

		InputStream jasper = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jasper/relatorio-alunos-ativos-periodo.jasper");

		JasperPrint jasperPrintPDF = JasperFillManager.fillReport(jasper, null,
				new JRBeanCollectionDataSource(getAlunoStatusPeriodo()));

		JasperExportManager.exportReportToPdfFile(jasperPrintPDF, "target/relatorio-alunos-ativos-periodo.pdf");
	}

	@Test
	public void relatorioAlunosInativosPorPeriodo() throws JRException {

		InputStream jasper = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jasper/relatorio-alunos-inativos-periodo.jasper");

		JasperPrint jasperPrintPDF = JasperFillManager.fillReport(jasper, null,
				new JRBeanCollectionDataSource(getAlunoStatusPeriodo()));

		JasperExportManager.exportReportToPdfFile(jasperPrintPDF, "target/relatorio-alunos-inativos-periodo.pdf");
	}
	
	private List<AlunosStatusPorPeriodoVO> getAlunoStatusPeriodo() {

		List<AlunosStatusPorPeriodoVO> lista = new ArrayList<>();

		for (Periodo periodo : Periodo.values()) {

			AlunosStatusPorPeriodoVO vo = new AlunosStatusPorPeriodoVO(periodo);

			vo.getAlunos()
					.addAll(Arrays.asList(
							new AlunoVO("ADALBERTO DE SOUZA FERREIRA"),
							new AlunoVO("ADALTO DOS SANTOS"),
							new AlunoVO("GUSTAVO EDUARDO MARTINS FRANÇA"),
							new AlunoVO("ISABELA CRISTINA GONÇALVES DE OLIVEIRA")));

			lista.add(vo);
		}

		return lista;
	}

}
