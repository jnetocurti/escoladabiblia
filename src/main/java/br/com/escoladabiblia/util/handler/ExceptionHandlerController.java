package br.com.escoladabiblia.util.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.escoladabiblia.util.dto.MessageDTO;
import br.com.escoladabiblia.util.dto.MessageDTO.TipoMensagem;
import br.com.escoladabiblia.util.dto.ValidationErrosDTO;
import br.com.escoladabiblia.util.exception.BusinessException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@Autowired
	private ExceptionHandlerHelper helper;

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);
	
	@ResponseBody
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(BusinessException.class)
	public MessageDTO handleBusinessException(final BusinessException ex) {

		return new MessageDTO(TipoMensagem.ERROR, helper.getMessageFromProperties(ex));
	}
	
	/**
	 * Intercepta erros causados na validação dos dados enviados nas requests
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ValidationErrosDTO handleBadRequest(BindException validException) {
		
		final BindingResult result = validException.getBindingResult();

		final List<FieldError> fieldErrors = result.getFieldErrors();

		return helper.processFieldErrors(fieldErrors);
	}
	
	/**
	 * Intercepta qualquer exceção não tratada e lançada pelo no sistema
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public MessageDTO handleInternalError(final Exception ex) {

		final String msg = getLocalizedMessage(ex);

		LOGGER.error(msg, ex);

		return new MessageDTO(TipoMensagem.ERROR, msg);
	}
	
	/**
	 * @param ex - a exceção capturada
	 * @return Localized Message ou Message ou String vazia caso não exista mensagem.
	 */
	private String getLocalizedMessage(final Exception ex) {

		final String msg = ex.getLocalizedMessage() != null ? ex.getLocalizedMessage()
				: ex.getMessage() != null ? ex.getMessage() : "";

		return msg;
	}

}
