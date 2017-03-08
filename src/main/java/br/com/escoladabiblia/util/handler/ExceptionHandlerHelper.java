package br.com.escoladabiblia.util.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.escoladabiblia.util.dto.ValidationErrosDTO;

/**
 * A classe utilitária {@code ExceptionHandlerHelper} é responsavel
 * por processar listas de erros de validação de dados enviados nas requests.
 * 
 * @author José Cataldo Curti Neto
 * 
 * @see br.com.escoladabiblia.util.dto.ValidationErrosDTO
 * @see br.com.escoladabiblia.util.dto.FieldErrorDTO
 */
@Component
public class ExceptionHandlerHelper {

	@Autowired
	public MessageSource messageSource;

	/**
	 * Monta um objeto {@code ValidationErrosDTO} com os erros de validações
	 * 
	 * @param fieldErrors
	 * @return validationErros
	 */
	public ValidationErrosDTO processFieldErrors(List<FieldError> fieldErrors) {

		ValidationErrosDTO validationErros = new ValidationErrosDTO();

		for (FieldError fieldError : fieldErrors) {

			String localizeErrorMessage = this.resolveLocalizedErrorMessage(fieldError);

			validationErros.addFieldError(fieldError.getField(), localizeErrorMessage);
		}

		return validationErros;
	}

	/**
	 * Resolve a mensagem para o campo levando em conta o Locale atual
	 * 
	 * @param fieldError
	 * @return mensagem de validação do campo
	 */
	private String resolveLocalizedErrorMessage(FieldError fieldError) {

		return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
	}

}
