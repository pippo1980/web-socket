/**
 *
 */
package com.rensea.message.api;

import java.util.HashMap;
import java.util.Map;

import com.rensea.message.api.http.HttpStrategyApi;
import com.rensea.message.dto.StatusMessage;

/**
 * @author pippo
 *
 */
public class ApiCommandShell {

	private Map<Protocol, ApiFacade> strategies = new HashMap<Protocol, ApiFacade>();

	public void init() {
		strategies.put(Protocol.Http, httpApi);
	}

	public void newStatus(Protocol protocol, StatusMessage status) throws ApiException {
		ApiFacade api = this.strategies.get(protocol);
		if (api == null)
			throw new ApiException("can find api support with protocol:" + protocol);
		api.newStatus(status);
	}

	public StatusMessage getStatus(Protocol protocol, String id) throws ApiException {
		ApiFacade api = this.strategies.get(protocol);
		if (api == null)
			throw new ApiException("can find api support with protocol:" + protocol);
		return api.getStatus(id);
	}

	private HttpStrategyApi httpApi;

	public void setHttpApi(HttpStrategyApi httpApi) {
		this.httpApi = httpApi;
	}

	public enum Protocol {

		Http

	}

}
