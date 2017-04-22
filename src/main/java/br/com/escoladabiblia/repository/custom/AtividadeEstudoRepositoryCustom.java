package br.com.escoladabiblia.repository.custom;

import java.util.List;

import br.com.escoladabiblia.util.dto.AtividadeEstudoImpressaoDTO;

public interface AtividadeEstudoRepositoryCustom {

	/**
	 * @param idPostagem
	 *            - Id da postagem qual a atividade de estudo está relacionada
	 * 
	 * @return Lista de {@code AtividadeEstudoImpressaoDTO} agrupadas por aluno
	 *
	 * @see {@link br.com.escoladabiblia.util.dto.AtividadeEstudoImpressaoDTO}
	 * @see {@link br.com.escoladabiblia.model.TipoEnvelope }
	 */
	List<AtividadeEstudoImpressaoDTO> obterAtividadesEstudoAlunosParaImpressao(Long idPostagem);
}
