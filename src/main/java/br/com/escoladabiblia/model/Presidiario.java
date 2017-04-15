package br.com.escoladabiblia.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.annotation.Generated;

@Entity
@Table(name = "presidiarios")
@PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "caracterizacao_fk"))
public class Presidiario extends Caracterizacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "matricula", length = 30)
	private String matricula;

	@Column(name = "raio")
	private Integer raio;

	@Column(name = "cela")
	private Integer cela;

	@Column(name = "complemento", length = 50)
	private String complemento;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "presidio_id", nullable = false, foreignKey = @ForeignKey(name = "presidio_fk"))
	private Presidio presidio;

	public Presidiario() {
		super.setAtiva(true);
		super.setDataRegistro(Calendar.getInstance());
		super.setTipo(TipoCaracterizacao.PRESIDIARIO);
	}

	@Generated("SparkTools")
	private Presidiario(Builder builder) {
		this.matricula = builder.matricula;
		this.raio = builder.raio;
		this.cela = builder.cela;
		this.complemento = builder.complemento;
		this.presidio = builder.presidio;
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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Presidio getPresidio() {
		return presidio;
	}

	public void setPresidio(Presidio presidio) {
		this.presidio = presidio;
	}

	@Override
	public String toString() {
		return "Presidiario [numeroMatricula=" + matricula + ", raio=" + raio + ", cela=" + cela + ", presidio="
				+ presidio + "]";
	}

	/**
	 * Creates builder to build {@link Presidiario}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Presidiario}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String matricula;
		private Integer raio;
		private Integer cela;
		private String complemento;
		private Presidio presidio;

		private Builder() {
		}

		public Builder withMatricula(String matricula) {
			this.matricula = matricula;
			return this;
		}

		public Builder withRaio(Integer raio) {
			this.raio = raio;
			return this;
		}

		public Builder withCela(Integer cela) {
			this.cela = cela;
			return this;
		}

		public Builder withComplemento(String complemento) {
			this.complemento = complemento;
			return this;
		}

		public Builder withPresidio(Presidio presidio) {
			this.presidio = presidio;
			return this;
		}

		public Presidiario build() {
			return new Presidiario(this);
		}
	}

}
