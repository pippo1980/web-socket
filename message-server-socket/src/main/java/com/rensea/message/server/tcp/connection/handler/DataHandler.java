package com.rensea.message.server.tcp.connection.handler;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.channels.ClosedChannelException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.IDataHandler;
import org.xsocket.connection.INonBlockingConnection;

import com.rensea.message.server.tcp.connection.ConnectionHelper;
import com.rensea.message.server.tcp.connection.filter.ConnectionFilter;

public class DataHandler implements IDataHandler {

	private Logger logger = LoggerFactory.getLogger(DataHandler.class);

	public boolean onData(INonBlockingConnection connection) throws IOException, BufferUnderflowException,
			ClosedChannelException, MaxReadSizeExceededException {
		try {
			String message = ConnectionHelper.read(connection);

			if (message == null) {
				return true;
			}

			if (this.logger.isInfoEnabled()) {
				this.logger.info("new message:" + message);
			}

			if (message.endsWith("\0")) {
				message = message.substring(0, message.length() - 1);
			}

			boolean processed = false;
			for (ConnectionFilter filter : this.filters) {
				processed = filter.doFilter(message, connection);
				if (processed) {
					break;
				}
			}

			/* 未处理消息,直接返回 */
			if (!processed) {
				ConnectionHelper.write(connection, message);
			}
		} catch (Exception e) {
			this.logger.error("resovle data due to error", e);
			throw new RuntimeException(e);
		}
		return true;
	}

	private List<ConnectionFilter> filters;

	public void setFilters(List<ConnectionFilter> filters) {
		this.filters = filters;
	}

}
