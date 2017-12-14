package br.com.escoladabiblia.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.escoladabiblia.service.ImportacaoPresidiosService;
import br.com.escoladabiblia.util.exception.BusinessException;

/**
 * @deprecated escoladabiblia 1.0 - O processo de importação dos dados legados
 *             atualmente salvos em planílhas do Excel não mais existirá nas
 *             próximas versões do sistema, tendo apenas o propósito específico
 *             de facilitar o cadastro/setup destas informações.
 */
@Controller
@RequestScope
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

		try {

			importacaoService.importPresidiosFromXLSXFile(file.getInputStream());

			redirectAttributes.addFlashAttribute("message",
					super.getSuccessMessage("sucesso.presidio.importacao.importado").getMessage());

		} catch (BusinessException e) {

			redirectAttributes.addFlashAttribute("error", super.getErrorMessage(e.getKey()).getMessage());
		}

		return "redirect:/";
	}

}
