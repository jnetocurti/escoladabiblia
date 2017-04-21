package br.com.escoladabiblia.util.dto;

import java.io.Serializable;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.TipoEnvelope;

public class AtividadeEstudoImpressaoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Aluno aluno;

	private final TipoEnvelope tipoEnvelope;

	public AtividadeEstudoImpressaoDTO(Aluno aluno, TipoEnvelope tipoEnvelope) {
		this.aluno = aluno;
		this.tipoEnvelope = tipoEnvelope;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public TipoEnvelope getTipoEnvelope() {
		return tipoEnvelope;
	}

}
