package br.com.escoladabiblia.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import br.com.escoladabiblia.util.dto.MessageDTO;
import br.com.escoladabiblia.util.dto.MessageDTO.TipoMensagem;

@Controller
public abstract class BaseController {

	@Autowired
	private MessageSource messageSource;

	protected MessageDTO returnSuccess(String key) {
		
		return this.returnSuccess(key, new Object(){});
	}

	protected MessageDTO returnSuccess(String key, Object... args) {

		return new MessageDTO(TipoMensagem.SUCCESS, messageSource.getMessage(key, args, Locale.getDefault()));
	}

}
