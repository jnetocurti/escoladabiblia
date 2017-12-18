package br.com.escoladabiblia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import br.com.escoladabiblia.model.Aluno;
import br.com.escoladabiblia.repository.EstadoRepository;
import br.com.escoladabiblia.repository.MaterialEstudoRepository;
import br.com.escoladabiblia.service.AlunoService;
import br.com.escoladabiblia.util.dto.AlunoComumDTO;
import br.com.escoladabiblia.util.dto.MessageDTO;
import br.com.escoladabiblia.util.pagination.BootgridRequest;
import br.com.escoladabiblia.util.pagination.BootgridResponse;

@Controller
@RequestScope
@RequestMapping("/alunos-comuns")
public class AlunosComunsController extends BaseController {

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private MaterialEstudoRepository materialEstudoRepository;

	@GetMapping({ "", "/{id}" })
	public String index(Model model, @PathVariable(name = "id", required = false) Long id) {

		model.addAttribute("aluno", Aluno.builder().build());
		model.addAttribute("estados", estadoRepository.findAll());
		model.addAttribute("materiais", materialEstudoRepository.findByAtivoIsTrueOrderByNumeroOrdem());
		
		if (id != null) {
			Aluno alunoPath = alunoService.editar(id);
			model.addAttribute("alunoPath", alunoPath != null ? alunoPath.getNome() : null);
		}

		return "alunos/comuns/index";
	}

	@PostMapping(path = "listar")
	public @ResponseBody BootgridResponse<AlunoComumDTO> listar(@ModelAttribute BootgridRequest bootgridRequest) {

		return alunoService.findAlunosComunsByNome(bootgridRequest);
	}

	@PostMapping(path = "editar")
	public @ResponseBody Aluno editar(@RequestBody Long id) {

		return alunoService.editar(id);
	}

	@PostMapping(path = "salvar")
	public @ResponseBody MessageDTO salvar(@Valid @ModelAttribute Aluno aluno) {

		alunoService.salvarAlunoComum(aluno);

		return super.getSuccessMessage("sucesso.aluno.salvo");
	}

}
