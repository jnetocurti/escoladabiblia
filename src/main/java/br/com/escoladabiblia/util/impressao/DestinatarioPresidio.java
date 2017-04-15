package br.com.escoladabiblia.util.impressao;

import org.springframework.util.StringUtils;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Presidiario;
import br.com.escoladabiblia.model.TipoCaracterizacao;

public class DestinatarioPresidio extends Destinatario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DestinatarioPresidio(Aluno aluno) {
		super(aluno);
	}

	public String getInstituicao() {

		if (TipoCaracterizacao.PRESIDIARIO.equals(aluno.getTipoCaracterizacao())) {

			Presidiario presidiario = (Presidiario) aluno.getCaracterizacao();

			return stringFromBuilder(new StringBuilder(presidiario.getPresidio().getNome()));
		}

		return null;
	}

	public String getIdentificacao() {

		StringBuilder builder = new StringBuilder();

		if (TipoCaracterizacao.PRESIDIARIO.equals(aluno.getTipoCaracterizacao())) {

			createIdentificacao(builder);
		}

		return stringFromBuilder(builder);
	}

	private void createIdentificacao(StringBuilder builder) {

		Presidiario presidiario = (Presidiario) aluno.getCaracterizacao();

		appendMatricula(presidiario, builder);

		appendRaio(presidiario, builder);

		appendCela(presidiario, builder);
	}

	private void appendMatricula(Presidiario presidiario, StringBuilder builder) {

		if (!StringUtils.isEmpty(presidiario.getMatricula())) {

			builder.append("Mt: " + presidiario.getMatricula());
			
			if (!StringUtils.isEmpty(presidiario.getRaio()) || !StringUtils.isEmpty(presidiario.getCela())) {

				builder.append(" - ");
			}
		}
	}

	private void appendRaio(Presidiario presidiario, StringBuilder builder) {

		if (!StringUtils.isEmpty(presidiario.getRaio())) {

			builder.append("Raio: " + presidiario.getRaio());
			
			if (!StringUtils.isEmpty(presidiario.getCela())) {

				builder.append(" - ");
			}
		}
	}

	private void appendCela(Presidiario presidiario, StringBuilder builder) {

		if (!StringUtils.isEmpty(presidiario.getCela())) {

			builder.append("Cela: " + presidiario.getCela());
		}
	}
}
