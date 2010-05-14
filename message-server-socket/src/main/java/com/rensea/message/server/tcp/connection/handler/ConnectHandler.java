/**
 * project : flashpush-server
 * user created : pippo
 * date created : 2009-8-13 - 下午12:45:53
 */
package com.rensea.message.server.tcp.connection.handler;

import java.io.IOException;
import java.nio.BufferUnderflowException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.IConnectHandler;
import org.xsocket.connection.INonBlockingConnection;

import com.rensea.message.server.monitor.ConnectionManagerMBean;

/**
 * @since 2009-8-13
 * @author pippo
 */
@Component("connectHandler")
public class ConnectHandler implements IConnectHandler {

	private Logger logger = LoggerFactory.getLogger(ConnectHandler.class);

	public boolean onConnect(INonBlockingConnection connection) throws IOException, BufferUnderflowException,
			MaxReadSizeExceededException {

		if (this.logger.isInfoEnabled()) {
			this.logger.info("new connection:" + connection + " ,the remote address is:"
					+ connection.getRemoteAddress());
		}

		this.connectionManagerMBean.onConnected(connection.toString());
		return false;
	}

	@Resource
	private ConnectionManagerMBean connectionManagerMBean;
}
