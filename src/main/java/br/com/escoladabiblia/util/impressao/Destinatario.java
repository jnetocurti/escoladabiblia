package br.com.escoladabiblia.util.impressao;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Endereco;

public abstract class Destinatario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected final Aluno aluno;

	public Destinatario(Aluno aluno) {

		if (aluno == null) {
			throw new IllegalArgumentException("Aluno não pode ser diferente de null");
		}

		this.aluno = aluno;
	}

	protected String getNome() {
		return aluno.getNome();
	}

	protected String getLocalidade() {

		Endereco endereco = aluno.getEnderecoEfetivo();

		if (endereco == null) {
			return null;
		}

		return stringFromBuilder(new StringBuilder(endereco.getLogradouro() + appendNumero(endereco)));
	}

	private String appendNumero(Endereco endereco) {

		return endereco.getNumero() != null ? ", " + endereco.getNumero() : "";
	}

	protected String getComplemento() {

		Endereco endereco = aluno.getEnderecoEfetivo();

		if (endereco == null || StringUtils.isEmpty(endereco.getComplemento())) {
			return null;
		}

		return new StringBuilder(endereco.getComplemento()).toString();
	}

	protected String getBairro() {

		Endereco endereco = aluno.getEnderecoEfetivo();

		if (endereco == null || StringUtils.isEmpty(endereco.getBairro())) {
			return null;
		}

		return stringFromBuilder(new StringBuilder("Bairro: " + endereco.getBairro()));
	}

	protected String getCidadeEstado() {

		Endereco endereco = aluno.getEnderecoEfetivo();

		if (endereco == null) {
			return null;
		}

		return stringFromBuilder(new StringBuilder(endereco.getCidade() + " - " + endereco.getEstado().getUf()));
	}

	protected String getCep() {

		Endereco endereco = aluno.getEnderecoEfetivo();

		if (endereco == null || StringUtils.isEmpty(endereco.getCep())) {
			return null;
		}

		return stringFromBuilder(new StringBuilder(endereco.getCep()));
	}

	protected String stringFromBuilder(StringBuilder builder) {

		return !StringUtils.isEmpty(builder.toString()) ? builder.toString() : null;
	}

}
