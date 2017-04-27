package br.com.escoladabiblia.util.dto;

import java.io.Serializable;

public class AlunoComumDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4884621784190595098L;

	private Long id;

	private String nome;

	public AlunoComumDTO() {
	}

	public AlunoComumDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
