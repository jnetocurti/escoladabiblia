package br.com.escoladabiblia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "postagens")
public class Postagem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_postagem", nullable = false)
	private Calendar dataPostagem;

	@Column(name = "postado", nullable = false)
	private boolean postado;

	@OneToMany
	@JsonInclude(value = Include.NON_EMPTY)
	@JoinTable(name = "atividades_postagem", joinColumns = @JoinColumn(name = "postagem_id"), foreignKey = @ForeignKey(name = "postagem_fk"), 
	inverseJoinColumns = @JoinColumn(name = "atividade_id"), inverseForeignKey = @ForeignKey(name = "atividade_fk"), 
	uniqueConstraints = @UniqueConstraint(name = "atividades_postagem_uk", columnNames = "atividade_id"))
	private List<AtividadeEstudo> atividadesEstudo = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Calendar dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public boolean isPostado() {
		return postado;
	}

	public void setPostado(boolean postado) {
		this.postado = postado;
	}

	public List<AtividadeEstudo> getAtividadesEstudo() {
		return atividadesEstudo;
	}

	public void setAtividadesEstudo(List<AtividadeEstudo> atividadesEstudo) {
		this.atividadesEstudo = atividadesEstudo;
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
		Postagem other = (Postagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Postagem [dataPostagem=" + dataPostagem + ", postado=" + postado + "]";
	}

}
