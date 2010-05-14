/**
 * project : flashpush-server
 * user created : pippo
 * date created : 2009-8-13 - 下午12:23:24
 */
package com.rensea.message.server.tcp.connection.handler;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.channels.ClosedChannelException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.IConnectExceptionHandler;
import org.xsocket.connection.IConnectHandler;
import org.xsocket.connection.IDataHandler;
import org.xsocket.connection.IDisconnectHandler;
import org.xsocket.connection.INonBlockingConnection;

/**
 * @since 2009-8-13
 * @author pippo
 */
@Component("serverHandler")
public class DelegateHandler implements IConnectHandler, IDisconnectHandler, IDataHandler,
		IConnectExceptionHandler {

	@Override
	public boolean onConnect(INonBlockingConnection connection) throws IOException,
			BufferUnderflowException, MaxReadSizeExceededException {
		return this.connectHandler.onConnect(connection);
	}

	@Override
	public boolean onDisconnect(INonBlockingConnection connection) throws IOException {
		return this.disconnectHandler.onDisconnect(connection);
	}

	@Override
	public boolean onData(INonBlockingConnection connection) throws IOException,
			BufferUnderflowException, ClosedChannelException, MaxReadSizeExceededException {
		return this.dataHandler.onData(connection);
	}

	@Override
	public boolean onConnectException(INonBlockingConnection connection, IOException ioe)
			throws IOException {
		return this.connectExceptionHandler.onConnectException(connection, ioe);
	}

	@Resource(name = "connectHandler")
	private ConnectHandler connectHandler;

	@Resource(name = "disconnectHandler")
	private DisconnectHandler disconnectHandler;

	@Resource(name = "dataHandler")
	private DataHandler dataHandler;

	@Resource(name = "connectExceptionHandler")
	private ConnectExceptionHandler connectExceptionHandler;

}
