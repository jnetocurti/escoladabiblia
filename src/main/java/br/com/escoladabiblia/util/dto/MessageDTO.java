package br.com.escoladabiblia.util.dto;

public class MessageDTO {

	private final TipoMensagem type;

	private final String message;

	public MessageDTO(TipoMensagem type, String message) {
		this.type = type;
		this.message = message;
	}

	public TipoMensagem getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public enum TipoMensagem {
		ERROR, SUCCESS
	}

}
