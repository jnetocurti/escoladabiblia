package br.com.escoladabiblia.util.exception;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String key;

	public BusinessException(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
