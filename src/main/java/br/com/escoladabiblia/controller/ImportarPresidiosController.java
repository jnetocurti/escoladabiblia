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

import br.com.escoladabiblia.service.ImportacaoPresidiosService;

@Controller
@RequestMapping("/importacao/presidios")
public class ImportarPresidiosController extends BaseController {

	@Autowired
	private ImportacaoPresidiosService importacaoService;

	@GetMapping({ "", "/" })
	public String listUploadedFiles() throws IOException {

		return "importacao/presidios/index";
	}

	@PostMapping("importar-dados")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException {

		redirectAttributes.addFlashAttribute("message",
				super.getSuccessMessage("sucesso.presidio.importado").getMessage());

		importacaoService.importPresidiosFromXLSXFile(file.getInputStream());

		return "redirect:/";
	}

}
