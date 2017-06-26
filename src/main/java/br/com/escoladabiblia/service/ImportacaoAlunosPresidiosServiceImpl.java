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
import org.springframework.transaction.annotation.Transactional;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.ControleImportacao;
import br.com.escoladabiblia.model.Presidiario;
import br.com.escoladabiblia.model.Presidio;
import br.com.escoladabiblia.model.Sexo;
import br.com.escoladabiblia.model.TipoImportacao;
import br.com.escoladabiblia.repository.AlunoRepository;
import br.com.escoladabiblia.repository.ControleImportacaoRepository;
import br.com.escoladabiblia.repository.PresidioRepository;
import br.com.escoladabiblia.util.exception.BusinessException;

/**
 * @deprecated escoladabiblia 1.0 - O processo de importação dos dados legados
 *             atualmente salvos em planílhas do Excel não mais existirá nas
 *             próximas versões do sistema, tendo apenas o propósito específico
 *             de facilitar o cadastro/setup destas informações.
 */
@Service
public class ImportacaoAlunosPresidiosServiceImpl implements ImportacaoAlunosPresidiosService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportacaoAlunosPresidiosService.class);

	private static final String FILE_NAME_ALUNAS_PRESIDIOS = "1- Alunas presídios (mulheres) - novo.xlsx";

	private static final String FILE_NAME_ALUNOS_PRESIDIOS = "2- Alunos presídios (homens) - novo.xlsx";

	private static final Pattern NOME_ALUNO_PATTERN = Pattern.compile("([A-ZÀ-Û .]+)");

	private static final Pattern RAIO_ALUNO_PATTERN = Pattern.compile(".*RAIO[-: ]*?(\\d+).*");

	private static final Pattern CELA_ALUNO_PATTERN = Pattern.compile(".*CELA[-: ]*?(\\d+).*");

	private Matcher matcher;

	@Autowired
	private PresidioRepository presidioRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private ControleImportacaoRepository controleImportacaoRepository;

	@Override
	@Transactional
	public void importAlunosPresidiosFromXLSXFile(InputStream stream, String fileName)
			throws IOException, BusinessException {

		final Sexo sexo = obterGeneroPeloNomeDoArquivo(fileName);

		final TipoImportacao tipoImportacao = obterTipoImportacao(sexo);

		validarImportacao(tipoImportacao);

		try (Workbook workbook = new XSSFWorkbook(stream)) {

			for (Sheet sheet : workbook) {

				LOGGER.info("<<======= INSERINDO ALUNOS DO PRESÍDIO {} =======>>", sheet.getSheetName());

				for (Row row : sheet) {

					if (isRegistroAluno(row)) {

						Aluno aluno = Aluno.builder().withNome(obterNomeAluno(row)).withSexo(sexo).build();

						Presidiario presidiario = createCaracterizacao(aluno, row);

						aluno.getCaracterizacoes().add(presidiario);

						LOGGER.info("Inserindo aluno {} - Matricula: {} - Raio: {} - Cela: {}", aluno.getNome(),
								presidiario.getMatricula(), presidiario.getRaio(), presidiario.getCela());

						alunoRepository.save(aluno);
					}
				}
			}

			controleImportacaoRepository.save(ControleImportacao.builder().withTipoImportacao(tipoImportacao).build());

		} catch (IOException e) {
			LOGGER.error("Erro inesperado ao processar o arquivo", e);
			throw e;

		} catch (Exception e) {
			LOGGER.error("Erro inesperado importar alunos do arquivo", e);
			throw e;
		}
	}

	private Sexo obterGeneroPeloNomeDoArquivo(String fileName) throws BusinessException {

		if (fileName.equals(FILE_NAME_ALUNAS_PRESIDIOS)) {

			return Sexo.F;

		} else if (fileName.equals(FILE_NAME_ALUNOS_PRESIDIOS)) {

			return Sexo.M;

		} else {

			throw new BusinessException("erro.aluno.presidio.importacao.nome.arquivo");
		}
	}

	private TipoImportacao obterTipoImportacao(Sexo sexo) {

		return sexo.equals(Sexo.F) ? TipoImportacao.ALUNAS_PRESIDIOS : TipoImportacao.ALUNOS_PRESIDIOS;
	}

	private void validarImportacao(TipoImportacao tipoImportacao) throws BusinessException {

		if (controleImportacaoRepository.existsByTipoImportacao(tipoImportacao)) {

			throw new BusinessException("erro.aluno.presidio.importacao.tipo.importado");
		}
	}

	private Presidiario createCaracterizacao(Aluno aluno, Row row) {
		
		return Presidiario.builder()
				.withAluno(aluno)
				.withMatricula(getCellValue(row, 1))
				.withRaio(obterRaio(row))
				.withCela(obterCela(row))
				.withComplemento(getCellValue(row, 3))
				.withPresidio(obterPresidio(row)).build();
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

	private String getCellValue(Row row, int index) {

		if (row.getCell(index) != null) {

			if (row.getCell(index).getCellTypeEnum().equals(CellType.NUMERIC)) {

				return String.format("%1$,.0f", row.getCell(index).getNumericCellValue());
			}
		}

		return row.getCell(index) != null ? row.getCell(index).toString().trim() : "";
	}

}
