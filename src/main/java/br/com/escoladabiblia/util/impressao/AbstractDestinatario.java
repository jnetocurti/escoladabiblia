package br.com.escoladabiblia.util.impressao;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Endereco;

public abstract class AbstractDestinatario implements Destinatario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected final Aluno aluno;

	public AbstractDestinatario(Aluno aluno) {
		Assert.notNull(aluno, "Aluno não pode ser null");
		this.aluno = aluno;
	}

	@Override
	public String getNome() {
		return aluno.getNome();
	}

	@Override
	public String getLogradouro() {

		Endereco endereco = getEndereco();

		return resultFromBuilder(new StringBuilder(endereco.getLogradouro() + appendNumero(endereco)));
	}

	@Override
	public String getComplemento() {

		Endereco endereco = getEndereco();
		
		if (endereco.getComplemento() == null) {
			return null;
		}

		return resultFromBuilder(new StringBuilder(endereco.getComplemento()));
	}

	@Override
	public String getBairro() {

		Endereco endereco = getEndereco();

		if (endereco.getBairro() == null) {
			return null;
		}

		return resultFromBuilder(new StringBuilder("Bairro: " + endereco.getBairro()));
	}

	@Override
	public String getLocalidadeAndUF() {

		Endereco endereco = getEndereco();

		return resultFromBuilder(new StringBuilder(endereco.getCidade() + " - " + endereco.getEstado().getUf()));
	}

	@Override
	public String getCEP() {

		Endereco endereco = getEndereco();
		
		if (endereco.getCep() == null) {
			return null;
		}

		return resultFromBuilder(new StringBuilder(endereco.getCep()));
	}

	private Endereco getEndereco() {

		Endereco endereco = aluno.getEnderecoEfetivo();

		if (endereco == null) {
			throw new IllegalArgumentException("Endereco não pode ser null");
		}

		return endereco;
	}

	private String appendNumero(Endereco endereco) {

		return endereco.getNumero() != null ? ", " + endereco.getNumero() : "";
	}

	protected String resultFromBuilder(StringBuilder builder) {

		return !StringUtils.isEmpty(builder.toString()) ? builder.toString() : null;
	}

}
