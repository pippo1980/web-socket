/**
 *
 */
package com.rensea.message.spi;

/**
 * @author pippo
 *
 */
public class MessageException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 6704048541543993121L;

	/**
	 *
	 */
	public MessageException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public MessageException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MessageException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MessageException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
