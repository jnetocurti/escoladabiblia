package br.com.escoladabiblia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.service.AlunoService;
import br.com.escoladabiblia.util.pagination.BootgridRequest;
import br.com.escoladabiblia.util.pagination.BootgridResponse;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@GetMapping({ "", "/" })
	public String index(Model model) {

		model.addAttribute("aluno", new Aluno());

		return "alunos/index";
	}

	@PostMapping(path = "listar")
	public @ResponseBody BootgridResponse<Aluno> listar(@ModelAttribute BootgridRequest bootgridRequest, Model model) {

		return alunoService.findByName(bootgridRequest);
	}

	@PostMapping(path = "salvar")
	public @ResponseBody Aluno salvar(@ModelAttribute Aluno aluno) {

		return alunoService.salvar(aluno);
	}

}
