package br.com.escoladabiblia.util.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A classe {@code ValidationErrosDTO} representa a mensagem a ser retornada
 * para o cliente quando ocorreres erros de validação dos dados enviados nas
 * requests.
 * 
 * @author José Cataldo Curti Neto
 * 
 * @see br.com.escoladabiblia.util.dto.FieldErrorDTO
 */
public class ValidationErrosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6992989641503430080L;

	private Set<FieldErrorDTO> errors = new HashSet<>();

	/**
	 * @param field
	 * @param message
	 */
	public void addFieldError(String field, String message) {
		
		errors.add(new FieldErrorDTO(field, message));
	}

	/**
	 * @return the errors
	 */
	public Set<FieldErrorDTO> getErrors() {
		
		return Collections.unmodifiableSet(errors);
	}

}
