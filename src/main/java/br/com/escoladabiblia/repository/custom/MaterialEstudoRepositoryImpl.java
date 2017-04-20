package br.com.escoladabiblia.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.escoladabiblia.model.MaterialEstudo;

public class MaterialEstudoRepositoryImpl extends AbstractRepositoryImpl<MaterialEstudo, Long>
		implements MaterialEstudoRepositoryCustom {

	public MaterialEstudoRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<MaterialEstudo> obterMateriaisNaoEstudados(List<Long> ids) {

		if (ids.isEmpty()) { // caso lista vazia retorna todos os materiais
			ids.add(-1L);
		}

		TypedQuery<MaterialEstudo> query = createTypedQuery("from MaterialEstudo m where m.id not in :ids",
				MaterialEstudo.class);

		query.setParameter("ids", ids);

		return query.getResultList();
	}

}
