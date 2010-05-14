/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-2 - 上午10:53:23
 */
package com.rensea.message.server.tcp.connection.command;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xsocket.connection.INonBlockingConnection;

import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.server.tcp.connection.ConnectionManager;

/**
 * @since 2009-12-2
 * @author pippo
 */
@Component("subscriptionCommandHandler")
public class SubscriptionCommandHandler implements CommandHandler {

	private Logger logger = LoggerFactory.getLogger(SubscriptionCommandHandler.class);

	@Override
	public void handle(INonBlockingConnection connection, JSONObject command) throws Exception {
		Connection con = this.connectionManager.getConnection(connection.getId());
		if (con == null) {
			return;
		}

		try {
			JSONArray messageTypes = command.getJSONArray("messageTypes");
			for (int i = 0; i < messageTypes.size(); i++) {
				con.getMetaData().subscribe_message(messageTypes.getString(i));
			}
		} catch (Exception e) {
			this.logger.error("subscription message type due to error:", e);
		}
	}

	@Resource
	private ConnectionManager connectionManager;

}
