/**
 * project : flashpush-server
 * user created : pippo
 * date created : 2009-8-13 - 下午01:22:21
 */
package com.rensea.message.server.tcp.connection.handler;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xsocket.connection.IDisconnectHandler;
import org.xsocket.connection.INonBlockingConnection;

import com.rensea.message.server.tcp.connection.ConnectionManager;

/**
 * @since 2009-8-13
 * @author pippo
 */
@Component("disconnectHandler")
public class DisconnectHandler implements IDisconnectHandler {

	private Logger logger = LoggerFactory.getLogger(DisconnectHandler.class);

	@Override
	public boolean onDisconnect(INonBlockingConnection connection) throws IOException {
		try {
			if (this.logger.isInfoEnabled()) {
				this.logger.info("close:" + connection);
			}
			this.connectionManager.removeConnection(connection.getId());
			return false;
		} catch (Exception e) {
			this.logger.error("disconnect due to error", e);
			throw new RuntimeException(e);
		}
	}

	@Resource
	private ConnectionManager connectionManager;

}
