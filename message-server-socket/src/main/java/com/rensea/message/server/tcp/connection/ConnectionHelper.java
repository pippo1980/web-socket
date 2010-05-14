/**
 * project : server
 * user created : pippo
 * date created : 2009-8-16 - 上午11:02:18
 */
package com.rensea.message.server.tcp.connection;

import java.io.IOException;

import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xsocket.connection.INonBlockingConnection;

/**
 * @since 2009-8-16
 * @author Administrator
 */
public final class ConnectionHelper {

	private static Logger logger = LoggerFactory.getLogger(ConnectionHelper.class);

	public static String read(INonBlockingConnection connection) throws IOException {
		int length = connection.available();
		if (length <= 0) {
			return null;
		}
		String message = null;
		try {
			message = new String(connection.readBytesByLength(length));

			if (logger.isDebugEnabled()) {
				logger.debug("the read message is:" + message);
			}

		} catch (Exception e) {
			logger.error("read message due to error and close connection" + connection, e);
			connection.close();
		}

		return message;
	}

	public static void write(INonBlockingConnection connection, String message) throws IOException {
		if (!connection.isOpen()) {
			logger.warn("the connection:" + connection + " is not open!");
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("the write message is:" + message);
		}

		try {
			connection.setAutoflush(true);
			connection.write(message.getBytes(CharEncoding.UTF_8));
			//			connection.write("\0".getBytes());
			//			connection.flush();
		} catch (Exception e) {
			logger.error("write message due to error and close connection" + connection, e);
			connection.setIdleTimeoutMillis(1);
			connection.close();
		}
	}
}
