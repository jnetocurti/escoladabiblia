package br.com.escoladabiblia.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import javax.annotation.Generated;

@Entity
@Table(name = "presidios")
public class Presidio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@NotNull
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "endereco_id", nullable = false, foreignKey = @ForeignKey(name = "endereco_fk"))
	private @Valid Endereco endereco;

	public Presidio() {
	}

	@Generated("SparkTools")
	private Presidio(Builder builder) {
		this.id = builder.id;
		this.nome = builder.nome;
		this.endereco = builder.endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		Presidio other = (Presidio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Presidio [nome=" + nome + "]";
	}

	/**
	 * Creates builder to build {@link Presidio}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Presidio}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private String nome;
		private Endereco endereco;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withEndereco(Endereco endereco) {
			this.endereco = endereco;
			return this;
		}

		public Presidio build() {
			return new Presidio(this);
		}
	}

}
