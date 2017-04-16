package br.com.escoladabiblia.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "caracterizacoes")
@EntityListeners(value = { Caracterizacao.class })
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Caracterizacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_caracterizacao", length = 50, nullable = false)
	protected TipoCaracterizacao tipo;

	@Column(name = "ativa", nullable = false)
	protected boolean ativa;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_registro", nullable = false)
	protected Calendar dataRegistro;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "aluno_id", foreignKey = @ForeignKey(name = "aluno_fk"))
	protected Aluno aluno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoCaracterizacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoCaracterizacao tipo) {
		this.tipo = tipo;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public Calendar getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Calendar dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
		Caracterizacao other = (Caracterizacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Caracterizacao [tipo=" + tipo + ", ativa=" + ativa + ", dataRegistro=" + dataRegistro + "]";
	}

}
