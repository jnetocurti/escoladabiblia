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
	
	private final Long biblias;

	public AtividadeEstudoImpressaoDTO(Aluno aluno, TipoEnvelope tipoEnvelope, Long biblias) {
		this.aluno = aluno;
		this.tipoEnvelope = tipoEnvelope;
		this.biblias = biblias;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public TipoEnvelope getTipoEnvelope() {
		if (biblias > 0) {
			return TipoEnvelope.ENVELOPE_185_X_248;
		}
		return tipoEnvelope;
	}

}
