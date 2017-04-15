package br.com.escoladabiblia.model;

import java.io.Serializable;
import java.util.Calendar;

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

	@Column(name = "envio_certificado")
	private boolean envioCertificado;

	@Column(name = "envio_biblia")
	private boolean envioBiblia;

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

	public boolean isEnvioCertificado() {
		return envioCertificado;
	}

	public void setEnvioCertificado(boolean envioCertificado) {
		this.envioCertificado = envioCertificado;
	}

	public boolean isEnvioBiblia() {
		return envioBiblia;
	}

	public void setEnvioBiblia(boolean envioBiblia) {
		this.envioBiblia = envioBiblia;
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
	
	@Transient
	public Calendar getDataEnvioEstudo() {
		return postagem.getDataPrevistaEnvio();
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

}
