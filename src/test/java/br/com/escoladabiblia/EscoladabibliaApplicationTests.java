package br.com.escoladabiblia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class EscoladabibliaApplicationTests {

	// TODO criar novas classes de teste para inserção de dados a partir do documento excel
	@Test
	public void contextLoads() {

		String excelFilePath = "C:\\Users\\Neto\\Desktop\\matriculados.xlsx";

		try (Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(excelFilePath)))) {

			Sheet firstSheet = workbook.getSheetAt(0);

			Iterator<Row> iterator = firstSheet.iterator();

			System.out.println(iterator.next().getCell(0));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
