package br.com.escoladabiblia.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.escoladabiblia.model.Postagem;
import br.com.escoladabiblia.service.PostagemService;
import br.com.escoladabiblia.util.dto.MessageDTO;
import br.com.escoladabiblia.util.dto.PeriodoDTO;
import br.com.escoladabiblia.util.exception.BusinessException;
import net.sf.jasperreports.engine.JRException;

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

	@GetMapping(path = "gerenciamento")
	public String gerenciarIndex(Model model) throws BusinessException {

		model.addAttribute("periodo", new PeriodoDTO());

		return "postagens/gerenciamento/index";
	}

	@GetMapping(path = "gerenciamento/postagem-em-aberto")
	public @ResponseBody List<Postagem> listarPostagemEmAberto() throws BusinessException {

		return Arrays.asList(postagemService.obterPostagemEmAberto());
	}

	@PostMapping(path = "gerenciamento/listar")
	public @ResponseBody List<Postagem> listar(@Valid @ModelAttribute PeriodoDTO periodo) throws BusinessException {

		return postagemService.listarPorPeriodo(periodo);
	}

	@GetMapping(path = "gerenciamento/processar/{id}/{encerrar}")
	public ResponseEntity<byte[]> processar(@PathVariable Long id, @PathVariable boolean encerrar)
			throws BusinessException, JRException, IOException {

		final byte[] envelopes = postagemService.processarPostagem(id, encerrar);

		return super.getPDFResponse(envelopes, "envelopes.pdf");
	}
	
	@GetMapping(path = "gerenciamento/relatorio/{id}")
	public ResponseEntity<byte[]> relatorio(@PathVariable Long id) throws JRException, IOException {

		final byte[] envelopes = postagemService.gerarRelatorio(id);

		return super.getPDFResponse(envelopes, "relatorio.pdf");
	}

}
