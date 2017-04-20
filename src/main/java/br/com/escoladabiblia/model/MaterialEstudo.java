package br.com.escoladabiblia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;
import javax.annotation.Generated;

@Entity
@Table(name = "materiais_estudo", uniqueConstraints = @UniqueConstraint(name = "numero_ordem_uk", columnNames = "numero_ordem"))
public class MaterialEstudo implements Serializable {

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

	@Column(name = "numero_ordem")
	private Integer numeroOrdem;

	@Column(name = "envia_certificado", nullable = false)
	private boolean enviaCertificado;

	@Column(name = "qtd_estoque")
	private Integer qtdEstoque;

	public MaterialEstudo() {
	}

	@Generated("SparkTools")
	private MaterialEstudo(Builder builder) {
		this.id = builder.id;
		this.nome = builder.nome;
		this.numeroOrdem = builder.numeroOrdem;
		this.enviaCertificado = builder.enviaCertificado;
		this.qtdEstoque = builder.qtdEstoque;
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

	public Integer getNumeroOrdem() {
		return numeroOrdem;
	}

	public void setNumeroOrdem(Integer numeroOrdem) {
		this.numeroOrdem = numeroOrdem;
	}

	public boolean isEnviaCertificado() {
		return enviaCertificado;
	}

	public void setEnviaCertificado(boolean enviaCertificado) {
		this.enviaCertificado = enviaCertificado;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
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
		MaterialEstudo other = (MaterialEstudo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MaterialEstudo [nome=" + nome + "]";
	}

	/**
	 * Creates builder to build {@link MaterialEstudo}.
	 * 
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link MaterialEstudo}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private String nome;
		private Integer numeroOrdem;
		private boolean enviaCertificado;
		private Integer qtdEstoque;

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

		public Builder withNumeroOrdem(Integer numeroOrdem) {
			this.numeroOrdem = numeroOrdem;
			return this;
		}

		public Builder withEnviaCertificado(boolean enviaCertificado) {
			this.enviaCertificado = enviaCertificado;
			return this;
		}

		public Builder withQtdEstoque(Integer qtdEstoque) {
			this.qtdEstoque = qtdEstoque;
			return this;
		}

		public MaterialEstudo build() {
			return new MaterialEstudo(this);
		}
	}

}
