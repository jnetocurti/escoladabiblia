package br.com.escoladabiblia.util.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.MaterialEstudo;

public class EdicaoAtividadesEstudoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Aluno aluno;

	private final Calendar dataProximoEnvio;

	private List<MaterialEstudo> materiais = new ArrayList<>();

	public EdicaoAtividadesEstudoDTO(Aluno aluno, Calendar dataProximoEnvio) {
		this.aluno = aluno;
		this.dataProximoEnvio = dataProximoEnvio;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public Calendar getDataProximoEnvio() {
		return dataProximoEnvio;
	}

	public List<MaterialEstudo> getMateriais() {
		return materiais;
	}

}
