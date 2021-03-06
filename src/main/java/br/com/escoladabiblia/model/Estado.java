package br.com.escoladabiblia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.annotation.Generated;

@Entity
@Table(name = "estados")
public class Estado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Size(min = 2, max = 2)
	@Column(name = "uf", length = 2, nullable = false)
	private String uf;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;

	public Estado() {
	}

	@Generated("SparkTools")
	private Estado(Builder builder) {
		this.id = builder.id;
		this.uf = builder.uf;
		this.descricao = builder.descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		Estado other = (Estado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estado [uf=" + uf + "]";
	}

	/**
	 * Creates builder to build {@link Estado}.
	 * 
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Estado}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private String uf;
		private String descricao;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withUf(String uf) {
			this.uf = uf;
			return this;
		}

		public Builder withDescricao(String descricao) {
			this.descricao = descricao;
			return this;
		}

		public Estado build() {
			return new Estado(this);
		}
	}

}
