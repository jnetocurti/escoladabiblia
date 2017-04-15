package br.com.escoladabiblia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.model.Presidiario;
import br.com.escoladabiblia.service.AlunoService;
import br.com.escoladabiblia.service.PresidioService;
import br.com.escoladabiblia.util.dto.AlunoPresidioDTO;
import br.com.escoladabiblia.util.dto.MessageDTO;
import br.com.escoladabiblia.util.pagination.BootgridRequest;
import br.com.escoladabiblia.util.pagination.BootgridResponse;

@Controller
@RequestMapping("/alunos-presidios")
public class AlunosPresidiosController extends BaseController {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private PresidioService presidioService;

	@GetMapping({ "", "/" })
	public String index(Model model) {

		model.addAttribute("aluno", Aluno.builder().build());
		model.addAttribute("presidiario", Presidiario.builder().build());
		model.addAttribute("presidios", presidioService.findAll());

		return "alunos/presidios/index";
	}

	@PostMapping(path = "listar")
	public @ResponseBody BootgridResponse<AlunoPresidioDTO> listar(@ModelAttribute BootgridRequest bootgridRequest) {

		return alunoService.findAlunosPresidiosByNomeOrMatriculaOrNomePresidio(bootgridRequest);
	}

	@PostMapping(path = "editar")
	public @ResponseBody Aluno editar(@RequestBody Long id) {

		return alunoService.editar(id);
	}

	@PostMapping(path = "salvar")
	public @ResponseBody MessageDTO salvar(@Valid @ModelAttribute Aluno aluno,
			@Valid @ModelAttribute Presidiario presidiario) {

		alunoService.salvar(aluno, presidiario);

		return super.getSuccessMessage("sucesso.aluno.salvo");
	}

}
