/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-2 - 上午10:55:05
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
 * @since 2009-12-2
 * @author pippo
 */
@Component("unsubscriptonCommandHandler")
public class UnsubscriptonCommandHandler implements CommandHandler {

	@Override
	public void handle(INonBlockingConnection connection, JSONObject command) throws Exception {
		Connection con = this.connectionManager.getConnection(connection.getId());
		if (con == null) {
			return;
		}

		JSONArray messageTypes = command.getJSONArray("messageTypes");
		for (int i = 0; i < messageTypes.size(); i++) {
			con.getMetaData().unsubscribe_message(messageTypes.getString(i));
		}
	}

	@Resource
	private ConnectionManager connectionManager;

}
