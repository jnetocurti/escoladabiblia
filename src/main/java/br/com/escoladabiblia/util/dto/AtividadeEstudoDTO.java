package br.com.escoladabiblia.util.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class AtividadeEstudoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Long idAluno;

	@NotNull
	private Long idPostagem;

	@NotNull
	private Long idMaterial;

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public Long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public Long getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Long isMaterial) {
		this.idMaterial = isMaterial;
	}

}
