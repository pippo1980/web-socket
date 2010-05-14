/**
 *
 */
package com.rensea.message.api.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.lang.StringUtils;

import com.rensea.message.api.ApiException;
import com.rensea.message.dto.StatusMessage;

/**
 * @author pippo
 */
public class NewStatusCommand extends HttpCommand<String> {

	public NewStatusCommand(String host, String resource, StatusMessage status) {
		this.host = host;
		this.resource = resource;
		this.status = status;
	}

	private StatusMessage status;

	@Override
	public String execute() throws ApiException {
		String url = String.format("http://%s/%s", this.host, this.resource);
		Map<String, String> params = new HashMap<String, String>();

		params.put("text", this.status.getText());
		params.put("source", this.status.getSource());
		params.put("uid", this.status.getFromId());
		if (StringUtils.isNotBlank(this.status.getReplyToId())) {
			params.put("in_reply_to_status_id", this.status.getReplyToId());
		}
		return HttpClientHelper.get().post(url, params, new AuthScope(this.host, 80, AuthScope.ANY_REALM),
				new UsernamePasswordCredentials(this.super_user_id, this.super_user_password));
	}

	private String super_user_id = "rensea";

	private String super_user_password = "just!@#456";

	public void setSuper_user_id(String superUserId) {
		this.super_user_id = superUserId;
	}

	public void setSuper_user_password(String superUserPassword) {
		this.super_user_password = superUserPassword;
	}

	public static void main(String[] args) throws ApiException {
		StatusMessage message = new StatusMessage();
		message.setText("hi");
		message.setSource("Gtalk");
		message.setSender(292L);
		NewStatusCommand command = new NewStatusCommand("api.renjian.com", "statuses/update.json", message);
		command.execute();
	}
}
