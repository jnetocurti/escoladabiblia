package br.com.escoladabiblia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.util.dto.AlunoComumDTO;
import br.com.escoladabiblia.util.dto.AlunoPresidioDTO;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	@Query(" select distinct new br.com.escoladabiblia.util.dto.AlunoPresidioDTO(a.id, a.nome, c.matricula, c.raio, c.cela, p.nome) "
		 + "   from Aluno a "
		 + "   join a.caracterizacoes c "
		 + "   join c.presidio p "
		 + "  where a.nome like :filter "
		 + "    and c.ativa = true "
		 + "     or c.matricula like :filter "
		 + "     or p.nome like :filter "
		 + "  order by p.nome asc, a.nome asc ")
	Page<AlunoPresidioDTO> findAlunosPresidiosByNomeOrMatriculaOrNomePresidio(@Param("filter") String filter, Pageable pageable);
	
	@Query(" select new br.com.escoladabiblia.util.dto.AlunoComumDTO(a.id, a.nome) "
		 + "   from Aluno a "
		 + "   left join a.caracterizacoes c "
		 + "  where a.nome like :filter "
		 + "    and c = null or c.ativa = false "
		 + "  order by a.nome asc ")
	Page<AlunoComumDTO> findAlunosComunsByName(@Param("filter") String filter, Pageable pageable);

}
