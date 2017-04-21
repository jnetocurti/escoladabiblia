package br.com.escoladabiblia.util.impressao;

import br.com.escoladabiblia.model.Aluno;

public class DestinatarioFactory {

	private DestinatarioFactory() {
	}

	public static Destinatario getDestinatario(Aluno aluno) {

		switch (aluno.getTipoCaracterizacao()) {

		case PRESIDIARIO:

			return new PresidiarioDestinatario(aluno);

		default:
			return new SimpleDestinatario(aluno);
		}
	}

}
