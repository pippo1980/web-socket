/**
 *
 */
package com.rensea.message.api.http;

import net.sf.json.JSONObject;

import com.rensea.message.api.ApiException;
import com.rensea.message.dto.StatusMessage;

/**
 * @author pippo
 */
public class GetStatusCommand extends HttpCommand<StatusMessage> {

	public GetStatusCommand(String host, String resource, String statusId) {
		this.host = host;
		this.resource = resource;
		this.statusId = statusId;
	}

	private String statusId;

	@Override
	public StatusMessage execute() throws ApiException {
		String url = String.format("http://%s/%s/%s.json", this.host, this.resource, this.statusId);
		String result = HttpClientHelper.get().get(url);
		return this.assemble(result);
	}

	private StatusMessage assemble(String jsonStr) {
		JSONObject jo = JSONObject.fromObject(jsonStr);

		StatusMessage message = new StatusMessage();
		JSONObject user = jo.getJSONObject("user");
		message.setSender(user.getLong("id"));
		message.setProtect(user.getBoolean("protected"));

		message.setStatusId(jo.getLong("id"));
		message.setStatusType(jo.getString("status_type"));
		message.setSource(jo.getString("source"));
		message.setLinkTitle(jo.getString("link_title"));
		message.setOriginalUrl(jo.getString("original_url"));
		message.setText(jo.getString("text"));
		return message;
	}

	public static void main(String[] args) {
		String result = "{'id': 231737,'created_at': '2009-11-12 10:54:36 +0800','relative_date': '','text': '@pippo ddd','source': '','truncated': false,'favorited': false,'original_url': '','status_type': 'TEXT','link_title': '','link_desc': '','level': 1,'all_zt_num': 0,'favoriters': [],'user': {'id': 1294,'name': 'philip','screen_name': 'philip','profile_image_url': 'http://rensea.com/images/buddy_icon/120x120.jpg','protected': false,'created_at': '2009-08-25 16:58:36 +0800','followers_count': 8,'following_count': 7,'favourites_count': 0,'is_followed_me': 0,'is_following': 0,'score': 320}}";
		JSONObject jo = JSONObject.fromObject(result);

		StatusMessage message = new StatusMessage();
		JSONObject user = jo.getJSONObject("user");
		message.setSender(user.getLong("id"));
		message.setProtect(user.getBoolean("protected"));

		message.setStatusId(jo.getLong("id"));
		message.setStatusType(jo.getString("status_type"));
		message.setSource(jo.getString("source"));
		message.setLinkTitle(jo.getString("link_title"));
		message.setOriginalUrl(jo.getString("original_url"));
		message.setText(jo.getString("text"));

		//		System.out.println(message);
	}
}
