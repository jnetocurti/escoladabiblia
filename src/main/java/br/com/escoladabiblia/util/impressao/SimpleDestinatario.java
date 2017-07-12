package br.com.escoladabiblia.util.impressao;

import org.springframework.util.Assert;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.TipoCaracterizacao;

public class SimpleDestinatario extends AbstractDestinatario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleDestinatario(Aluno aluno) {
		super(aluno);
		Assert.isTrue(aluno.getTipoCaracterizacao() == TipoCaracterizacao.NONE,
				"Aluno  não pode possuir um tipo de caracterização ativa");
	}

	@Override
	public String getIdentificacao() { // Não aplicável para alunos sem caracterização
		return null;
	}

}
