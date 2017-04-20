package br.com.escoladabiblia.repository.custom;

import java.util.List;

import br.com.escoladabiblia.model.MaterialEstudo;

public interface MaterialEstudoRepositoryCustom {

	/**
	 * @param ids
	 *            - Ids dos materiais ja estudados
	 * @return Lista de materiais ainda não estudados pelo aluno
	 */
	List<MaterialEstudo> obterMateriaisNaoEstudados(List<Long> ids);
}
