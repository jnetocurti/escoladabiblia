package br.com.escoladabiblia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "alunos")
public class Aluno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Calendar dataNascimento;

	@Column(name = "possui_biblia")
	private Boolean possuiBiblia;

	@Column(name = "foi_batizado")
	private Boolean batizado;

	@Column(name = "frequentou_igreja")
	private Boolean frequentouIgreja;

	@JsonInclude(value = Include.NON_NULL)
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "endereco_id", foreignKey = @ForeignKey(name = "endereco_fk"))
	private Endereco endereco;

	@JsonInclude(value = Include.NON_EMPTY)
	@OneToMany(mappedBy = "aluno", cascade = { CascadeType.ALL })
	private List<Caracterizacao> caracterizacoes = new ArrayList<>();

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

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getPossuiBiblia() {
		return possuiBiblia;
	}

	public void setPossuiBiblia(Boolean possuiBiblia) {
		this.possuiBiblia = possuiBiblia;
	}

	public Boolean getBatizado() {
		return batizado;
	}

	public void setBatizado(Boolean batizado) {
		this.batizado = batizado;
	}

	public Boolean getFrequentouIgreja() {
		return frequentouIgreja;
	}

	public void setFrequentouIgreja(Boolean frequentouIgreja) {
		this.frequentouIgreja = frequentouIgreja;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Caracterizacao> getCaracterizacoes() {
		return caracterizacoes;
	}
	
	@Transient
	public String getCaracterizacao() {

		if (!caracterizacoes.isEmpty()) {

			return caracterizacoes.get(caracterizacoes.size() - 1).getCaracterizacao();

		} else {
			// TODO definir caracterização padrão
			return "";
		}
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + "]";
	}

}
