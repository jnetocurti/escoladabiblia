package br.com.escoladabiblia.util.dto;

import java.io.Serializable;

public class AlunoPresidioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4884621784190595098L;

	private Long id;

	private String nome;

	private String matricula;

	private Integer raio;

	private Integer cela;

	private String presidio;

	public AlunoPresidioDTO() {
	}

	public AlunoPresidioDTO(Long id, String nome, String matricula, Integer raio, Integer cela, String presidio) {
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.raio = raio;
		this.cela = cela;
		this.presidio = presidio;
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getRaio() {
		return raio;
	}

	public void setRaio(Integer raio) {
		this.raio = raio;
	}

	public Integer getCela() {
		return cela;
	}

	public void setCela(Integer cela) {
		this.cela = cela;
	}

	public String getPresidio() {
		return presidio;
	}

	public void setPresidio(String presidio) {
		this.presidio = presidio;
	}

}
