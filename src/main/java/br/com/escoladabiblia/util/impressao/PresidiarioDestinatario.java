package br.com.escoladabiblia.util.impressao;

import org.springframework.util.StringUtils;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Presidiario;
import br.com.escoladabiblia.model.TipoCaracterizacao;

public class PresidiarioDestinatario extends AbstractDestinatario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PresidiarioDestinatario(Aluno aluno) {
		super(aluno);
		if (aluno.getTipoCaracterizacao() == null || !aluno.getTipoCaracterizacao().equals(TipoCaracterizacao.PRESIDIARIO)) {
			throw new IllegalArgumentException("Tipo de caracterização do aluno deve ser PRESIDIARIO");
		}
	}

	@Override
	public String getIdentificacao() {

		StringBuilder builder = new StringBuilder();

		createIdentificacao(builder);

		return resultFromBuilder(builder);
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
