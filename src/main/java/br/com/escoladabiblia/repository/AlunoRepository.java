package br.com.escoladabiblia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.util.dto.AlunoPresidioDTO;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	@Query(" SELECT new br.com.escoladabiblia.util.dto.AlunoPresidioDTO(a.id, a.nome, c.matricula, c.raio, c.cela, p.nome) "
		 + "   FROM Aluno a "
		 + "   JOIN a.caracterizacoes c "
		 + "   JOIN c.presidio p "
		 + "  WHERE a.nome LIKE :param "
		 + "     OR c.matricula LIKE :param "
		 + "     OR p.nome LIKE :param "
		 + "  ORDER BY p.nome ASC, a.nome ASC ")
	Page<AlunoPresidioDTO> findAlunosPresidiosByNomeOrMatriculaOrNomePresidio(@Param("param") String param, Pageable pageable);

}
