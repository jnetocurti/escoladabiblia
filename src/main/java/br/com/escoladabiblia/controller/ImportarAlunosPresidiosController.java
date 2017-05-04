package br.com.escoladabiblia.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.escoladabiblia.service.ImportacaoAlunosPresidiosService;
import br.com.escoladabiblia.util.exception.BusinessException;

@Controller
@RequestMapping("/importacao/alunos-presidios")
public class ImportarAlunosPresidiosController extends BaseController {

	@Autowired
	private ImportacaoAlunosPresidiosService importacaoAlunosPresidios;

	@GetMapping({ "", "/" })
	public String listUploadedFiles() throws IOException {

		return "importacao/alunos-presidios/index";
	}

	@PostMapping("importar-dados")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException {

		try {

			importacaoAlunosPresidios.importAlunosPresidiosFromXLSXFile(file.getInputStream(), file.getOriginalFilename());

			redirectAttributes.addFlashAttribute("message",
					super.getSuccessMessage("sucesso.aluno.presidio.importado").getMessage());

		} catch (BusinessException e) {

			redirectAttributes.addFlashAttribute("error", super.getErrorMessage(e.getKey()).getMessage());
		}

		return "redirect:/";
	}

}
