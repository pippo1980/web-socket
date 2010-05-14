/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-2 - 上午10:47:49
 */
package com.rensea.message.server.tcp.connection.command;

import net.sf.json.JSONObject;

import org.xsocket.connection.INonBlockingConnection;

/**
 * @since 2009-12-2
 * @author pippo
 */
public interface CommandHandler {

	void handle(INonBlockingConnection connection, JSONObject command) throws Exception;

}
