package br.com.escoladabiblia.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Presidiario;
import br.com.escoladabiblia.model.Presidio;
import br.com.escoladabiblia.repository.AlunoRepository;
import br.com.escoladabiblia.repository.PresidioRepository;

@Service
public class ImportacaoAlunosPresidiosServiceImpl implements ImportacaoAlunosPresidiosService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportacaoAlunosPresidiosService.class);

	private static final Pattern NOME_ALUNO_PATTERN = Pattern.compile("([A-ZÀ-Û .]+)");

	private static final Pattern RAIO_ALUNO_PATTERN = Pattern.compile(".*RAIO[-: ]*?(\\d+).*");

	private static final Pattern CELA_ALUNO_PATTERN = Pattern.compile(".*CELA[-: ]*?(\\d+).*");

	private Matcher matcher;

	@Autowired
	private PresidioRepository presidioRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public void importAlunosPresidiosFromXLSXFile(InputStream stream) throws IOException {

		try (Workbook workbook = new XSSFWorkbook(stream)) {

			for (Sheet sheet : workbook) {

				LOGGER.info("<<======= INSERINDO ALUNOS DO PRESÍDIO {} =======>>", sheet.getSheetName());

				for (Row row : sheet) {

					if (isRegistroAluno(row)) {

						Aluno aluno = new Aluno();
						
						Presidiario presidiario = createCaracterizacao(aluno, row);

						aluno.setNome(obterNomeAluno(row));

						aluno.getCaracterizacoes().add(presidiario);
						
						LOGGER.info("Inserindo aluno {} - Matricula: {} - Raio: {} - Cela: {} - Presidio: {}",
								aluno.getNome(), presidiario.getMatricula(), presidiario.getRaio(),
								presidiario.getCela(), presidiario.getPresidio().getNome());

						alunoRepository.save(aluno);
					}
				}
			}

		} catch (IOException e) {
			LOGGER.error("Erro inesperado ao processar o arquivo", e);
			throw e;

		} catch (Exception e) {
			LOGGER.error("Erro inesperado importar alunos do arquivo", e);
			throw e;
		}
	}

	private Presidiario createCaracterizacao(Aluno aluno, Row row) {

		Presidiario presidiario = new Presidiario();

		presidiario.setAluno(aluno);

		presidiario.setMatricula(getCellValue(row, 1));

		presidiario.setRaio(obterRaio(row));

		presidiario.setCela(obterCela(row));

		presidiario.setComplemento(getCellValue(row, 3));

		presidiario.setPresidio(obterPresidio(row));

		return presidiario;
	}

	private boolean isRegistroAluno(Row row) {

		String cell = getCellValue(row, 0);

		return !cell.equals("") && !cell.equals("NOME");
	}

	private String obterNomeAluno(Row row) {

		matcher = NOME_ALUNO_PATTERN.matcher(getCellValue(row, 0));

		return matcher.find() ? matcher.group() : null;
	}

	private Integer obterRaio(Row row) {

		matcher = RAIO_ALUNO_PATTERN.matcher(getCellValue(row, 2));
		
		return matcher.matches() ? Integer.valueOf(matcher.group(1)) : null;
	}

	private Integer obterCela(Row row) {

		matcher = CELA_ALUNO_PATTERN.matcher(getCellValue(row, 2));

		return matcher.matches() ? Integer.valueOf(matcher.group(1)) : null;
	}

	private Presidio obterPresidio(Row row) {

		return presidioRepository.findByNome(getCellValue(row, 4));
	}

	@SuppressWarnings("deprecation")
	private String getCellValue(Row row, int index) {

		if (row.getCell(index).getCellTypeEnum().equals(CellType.NUMERIC)) {

			return String.format("%1$,.0f", row.getCell(index).getNumericCellValue());
		}

		return row.getCell(index) != null ? row.getCell(index).toString().trim() : "";
	}

}
