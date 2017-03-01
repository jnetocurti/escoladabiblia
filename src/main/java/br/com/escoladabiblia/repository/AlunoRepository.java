package br.com.escoladabiblia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escoladabiblia.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	Page<Aluno> findByNomeLike(String string, Pageable pageable);

}
