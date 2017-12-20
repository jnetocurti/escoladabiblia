package br.com.escoladabiblia.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Endereco;
import br.com.escoladabiblia.model.Presidiario;
import br.com.escoladabiblia.repository.AlunoRepository;
import br.com.escoladabiblia.util.dto.AlunoComumDTO;
import br.com.escoladabiblia.util.dto.AlunoPresidioDTO;
import br.com.escoladabiblia.util.impressao.JasperUtil;
import br.com.escoladabiblia.util.impressao.QuantidadeAlunosPresidioVO;
import br.com.escoladabiblia.util.pagination.BootgridRequest;
import br.com.escoladabiblia.util.pagination.BootgridResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Transactional(readOnly = true)
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public BootgridResponse<AlunoPresidioDTO> findAlunosPresidiosByNomeOrMatriculaOrNomePresidio(
			BootgridRequest bootgridRequest) {

		return new BootgridResponse<AlunoPresidioDTO>(
				alunoRepository.findAlunosPresidiosByNomeOrMatriculaOrNomePresidio(
						"%" + bootgridRequest.getSearchPhrase().toUpperCase() + "%", bootgridRequest.getPageRequest()));
	}

	@Override
	@Transactional(readOnly = false)
	public Aluno salvarAlunoPresidio(Aluno aluno, Presidiario presidiario) {

		presidiario.setAluno(aluno);

		aluno.getCaracterizacoes().add(presidiario);

		return alunoRepository.saveAndFlush(aluno);
	}
	
	@Override
	public BootgridResponse<AlunoComumDTO> findAlunosComunsByNome(BootgridRequest bootgridRequest) {

		return new BootgridResponse<AlunoComumDTO>(alunoRepository.findAlunosComunsByName(
				"%" + bootgridRequest.getSearchPhrase().toUpperCase() + "%", bootgridRequest.getPageRequest()));
	}

	@Override
	@Transactional(readOnly = false)
	public Aluno salvarAlunoComum(Aluno aluno) {
		
		return alunoRepository.save(aluno);
	}

	@Override
	public Aluno editar(Long id) {

		return alunoRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void alterarAlunoEmLiberdade(Long id, String observacao, Endereco endereco) {

		Aluno aluno = alunoRepository.findOne(id);

		if (aluno.getCaracterizacao() != null) {
			
			aluno.getCaracterizacao().setAtiva(false);
		}

		aluno.setObservacao(observacao);
		aluno.setEndereco(endereco);

		alunoRepository.save(aluno);
	}

	@Override
	public byte[] relatorioAlunosPorPresidio() throws JRException {

		final List<QuantidadeAlunosPresidioVO> alunosPresidios = alunoRepository.obterQuantidadeAlunosPorPresidio();

		final InputStream jasper = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jasper/relatorio-alunos-por-presidio.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, null,
				new JRBeanCollectionDataSource(alunosPresidios));

		return JasperUtil.exportReport(jasperPrint);
	}

}
