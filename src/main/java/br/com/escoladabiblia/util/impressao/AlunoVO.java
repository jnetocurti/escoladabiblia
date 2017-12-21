package br.com.escoladabiblia.util.impressao;

import java.io.Serializable;

public class AlunoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;

	public AlunoVO(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
