/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-2 - 上午10:56:43
 */
package com.rensea.message.server.tcp.connection.command;

import javax.annotation.Resource;

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
@Component("pingCommandHandler")
public class PingCommandHandler implements CommandHandler {

	private Logger logger = LoggerFactory.getLogger(PingCommandHandler.class);

	@Resource
	private ConnectionManager connectionManager;

	@Override
	public void handle(INonBlockingConnection connection, JSONObject command) throws Exception {
		Connection con = this.connectionManager.getConnection(connection.getId());
		if (con == null) {
			return;
		}

		/* 现在对于ping的处理只是简单的返回一个ping */
		try {
			con.write(command.toString());
		} catch (Exception e) {
			this.logger.error("ping due to error:", e);
		}

	}

}
