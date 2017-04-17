package br.com.escoladabiblia.util.impressao;

import java.io.Serializable;

public interface Destinatario extends Serializable {

	String getNome();

	String getIdentificacao();

	String getLogradouro();

	String getComplemento();

	String getBairro();

	String getLocalidadeAndUF();

	String getCEP();
}
