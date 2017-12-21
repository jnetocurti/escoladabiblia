package br.com.escoladabiblia.util.impressao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlunosStatusPorPeriodoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Periodo periodo;

	private final List<AlunoVO> alunos = new ArrayList<>();

	public AlunosStatusPorPeriodoVO(Periodo periodo) {
		this.periodo = periodo;
	}

	public String getPeriodoDescricao() {
		return periodo.descricao;
	}

	public Calendar getPeriodoInicio() {
		return periodo.getDataInicial();
	}

	public Calendar getPeriodoFinal() {
		return periodo.getDataFinal();
	}

	public int getNumeroAlunos() {
		return alunos.size();
	}

	public List<AlunoVO> getAlunos() {
		return alunos;
	}

	public enum Periodo {

		ULTIMOS_TRES_MESES("ÚLTIMOS TRÊS MESES", 3), 
		ULTIMOS_SEIS_MESES("ÚLTIMOS SEIS MESES", 6);

		private final String descricao;

		private final int numeroMeses;

		private Periodo(String descricao, int numeroMeses) {
			this.descricao = descricao;
			this.numeroMeses = numeroMeses;
		}

		Calendar getDataInicial() {
			Calendar dataRetroativa = Calendar.getInstance();
			dataRetroativa.add(Calendar.MONTH, -this.numeroMeses);
			dataRetroativa.set(Calendar.DAY_OF_MONTH, 1);
			return dataRetroativa;
		}

		Calendar getDataFinal() {
			return Calendar.getInstance();
		}
	}

}
