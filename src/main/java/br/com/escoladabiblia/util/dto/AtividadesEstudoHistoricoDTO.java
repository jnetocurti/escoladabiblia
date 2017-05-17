package br.com.escoladabiblia.util.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.escoladabiblia.model.AtividadeEstudo;

public class AtividadesEstudoHistoricoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idAluno;

	private String nomeAluno;

	private List<AtividadeEstudo> historico = new ArrayList<>();

	public AtividadesEstudoHistoricoDTO(Long idAluno, String nomeAluno) {
		this.idAluno = idAluno;
		this.nomeAluno = nomeAluno;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public List<AtividadeEstudo> getHistorico() {
		return historico;
	}

}
