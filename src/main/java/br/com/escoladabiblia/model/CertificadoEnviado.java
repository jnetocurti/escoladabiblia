package br.com.escoladabiblia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.annotation.Generated;

@Entity
@Table(name = "certificados_enviados")
public class CertificadoEnviado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "postagem_id", nullable = false, foreignKey = @ForeignKey(name = "postagem_fk"))
	private Postagem postagem;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "atividade_estudo_id", nullable = false, foreignKey = @ForeignKey(name = "atividade_estudo_fk"))
	private AtividadeEstudo atividadeEstudo;

	@Generated("SparkTools")
	private CertificadoEnviado(Builder builder) {
		this.id = builder.id;
		this.postagem = builder.postagem;
		this.atividadeEstudo = builder.atividadeEstudo;
	}

	public CertificadoEnviado() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public AtividadeEstudo getAtividadeEstudo() {
		return atividadeEstudo;
	}

	public void setAtividadeEstudo(AtividadeEstudo atividadeEstudo) {
		this.atividadeEstudo = atividadeEstudo;
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
		CertificadoEnviado other = (CertificadoEnviado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CertificadoEnviado [postagem=" + postagem + ", atividadeEstudo=" + atividadeEstudo + "]";
	}

	/**
	 * Creates builder to build {@link CertificadoEnviado}.
	 * 
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link CertificadoEnviado}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private Postagem postagem;
		private AtividadeEstudo atividadeEstudo;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withPostagem(Postagem postagem) {
			this.postagem = postagem;
			return this;
		}

		public Builder withAtividadeEstudo(AtividadeEstudo atividadeEstudo) {
			this.atividadeEstudo = atividadeEstudo;
			return this;
		}

		public CertificadoEnviado build() {
			return new CertificadoEnviado(this);
		}
	}

}
