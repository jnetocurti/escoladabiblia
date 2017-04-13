package br.com.escoladabiblia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.escoladabiblia.service.AtividadesEstudoService;
import br.com.escoladabiblia.util.dto.EdicaoAtividadesEstudoDTO;

@Controller
@RequestMapping("/atividades-estudo")
public class AtividadesEstudoController extends BaseController {

	@Autowired
	private AtividadesEstudoService atividadesEstudoService;

	@PostMapping(path = "editar-atividades")
	public @ResponseBody EdicaoAtividadesEstudoDTO editarAtividades(@RequestBody Long id) {

		return atividadesEstudoService.obterAtividadesEstudoAlunoParaEdicao(id);
	}

}
