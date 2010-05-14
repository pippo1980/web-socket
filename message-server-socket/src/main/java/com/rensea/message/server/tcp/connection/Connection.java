/**
 *
 */
package com.rensea.message.server.tcp.connection;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;

import org.xsocket.connection.INonBlockingConnection;

import com.rensea.message.dto.Message;

/**
 * @author pippo
 */
public interface Connection {

	boolean isValid();

	String getId();

	String getUserId();

	void setUserId(String userId);

	void setExecutorService(ScheduledExecutorService executorService);

	INonBlockingConnection getPayload();

	String read() throws IOException;

	void write(String message) throws IOException;

	void write(Message message) throws IOException;

	void destory();

	long getConnectionTime();

	long getConnectedPeriod();

	ConnectionMetaData getMetaData();

}
