package br.com.escoladabiblia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.escoladabiblia.service.AtividadesEstudoService;
import br.com.escoladabiblia.util.dto.AtividadeEstudoDTO;
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

	@PostMapping(path = "adicionar-atividade")
	public @ResponseBody EdicaoAtividadesEstudoDTO adicionarAtividade(@Valid @ModelAttribute AtividadeEstudoDTO atividadeEstudo) {

		atividadesEstudoService.adicionarAtividade(atividadeEstudo.getIdAluno(), atividadeEstudo.getDataProximoEnvio(),
				atividadeEstudo.getIdMaterial());

		return atividadesEstudoService.obterAtividadesEstudoAlunoParaEdicao(atividadeEstudo.getIdAluno());
	}

}
