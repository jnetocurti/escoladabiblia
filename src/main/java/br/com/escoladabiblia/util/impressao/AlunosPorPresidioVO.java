package br.com.escoladabiblia.util.impressao;

import java.io.Serializable;

public class AlunosPorPresidioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5306686041221029220L;

	private final String nomePresidio;

	private final Long quantidadeAlunos;

	public AlunosPorPresidioVO(String nomePresidio, Long quantidadeAlunos) {
		this.nomePresidio = nomePresidio;
		this.quantidadeAlunos = quantidadeAlunos;
	}

	public String getNomePresidio() {
		return nomePresidio;
	}

	public Long getQuantidadeAlunos() {
		return quantidadeAlunos;
	}

}
