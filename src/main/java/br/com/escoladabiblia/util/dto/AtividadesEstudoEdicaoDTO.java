package br.com.escoladabiblia.util.dto;

import java.io.Serializable;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Postagem;

public class AtividadesEstudoEdicaoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Aluno aluno;

	private final Postagem postagem;

	public AtividadesEstudoEdicaoDTO(Aluno aluno, Postagem postagem) {
		this.aluno = aluno;
		this.postagem = postagem;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public Postagem getPostagem() {
		return postagem;
	}

}
