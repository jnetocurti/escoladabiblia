package br.com.escoladabiblia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

import br.com.escoladabiblia.service.AlunoService;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestScope
@RequestMapping("/relatorios/alunos")
public class RelatoriosAlunosController extends BaseController {

	@Autowired
	private AlunoService alunoService;
	
	@GetMapping(path = "alunos-por-presidio")
	public ResponseEntity<byte[]> relatorioAlunosPorPresidio() throws JRException {

		final byte[] alunos = alunoService.relatorioAlunosPorPresidio();

		return super.getPDFResponse(alunos, "alunos-por-presidio.pdf");
	}
	
	@GetMapping(path = "alunos-ativos-periodo")
	public ResponseEntity<byte[]> relatorioAlunosAtivosPorPeriodo() throws JRException {

		final byte[] alunos = alunoService.relatorioAlunosAtivosPorPeriodo();

		return super.getPDFResponse(alunos, "alunos-ativos-periodo.pdf");
	}
	
	@GetMapping(path = "alunos-inativos-periodo")
	public ResponseEntity<byte[]> relatorioAlunosInativosPorPeriodo() throws JRException {

		final byte[] alunos = alunoService.relatorioAlunosInativosPorPeriodo();

		return super.getPDFResponse(alunos, "alunos-inativos-periodo.pdf");
	}

}
