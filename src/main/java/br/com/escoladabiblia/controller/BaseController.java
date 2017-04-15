package br.com.escoladabiblia.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import br.com.escoladabiblia.util.dto.MessageDTO;
import br.com.escoladabiblia.util.dto.MessageDTO.TipoMensagem;

@Controller
public abstract class BaseController {

	@Autowired
	private MessageSource messageSource;

	protected MessageDTO getSuccessMessage(String key) {
		
		return this.getSuccessMessage(key, new Object(){});
	}

	protected MessageDTO getSuccessMessage(String key, Object... args) {

		return new MessageDTO(TipoMensagem.SUCCESS, messageSource.getMessage(key, args, Locale.getDefault()));
	}
	
	protected ResponseEntity<byte[]> getPDFResponse(final byte[] envelopes, String filename) {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.parseMediaType("application/pdf"));

		headers.set("Content-Disposition", "inline; filename=" + filename);

		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

		return new ResponseEntity<byte[]>(envelopes, headers, HttpStatus.OK);
	}

}
