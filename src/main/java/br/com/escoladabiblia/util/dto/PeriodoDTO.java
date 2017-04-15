package br.com.escoladabiblia.util.dto;

import java.io.Serializable;
import java.util.Calendar;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class PeriodoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Calendar dataDe;

	@NotNull
	private Calendar dataAte;

	public PeriodoDTO() {
	}

	public PeriodoDTO(Calendar dataDe, Calendar dataAte) {
		this.dataDe = dataDe;
		this.dataAte = dataAte;
	}

	public Calendar getDataDe() {
		return dataDe;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setDataDe(Calendar dataDe) {
		this.dataDe = dataDe;
	}

	public Calendar getDataAte() {
		return dataAte;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setDataAte(Calendar dataAte) {
		this.dataAte = dataAte;
	}

}
