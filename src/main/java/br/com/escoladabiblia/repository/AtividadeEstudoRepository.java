package br.com.escoladabiblia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.escoladabiblia.model.AtividadeEstudo;
import br.com.escoladabiblia.repository.custom.AtividadeEstudoRepositoryCustom;

public interface AtividadeEstudoRepository extends JpaRepository<AtividadeEstudo, Long>, AtividadeEstudoRepositoryCustom {

	@Query(" select atividade from AtividadeEstudo atividade "
		 + "  where atividade.aluno.id = :id "
		 + "    and atividade.atividadeEncerrada = true "
		 + "  order by atividade.postagem.dataPrevistaEnvio asc ")
	List<AtividadeEstudo> obterHistoricoAtividadesEstudoAluno(@Param("id") Long id);

}
