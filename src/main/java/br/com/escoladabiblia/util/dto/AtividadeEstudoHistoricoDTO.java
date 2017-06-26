package br.com.escoladabiblia.util.dto;

import java.io.Serializable;
import java.util.Calendar;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class AtividadeEstudoHistoricoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Long aluno;

	@NotNull
	private Long material;

	@Past
	@NotNull
	private Calendar dataEnvioEstudo;

	@Past
	@NotNull
	private Calendar dataRetornoEstudo;

	@NotNull
	@DecimalMin(value = "0")
	@DecimalMax(value = "10.00")
	private Float nota;

	private Calendar certificado;

	private Calendar biblia;

	public Long getAluno() {
		return aluno;
	}

	public void setAluno(Long aluno) {
		this.aluno = aluno;
	}

	public Long getMaterial() {
		return material;
	}

	public void setMaterial(Long material) {
		this.material = material;
	}

	public Calendar getDataEnvioEstudo() {
		return dataEnvioEstudo;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setDataEnvioEstudo(Calendar dataEnvioEstudo) {
		this.dataEnvioEstudo = dataEnvioEstudo;
	}

	public Calendar getDataRetornoEstudo() {
		return dataRetornoEstudo;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setDataRetornoEstudo(Calendar dataRetornoEstudo) {
		this.dataRetornoEstudo = dataRetornoEstudo;
	}

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}

	public Calendar getCertificado() {
		return certificado;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setCertificado(Calendar certificado) {
		this.certificado = certificado;
	}

	public Calendar getBiblia() {
		return biblia;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setBiblia(Calendar biblia) {
		this.biblia = biblia;
	}

}
