package br.com.escoladabiblia.util.dto;

import java.io.Serializable;
import java.util.Calendar;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class AtividadeEstudoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Long idAluno;

	@NotNull
	private Calendar dataProximoEnvio;

	@NotNull
	private Long idMaterial;

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public Calendar getDataProximoEnvio() {
		return dataProximoEnvio;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setDataProximoEnvio(Calendar dataProximoEnvio) {
		this.dataProximoEnvio = dataProximoEnvio;
	}

	public Long getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Long isMaterial) {
		this.idMaterial = isMaterial;
	}

}
