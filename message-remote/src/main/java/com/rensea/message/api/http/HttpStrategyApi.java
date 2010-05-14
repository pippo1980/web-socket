/**
 *
 */
package com.rensea.message.api.http;

import com.rensea.message.api.ApiException;
import com.rensea.message.api.ApiFacade;
import com.rensea.message.dto.StatusMessage;

/**
 * @author pippo
 *
 */
public class HttpStrategyApi implements ApiFacade {

	@Override
	public void newStatus(StatusMessage status) throws ApiException {
		NewStatusCommand command = new NewStatusCommand(host, new_status_url, status);
		command.execute();
	}

	@Override
	public StatusMessage getStatus(String id) throws ApiException {
		GetStatusCommand command = new GetStatusCommand(host, get_status_url, id);
		return command.execute();
	}

	private String host;

	public void setHost(String host) {
		this.host = host;
	}

	private String new_status_url = "statuses/update.json";

	public void setNew_status_url(String newStatusUrl) {
		new_status_url = newStatusUrl;
	}

	private String get_status_url = "statuses/show";

	public void setGet_status_url(String getStatusUrl) {
		get_status_url = getStatusUrl;
	}

}
