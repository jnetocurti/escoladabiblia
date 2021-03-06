package br.com.escoladabiblia.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.annotation.Generated;

@Entity
@Table(name = "atividades_estudo")
public class AtividadeEstudo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_retorno_estudo")
	private Calendar dataRetornoEstudo;

	@Column(name = "nota")
	private Float nota;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "aluno_id", nullable = false, foreignKey = @ForeignKey(name = "aluno_fk"))
	private Aluno aluno;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "postagem_id", nullable = false, foreignKey = @ForeignKey(name = "postagem_fk"))
	private Postagem postagem;

	@ManyToOne
	@JoinColumn(name = "material_id", nullable = false, foreignKey = @ForeignKey(name = "material_fk"))
	private MaterialEstudo material;

	@OneToOne(mappedBy = "atividadeEstudo", cascade = CascadeType.ALL)
	private CertificadoEnviado certificado;

	@OneToOne(mappedBy = "atividadeEstudo", cascade = CascadeType.ALL)
	private BibliaEnviada biblia;
	
	@Column(name = "atividade_encerrada")
	private boolean atividadeEncerrada;

	public AtividadeEstudo() {
	}

	@Generated("SparkTools")
	private AtividadeEstudo(Builder builder) {
		this.id = builder.id;
		this.aluno = builder.aluno;
		this.postagem = builder.postagem;
		this.material = builder.material;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataRetornoEstudo() {
		return dataRetornoEstudo;
	}

	public void setDataRetornoEstudo(Calendar dataRetornoEstudo) {
		this.dataRetornoEstudo = dataRetornoEstudo;
	}

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public MaterialEstudo getMaterial() {
		return material;
	}

	public void setMaterial(MaterialEstudo material) {
		this.material = material;
	}
	
	public CertificadoEnviado getCertificado() {
		return certificado;
	}

	public void setCertificado(CertificadoEnviado certificado) {
		this.certificado = certificado;
	}

	public BibliaEnviada getBiblia() {
		return biblia;
	}

	public void setBiblia(BibliaEnviada biblia) {
		this.biblia = biblia;
	}
	
	public boolean isAtividadeEncerrada() {
		return atividadeEncerrada;
	}

	public void setAtividadeEncerrada(boolean atividadeEncerrada) {
		this.atividadeEncerrada = atividadeEncerrada;
	}

	@Transient
	public Calendar getDataEnvioEstudo() {
		return postagem.getDataPrevistaEnvio();
	}
	
	@Transient
	public boolean isPostagemEncerrada() {
		return postagem.getDataEfetivaEnvio() != null;
	}
	
	@Transient
	public boolean isCorrigible() {
		return material.isCorrigible();
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
		AtividadeEstudo other = (AtividadeEstudo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AtividadeEstudo [dataEnvioEstudo=" + postagem.getDataPrevistaEnvio() + ", aluno=" + aluno
				+ ", material=" + material + "]";
	}

	/**
	 * Creates builder to build {@link AtividadeEstudo}.
	 * 
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link AtividadeEstudo}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private Aluno aluno;
		private Postagem postagem;
		private MaterialEstudo material;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withAluno(Aluno aluno) {
			this.aluno = aluno;
			return this;
		}

		public Builder withPostagem(Postagem postagem) {
			this.postagem = postagem;
			return this;
		}

		public Builder withMaterial(MaterialEstudo material) {
			this.material = material;
			return this;
		}

		public AtividadeEstudo build() {
			return new AtividadeEstudo(this);
		}
	}

}
