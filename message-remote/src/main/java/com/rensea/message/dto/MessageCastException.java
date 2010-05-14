package com.rensea.message.dto;

public class MessageCastException extends RuntimeException{

	private static final long serialVersionUID = -583553622781624833L;

	public MessageCastException() {
		super();
	}

	public MessageCastException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageCastException(String message) {
		super(message);
	}

	public MessageCastException(Throwable cause) {
		super(cause);
	}

	
}
