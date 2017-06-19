package br.com.escoladabiblia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.escoladabiblia.model.CertificadoEnviado;
import br.com.escoladabiblia.util.impressao.AlunosCertificadosPostagemVO;
import br.com.escoladabiblia.util.impressao.CertificadosPostagemVO;

public interface CertificadoEnviadoRepository extends JpaRepository<CertificadoEnviado, Long> {

	boolean existsByAtividadeEstudo_Id(Long id);

	@Modifying
	@Query("delete from CertificadoEnviado certificado where certificado.atividadeEstudo.id = :id")
	void deleteByAtividadeEstudoId(@Param("id") Long id);

	@Query("select new br.com.escoladabiblia.util.impressao.CertificadosPostagemVO(material.nome, count(material.id))"
		 + "  from CertificadoEnviado as certificado"
		 + "  join certificado.atividadeEstudo as atividadeEstudo"
		 + "  join atividadeEstudo.material as material"
		 + " where certificado.postagem.id = :idPostagem"
		 + " group by material.nome, material.numeroOrdem"
		 + " order by material.numeroOrdem")
	List<CertificadosPostagemVO> findCertificadosPostagem(@Param("idPostagem") Long idPostagem);

	@Query("select new br.com.escoladabiblia.util.impressao.AlunosCertificadosPostagemVO(aluno.nome)"
		 + "  from CertificadoEnviado as certificado"
		 + "  join certificado.atividadeEstudo as atividadeEstudo"
		 + "  join atividadeEstudo.material as material"
		 + "  join atividadeEstudo.aluno as aluno"
		 + " where certificado.postagem.id = :idPostagem"
		 + "   and material.nome = :certificado")
	List<AlunosCertificadosPostagemVO> findAlunosCertificados(@Param("idPostagem") Long idPostagem,
			@Param("certificado") String certificado);

}
