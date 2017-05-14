package br.com.escoladabiblia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.escoladabiblia.model.AtividadeEstudo;
import br.com.escoladabiblia.service.AtividadesEstudoService;
import br.com.escoladabiblia.util.dto.AtividadeEstudoDTO;
import br.com.escoladabiblia.util.dto.AtividadeEstudoEdicaoDTO;
import br.com.escoladabiblia.util.dto.AtividadesEstudoEdicaoDTO;
import br.com.escoladabiblia.util.exception.BusinessException;

@Controller
@RequestMapping("/atividades-estudo")
public class AtividadesEstudoController extends BaseController {

	@Autowired
	private AtividadesEstudoService atividadesEstudoService;

	@PostMapping(path = "editar-atividades")
	public @ResponseBody AtividadesEstudoEdicaoDTO editarAtividades(@RequestBody Long id) throws BusinessException {

		return atividadesEstudoService.obterAtividadesEstudoAlunoParaEdicao(id);
	}

	@PostMapping(path = "adicionar-atividade")
	public @ResponseBody AtividadesEstudoEdicaoDTO adicionarAtividade(
			@Valid @ModelAttribute AtividadeEstudoDTO atividadeEstudo) throws BusinessException {

		atividadesEstudoService.adicionarAtividade(atividadeEstudo.getIdAluno(), atividadeEstudo.getIdPostagem(),
				atividadeEstudo.getIdMaterial());

		return atividadesEstudoService.obterAtividadesEstudoAlunoParaEdicao(atividadeEstudo.getIdAluno());
	}
	
	@PostMapping(path = "editar-atividade")
	public @ResponseBody AtividadeEstudo editarAtividade(@RequestBody Long id) throws BusinessException {

		return atividadesEstudoService.obterAtividadePorId(id);
	}
	
	@PostMapping(path = "atualizar-atividade")
	public @ResponseBody AtividadesEstudoEdicaoDTO atualizarAtividade(@Valid @ModelAttribute AtividadeEstudoEdicaoDTO atividade) 
			throws BusinessException {

		AtividadeEstudo atividadeEstudo = atividadesEstudoService.atualizarAtividade(atividade);

		return atividadesEstudoService.obterAtividadesEstudoAlunoParaEdicao(atividadeEstudo.getAluno().getId());
	}
	
	@PostMapping(path = "deletar-atividade")
	public @ResponseBody AtividadesEstudoEdicaoDTO deletarAtividade(@RequestParam Long id) throws BusinessException {
		
		AtividadeEstudo atividadeEstudo = atividadesEstudoService.obterAtividadePorId(id);
		
		Long idAluno = atividadeEstudo.getAluno().getId();

		atividadesEstudoService.deletarAtividade(atividadeEstudo);

		return atividadesEstudoService.obterAtividadesEstudoAlunoParaEdicao(idAluno);
	}
	
	@PostMapping(path = "visualizar-atividade")
	public String visualizarAtividade(@RequestBody Long id, Model model) {

		model.addAttribute("atividade", atividadesEstudoService.obterAtividadePorId(id));
		
		return "alunos/common-fragments/modal-visualizacao-atividade";
	}

}
