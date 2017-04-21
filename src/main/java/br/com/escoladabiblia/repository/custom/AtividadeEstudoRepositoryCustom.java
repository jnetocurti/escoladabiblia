package br.com.escoladabiblia.repository.custom;

import java.util.List;

import br.com.escoladabiblia.util.dto.AtividadeEstudoImpressaoDTO;

public interface AtividadeEstudoRepositoryCustom {

	/**
	 * @param idPostagem
	 *            - O {@code id} da postagem relacionada à atividade de estudo
	 * 
	 * @return Lista de {@code AtividadeEstudoImpressaoDTO} agrupadas por alunos
	 *         e com o maior tipo de envelope do material de estudo
	 */
	List<AtividadeEstudoImpressaoDTO> obterAtividadesEstudoAlunosParaImpressao(Long idPostagem);
}
