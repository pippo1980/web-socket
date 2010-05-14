/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-30 - 上午11:46:37
 */
package com.rensea.message.server.spi.messagehandler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rensea.message.dto.Message;
import com.rensea.message.server.tcp.connection.Connection;

/**
 * @since 2009-11-30
 * @author pippo
 */
public abstract class MessageHandlerTemplate<M extends Message> implements MessageHandler<M> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected void flush(Connection connection, Message message) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("resolve message:" + message + ", the listener is:" + this);
		}
		try {
			connection.write(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
