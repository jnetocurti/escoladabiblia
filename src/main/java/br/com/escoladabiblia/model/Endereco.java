package br.com.escoladabiblia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import javax.annotation.Generated;

@Entity
@Table(name = "enderecos")
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false, foreignKey = @ForeignKey(name = "estado_fk"))
	private Estado estado;

	@NotBlank
	@Size(min = 3, max = 100)
	@Column(name = "cidade", length = 100, nullable = false)
	private String cidade;

	@NotBlank
	@Size(min = 3, max = 100)
	@Column(name = "logradouro", length = 100, nullable = false)
	private String logradouro;

	@NotNull
	@Column(name = "numero", length = 8, nullable = false, columnDefinition = "integer default 0")
	private Integer numero;

	@Column(name = "bairro", length = 100)
	private String bairro;

	@Column(name = "complemento", length = 100)
	private String complemento;

	@Size(min = 9, max = 9)
	@Column(name = "cep", length = 9)
	private String cep;

	public Endereco() {
	}

	@Generated("SparkTools")
	private Endereco(Builder builder) {
		this.id = builder.id;
		this.estado = builder.estado;
		this.cidade = builder.cidade;
		this.logradouro = builder.logradouro;
		this.numero = builder.numero;
		this.bairro = builder.bairro;
		this.complemento = builder.complemento;
		this.cep = builder.cep;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [estado=" + estado + ", cidade=" + cidade + ", logradouro=" + logradouro + ", numero=" + numero
				+ ", bairro=" + bairro + "]";
	}

	/**
	 * Creates builder to build {@link Endereco}.
	 * 
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Endereco}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private Estado estado;
		private String cidade;
		private String logradouro;
		private Integer numero;
		private String bairro;
		private String complemento;
		private String cep;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withEstado(Estado estado) {
			this.estado = estado;
			return this;
		}

		public Builder withCidade(String cidade) {
			this.cidade = cidade;
			return this;
		}

		public Builder withLogradouro(String logradouro) {
			this.logradouro = logradouro;
			return this;
		}

		public Builder withNumero(Integer numero) {
			this.numero = numero;
			return this;
		}

		public Builder withBairro(String bairro) {
			this.bairro = bairro;
			return this;
		}

		public Builder withComplemento(String complemento) {
			this.complemento = complemento;
			return this;
		}

		public Builder withCep(String cep) {
			this.cep = cep;
			return this;
		}

		public Endereco build() {
			return new Endereco(this);
		}
	}

}
