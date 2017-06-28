package br.com.escoladabiblia.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.escoladabiblia.model.AtividadeEstudo;
import br.com.escoladabiblia.util.dto.AtividadeEstudoImpressaoDTO;

public class AtividadeEstudoRepositoryImpl extends AbstractRepositoryImpl<AtividadeEstudo, Long>
		implements AtividadeEstudoRepositoryCustom {

	public AtividadeEstudoRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AtividadeEstudoImpressaoDTO> obterAtividadesEstudoAlunosParaImpressao(Long idPostagem) {

		Query query = createQuery(
				" select new br.com.escoladabiblia.util.dto.AtividadeEstudoImpressaoDTO(a, max(m.tipoEnvelope), count(b.id)) "
			  + "   from AtividadeEstudo ae "
			  + "   join ae.aluno a "
			  + "   join ae.material m "
			  + "	left join ae.biblia b "
			  + "  where ae.postagem.id = :idPostagem "
			  + "  group by a.id ");
		
		query.setParameter("idPostagem", idPostagem);

		return query.getResultList();
	}

}
