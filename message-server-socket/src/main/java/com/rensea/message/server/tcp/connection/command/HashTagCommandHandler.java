/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-24 - 下午02:33:27
 */
package com.rensea.message.server.tcp.connection.command;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import org.xsocket.connection.INonBlockingConnection;

import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.server.tcp.connection.ConnectionManager;

/**
 * @since 2009-12-24
 * @author pippo
 */
@Component("hashTagCommandHandler")
public class HashTagCommandHandler implements CommandHandler {

	//	private static Logger logger = LoggerFactory.getLogger(HashTagCommandHandler.class);

	private final static String ACTION_ADD = "add";

	private final static String ACTION_REMOVE = "remove";

	private final static String ACTION_RESET = "reset";

	/* {command:'hashtag',action:'add',tags:['XXXX','XXXX']} */
	/* {command:'hashtag',action:'remove',tags:['XXXX','XXXX']} */
	/* {command:'hashtag',action:'reset',tags:['XXXX','XXXX']} */
	@Override
	public void handle(INonBlockingConnection connection, JSONObject command) throws Exception {
		Connection con = this.connectionManager.getConnection(connection.getId());
		if (con == null) {
			return;
		}

		JSONArray tags = command.getJSONArray("tags");
		if (tags.isEmpty()) {
			return;
		}

		String action = command.getString("action");
		if (ACTION_ADD.equalsIgnoreCase(action)) {
			for (int i = 0; i < tags.size(); i++) {
				con.getMetaData().subscribe_hash_tag(tags.getString(i));
			}
		} else if (ACTION_REMOVE.equalsIgnoreCase(action)) {
			for (int i = 0; i < tags.size(); i++) {
				con.getMetaData().unsubscribe_e_hash_tag(tags.getString(i));
			}
		} else if (ACTION_RESET.equalsIgnoreCase(action)) {
			con.getMetaData().getSubscribed_hash_tags().clear();
			for (int i = 0; i < tags.size(); i++) {
				con.getMetaData().subscribe_hash_tag(tags.getString(i));
			}
		}

		//		logger.warn("hashtag###" + con.toString());
	}

	@Resource
	private ConnectionManager connectionManager;

}
