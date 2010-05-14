/**
 *
 */
package com.rensea.message.api.http;

import com.rensea.message.api.Command;

/**
 * @author pippo
 *
 */
public abstract class HttpCommand<Result> implements Command<Result> {

	protected String host;

	protected String resource;

	public String getHost() {
		return host;
	}

	public String getResource() {
		return resource;
	}

}
