package br.com.escoladabiblia.util.dto;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class AtividadeEstudoEdicaoDTO {

	@NotNull
	private Long id;

	@NotNull
	private Calendar dataRetornoEstudo;

	@NotNull
	private Float nota;

	private boolean certificado;

	private boolean biblia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isCertificado() {
		return certificado;
	}

	public void setCertificado(boolean certificado) {
		this.certificado = certificado;
	}

	public boolean isBiblia() {
		return biblia;
	}

	public void setBiblia(boolean biblia) {
		this.biblia = biblia;
	}

}
