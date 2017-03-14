package br.com.escoladabiblia.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "presidiarios")
public class Presidiario extends Caracterizacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Column(name = "matricula", length = 30, nullable = false)
	private String matricula;

	@NotNull
	@Column(name = "raio", nullable = false)
	private Integer raio;

	@NotNull
	@Column(name = "cela", nullable = false)
	private Integer cela;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "presidio_id", nullable = false, foreignKey = @ForeignKey(name = "presidio_fk"))
	private Presidio presidio;

	public Presidiario() {
		super.setAtiva(true);
		this.setPresidio(new Presidio());
		super.setDataRegistro(Calendar.getInstance());
		super.setTipo(TipoCaracterizacao.PRESIDIARIO);
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

	public Presidio getPresidio() {
		return presidio;
	}

	public void setPresidio(Presidio presidio) {
		this.presidio = presidio;
	}
	
	@Override
	@Transient
	public String getCaracterizacao() {
		return presidio.getNome();
	}

	@Override
	public String toString() {
		return "Presidiario [numeroMatricula=" + matricula + ", raio=" + raio + ", cela=" + cela + ", presidio="
				+ presidio + "]";
	}

}
