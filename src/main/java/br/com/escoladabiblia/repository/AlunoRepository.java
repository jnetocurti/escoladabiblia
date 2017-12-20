package br.com.escoladabiblia.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.util.dto.AlunoComumDTO;
import br.com.escoladabiblia.util.dto.AlunoPresidioDTO;
import br.com.escoladabiblia.util.impressao.QuantidadeAlunosPresidioVO;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	@Query(" select new br.com.escoladabiblia.util.dto.AlunoPresidioDTO(a.id, a.nome, c.matricula, c.raio, c.cela, p.nome) "
		 + "   from Aluno a "
		 + "   join a.caracterizacoes c "
		 + "   join c.presidio p "
		 + "  where c.ativa = true "
		 + "    and ( upper(a.nome) like :filter "
		 + "     or c.matricula like :filter "
		 + "     or upper(p.nome) like :filter ) "
		 + "  order by p.nome asc, a.nome asc ")
	Page<AlunoPresidioDTO> findAlunosPresidiosByNomeOrMatriculaOrNomePresidio(@Param("filter") String filter, Pageable pageable);
	
	@Query(" select new br.com.escoladabiblia.util.dto.AlunoComumDTO(a.id, a.nome) "
		 + "   from Aluno a "
		 + "   left join a.caracterizacoes c "
		 + "  where upper(a.nome) like :filter "
		 + "    and ( c = null or c.ativa = false ) "
		 + "  order by a.nome asc ")
	Page<AlunoComumDTO> findAlunosComunsByName(@Param("filter") String filter, Pageable pageable);
	
	@Query(" select aluno from Aluno aluno "
		 + "   join aluno.atividadesEstudo atividade "
		 + "   join atividade.material material "
		 + "  where atividade.postagem.id = :idPostagem "
		 + "    and material.numeroOrdem = 1"
		 + "  group by aluno.id ")
	List<Aluno> findAlunosNovosByPostagem(@Param("idPostagem") Long idPostagem);
	
	@Query(" select aluno from Aluno aluno "
		 + "   join aluno.atividadesEstudo atividade "
		 + "   join atividade.material material "
		 + "  where atividade.postagem.id = :idPostagem "
		 + "    and material.numeroOrdem > 1 "
		 + "  group by aluno.id ")
	List<Aluno> findAlunosSequenciaisByPostagem(@Param("idPostagem") Long idPostagem);

	@Modifying
	@Query("update Aluno aluno set aluno.possuiBiblia = :possuiBiblia where aluno.id = :id")
	void updateBibliaStatus(@Param("id") Long id, @Param("possuiBiblia") boolean possuiBiblia);

	@Query(" select new br.com.escoladabiblia.util.impressao.QuantidadeAlunosPresidioVO(p.nome, count(a.id)) "
		 + "   from Aluno a "
		 + "   join a.caracterizacoes c "
		 + "   join c.presidio p "
		 + "  where c.ativa = true "
		 + "  group by p.nome "
		 + "  order by p.nome asc ")
	List<QuantidadeAlunosPresidioVO> obterQuantidadeAlunosPorPresidio();

}
