package br.com.escoladabiblia.util.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.MaterialEstudo;
import br.com.escoladabiblia.model.Postagem;

public class EdicaoAtividadesEstudoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Aluno aluno;
	
	private final Postagem postagem; 

	private List<MaterialEstudo> materiais = new ArrayList<>();

	public EdicaoAtividadesEstudoDTO(Aluno aluno, Postagem postagem) {
		this.aluno = aluno;
		this.postagem = postagem;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public List<MaterialEstudo> getMateriais() {
		return materiais;
	}

}
