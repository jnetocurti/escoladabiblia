package br.com.escoladabiblia.util.dto;

import java.io.Serializable;

/**
 * A classe {@code FieldErrorDTO} representa atributos inválidos, normalmente
 * de entidades persistentes do sistema, e suas respectivas mensagens de erro.
 * 
 * @author José Cataldo Curti Neto
 */
public class FieldErrorDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5171772160609789632L;

	private String field;

	private String message;

	/**
	 * @param field - O campo inválido
	 * @param message - A mensagem correspondente
	 */
	public FieldErrorDTO(String field, String message) {
		this.field = field;
		this.message = message;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldErrorDTO other = (FieldErrorDTO) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		return true;
	}
	
}
