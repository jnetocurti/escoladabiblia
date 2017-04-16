package br.com.escoladabiblia.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escoladabiblia.model.Endereco;
import br.com.escoladabiblia.model.Presidio;
import br.com.escoladabiblia.repository.EstadoRepository;
import br.com.escoladabiblia.repository.PresidioRepository;

@Service
public class ImportacaoPresidiosServiceImpl implements ImportacaoPresidiosService {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private PresidioRepository presidioRepository;

	@Override
	public void importPresidiosFromXLSXFile(InputStream stream) throws IOException {

		try (Workbook workbook = new XSSFWorkbook(stream)) {

			Sheet firstSheet = workbook.getSheetAt(0);

			for (Row row : firstSheet) {

				if (!row.getCell(0).toString().equals("NOME") && !row.getCell(0).toString().equals("")) {

					Presidio presidio = Presidio.builder().withNome(row.getCell(0).toString())
							.withEndereco(createEndereco(row)).build();

					presidioRepository.save(presidio);
				}
			}

		} catch (IOException e) {
			throw e;
		}
	}

	private Endereco createEndereco(Row row) {

		return Endereco.builder()
				.withLogradouro(obterLogradouro(row.getCell(1)))
				.withNumero(obterNumero(row.getCell(1)))
				.withBairro(row.getCell(2).toString().trim())
				.withComplemento(row.getCell(3).toString().trim())
				.withCidade(obterCidade(row.getCell(4)))
				.withEstado(estadoRepository.findByUf(obterUf(row.getCell(4))))
				.withCep(row.getCell(5).toString().trim())
				.build();
	}

	private String obterLogradouro(Cell cell) {
		return cell.toString().split(",")[0].trim();
	}

	private Integer obterNumero(Cell cell) {
		String[] dados = cell.toString().split(",");
		if (dados.length > 1) {
			return Integer.valueOf(dados[1].trim());
		}
		return 0;
	}

	private String obterCidade(Cell cell) {
		return cell.toString().split("-")[0].trim();
	}

	private String obterUf(Cell cell) {
		return cell.toString().split("-")[1].trim();
	}

}
