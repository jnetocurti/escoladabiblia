package br.com.escoladabiblia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.annotation.Generated;

@Entity
@Table(name = "parametros")
public class Parametros implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "exibir_remetente")
	private boolean exibirRemetente;

	@Column(name = "exibir_carimbo_impresso")
	private boolean exibirCarimboImpresso;

	@Column(name = "exibir_carimbo_mala_direta")
	private boolean exibirCarimboMalaDireta;

	@Generated("SparkTools")
	private Parametros(Builder builder) {
		this.id = builder.id;
		this.exibirRemetente = builder.exibirRemetente;
		this.exibirCarimboImpresso = builder.exibirCarimboImpresso;
		this.exibirCarimboMalaDireta = builder.exibirCarimboMalaDireta;
	}

	public Parametros() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isExibirRemetente() {
		return exibirRemetente;
	}

	public void setExibirRemetente(boolean exibirRemetente) {
		this.exibirRemetente = exibirRemetente;
	}

	public boolean isExibirCarimboImpresso() {
		return exibirCarimboImpresso;
	}

	public void setExibirCarimboImpresso(boolean exibirCarimboImpresso) {
		this.exibirCarimboImpresso = exibirCarimboImpresso;
	}

	public boolean isExibirCarimboMalaDireta() {
		return exibirCarimboMalaDireta;
	}

	public void setExibirCarimboMalaDireta(boolean exibirCarimboMalaDireta) {
		this.exibirCarimboMalaDireta = exibirCarimboMalaDireta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parametros other = (Parametros) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parametros [id=" + id + ", exibirRemetente=" + exibirRemetente + ", exibirCarimboImpresso="
				+ exibirCarimboImpresso + ", exibirCarimboMalaDireta=" + exibirCarimboMalaDireta + "]";
	}

	/**
	 * Creates builder to build {@link Parametros}.
	 * 
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Parametros}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private boolean exibirRemetente;
		private boolean exibirCarimboImpresso;
		private boolean exibirCarimboMalaDireta;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withExibirRemetente(boolean exibirRemetente) {
			this.exibirRemetente = exibirRemetente;
			return this;
		}

		public Builder withExibirCarimboImpresso(boolean exibirCarimboImpresso) {
			this.exibirCarimboImpresso = exibirCarimboImpresso;
			return this;
		}

		public Builder withExibirCarimboMalaDireta(boolean exibirCarimboMalaDireta) {
			this.exibirCarimboMalaDireta = exibirCarimboMalaDireta;
			return this;
		}

		public Parametros build() {
			return new Parametros(this);
		}
	}

}
