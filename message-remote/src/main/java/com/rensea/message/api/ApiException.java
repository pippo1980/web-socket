/**
 *
 */
package com.rensea.message.api;

/**
 * @author pippo
 *
 */
public class ApiException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 692326860531384118L;

	/**
	 *
	 */
	public ApiException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ApiException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ApiException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApiException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
