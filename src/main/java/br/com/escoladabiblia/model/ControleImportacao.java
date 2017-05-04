package br.com.escoladabiblia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.annotation.Generated;

@Entity
@Table(name = "controle_importacoes", uniqueConstraints = @UniqueConstraint(name = "tipo_importacao_uk", columnNames = { "tipo_importacao" }))
public class ControleImportacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_importacao", length = 20, nullable = false)
	private TipoImportacao tipoImportacao;

	public ControleImportacao() {
	}

	@Generated("SparkTools")
	private ControleImportacao(Builder builder) {
		this.id = builder.id;
		this.tipoImportacao = builder.tipoImportacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoImportacao getTipoImportacao() {
		return tipoImportacao;
	}

	public void setTipoImportacao(TipoImportacao tipoImportacao) {
		this.tipoImportacao = tipoImportacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipoImportacao == null) ? 0 : tipoImportacao.hashCode());
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
		ControleImportacao other = (ControleImportacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipoImportacao != other.tipoImportacao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ControleImportacao [tipoImportacao=" + tipoImportacao + "]";
	}

	/**
	 * Creates builder to build {@link ControleImportacao}.
	 * 
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link ControleImportacao}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private TipoImportacao tipoImportacao;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withTipoImportacao(TipoImportacao tipoImportacao) {
			this.tipoImportacao = tipoImportacao;
			return this;
		}

		public ControleImportacao build() {
			return new ControleImportacao(this);
		}
	}

}
