package br.com.escoladabiblia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.escoladabiblia.model.Postagem;
import br.com.escoladabiblia.service.PostagemService;
import br.com.escoladabiblia.util.dto.MessageDTO;
import br.com.escoladabiblia.util.exception.BusinessException;

@Controller
@RequestMapping("/postagens")
public class PostagensController extends BaseController {

	@Autowired
	private PostagemService postagemService;

	@GetMapping(path = "agendamento")
	public String agendamentoIndex(Model model) throws BusinessException {
		
		model.addAttribute("postagem", new Postagem());

		return "postagens/agendamento/index";
	}

	@PostMapping(path = "agendamento/salvar")
	public @ResponseBody MessageDTO salvar(@Valid @ModelAttribute Postagem postagem) throws BusinessException {

		postagemService.salvar(postagem);

		return super.getSuccessMessage("sucesso.postagem.salvo");
	}

}
