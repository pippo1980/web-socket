/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-30 - 下午12:05:31
 */
package com.rensea.message.server.spi.messagehandler;

import com.rensea.message.dto.Message;
import com.rensea.message.dto.MessageType;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageException;

/**
 * @since 2009-11-30
 * @author pippo
 */
public interface MessageHandler<M extends Message> {

	void handle(Connection connection, M message) throws MessageException;

	boolean isSupport(MessageType messageType);

}
