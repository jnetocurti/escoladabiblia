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
import org.springframework.web.context.annotation.RequestScope;

import br.com.escoladabiblia.model.Presidio;
import br.com.escoladabiblia.repository.EstadoRepository;
import br.com.escoladabiblia.service.PresidioService;
import br.com.escoladabiblia.util.dto.MessageDTO;
import br.com.escoladabiblia.util.pagination.BootgridRequest;
import br.com.escoladabiblia.util.pagination.BootgridResponse;

@Controller
@RequestScope
@RequestMapping("/presidios")
public class PresidiosController extends BaseController {

	@Autowired
	private PresidioService presidioService;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping({ "", "/" })
	public String index(Model model) {

		model.addAttribute("presidio", Presidio.builder().build());
		model.addAttribute("estados", estadoRepository.findAll());

		return "presidios/index";
	}

	@PostMapping(path = "listar")
	public @ResponseBody BootgridResponse<Presidio> listar(@ModelAttribute BootgridRequest bootgridRequest) {

		return presidioService.findPresidiosByNome(bootgridRequest);
	}

	@PostMapping(path = "editar")
	public @ResponseBody Presidio editar(@RequestBody Long id) {

		return presidioService.findById(id);
	}

	@PostMapping(path = "salvar")
	public @ResponseBody MessageDTO salvar(@Valid @ModelAttribute Presidio presidio) {

		presidioService.salvar(presidio);

		return super.getSuccessMessage("sucesso.presidio.salvo");
	}

}
