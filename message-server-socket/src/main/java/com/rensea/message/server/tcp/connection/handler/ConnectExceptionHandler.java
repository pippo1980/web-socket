package com.rensea.message.server.tcp.connection.handler;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xsocket.connection.IConnectExceptionHandler;
import org.xsocket.connection.INonBlockingConnection;

import com.rensea.message.server.spi.ListenerRegistor;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.server.tcp.connection.ConnectionManager;

@Component("connectExceptionHandler")
public class ConnectExceptionHandler implements IConnectExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(ConnectExceptionHandler.class);

	@Override
	public boolean onConnectException(INonBlockingConnection connection, IOException ioe)
			throws IOException {
		logger.error("connection:" + connection + " duto error", ioe);

		Connection con = connectionManager.getConnection(connection.getId());
		this.listenerRegistor.deregist(con);
		this.connectionManager.removeConnection(connection.getId());
		return false;
	}

	@Resource
	private ListenerRegistor listenerRegistor;

	@Resource
	private ConnectionManager connectionManager;

}
