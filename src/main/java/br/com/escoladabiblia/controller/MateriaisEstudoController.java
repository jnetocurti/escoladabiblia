package br.com.escoladabiblia.controller;

import java.util.List;

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

import br.com.escoladabiblia.model.MaterialEstudo;
import br.com.escoladabiblia.service.MateriaisEstudoService;
import br.com.escoladabiblia.util.dto.MessageDTO;
import br.com.escoladabiblia.util.exception.BusinessException;

@Controller
@RequestScope
@RequestMapping("/materiais-estudo")
public class MateriaisEstudoController extends BaseController {

	@Autowired
	private MateriaisEstudoService materiaisEstudoService;

	@GetMapping({ "", "/" })
	public String index(Model model) {
		
		List<MaterialEstudo> materiais = materiaisEstudoService.findAll();
		
		model.addAttribute("materiais", materiais);
		model.addAttribute("material", getNewMaterial(materiais));

		return "materiais-estudo/index";
	}

	@PostMapping(path = "salvar-todos")
	public @ResponseBody MessageDTO salvarTodos(@RequestBody List<MaterialEstudo> materias)
			throws BusinessException {
		
		materiaisEstudoService.salvarTodos(materias);

		return super.getSuccessMessage("sucesso.materiais.salvos");
	}
	
	@PostMapping(path = "salvar")
	public @ResponseBody MessageDTO salvar(@Valid @ModelAttribute MaterialEstudo material) {
		
		materiaisEstudoService.salvar(material);
		
		return super.getSuccessMessage("sucesso.material.salvo");
	}
	
	private MaterialEstudo getNewMaterial(List<MaterialEstudo> materiais) {

		Integer numeroOrdem = !materiais.isEmpty() ? materiais.stream()
				.max((m1, m2) -> Integer.compare(m1.getNumeroOrdem(), m2.getNumeroOrdem())).get().getNumeroOrdem() + 1
				: 1;

		return MaterialEstudo.builder().withCorrigible(true).withAtivo(true).withNumeroOrdem(numeroOrdem).build();
	}
}
