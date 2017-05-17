package br.com.escoladabiblia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_prevista_envio", updatable = false, nullable = false)
	private Calendar dataPrevistaEnvio;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_efetiva_envio")
	private Calendar dataEfetivaEnvio;

	@JsonInclude(value = Include.NON_EMPTY)
	@OneToMany(mappedBy = "postagem", cascade = { CascadeType.ALL })
	private List<AtividadeEstudo> atividadesEstudo = new ArrayList<>();

	@JsonInclude(value = Include.NON_EMPTY)
	@OneToMany(mappedBy = "postagem", cascade = { CascadeType.ALL })
	private List<BibliaEnviada> bibliasEnviadas = new ArrayList<>();

	@JsonInclude(value = Include.NON_EMPTY)
	@OneToMany(mappedBy = "postagem", cascade = { CascadeType.ALL })
	private List<CertificadoEnviado> certificadosEnviados = new ArrayList<>();

	public Postagem() {
	}

	@Generated("SparkTools")
	private Postagem(Builder builder) {
		this.id = builder.id;
		this.dataPrevistaEnvio = builder.dataPrevistaEnvio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataPrevistaEnvio() {
		return dataPrevistaEnvio;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setDataPrevistaEnvio(Calendar dataPrevistaEnvio) {
		this.dataPrevistaEnvio = dataPrevistaEnvio;
	}

	public Calendar getDataEfetivaEnvio() {
		return dataEfetivaEnvio;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setDataEfetivaEnvio(Calendar dataEfetivaEnvio) {
		this.dataEfetivaEnvio = dataEfetivaEnvio;
	}

	public List<AtividadeEstudo> getAtividadesEstudo() {
		return atividadesEstudo;
	}

	public List<BibliaEnviada> getBibliasEnviadas() {
		return bibliasEnviadas;
	}

	public List<CertificadoEnviado> getCertificadosEnviados() {
		return certificadosEnviados;
	}

	@Transient
	public Integer getQuantidadeAlunos() {

		return this.atividadesEstudo.stream().map(a -> a.getAluno()).collect(Collectors.toSet()).size();
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
		return "Postagem [dataPrevistaEnvio=" + dataPrevistaEnvio + ", dataEfetivaEnvio=" + dataEfetivaEnvio + "]";
	}

	/**
	 * Creates builder to build {@link Postagem}.
	 * 
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Postagem}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private Calendar dataPrevistaEnvio;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withDataPrevistaEnvio(Calendar dataPrevistaEnvio) {
			this.dataPrevistaEnvio = dataPrevistaEnvio;
			return this;
		}

		public Postagem build() {
			return new Postagem(this);
		}
	}

}
