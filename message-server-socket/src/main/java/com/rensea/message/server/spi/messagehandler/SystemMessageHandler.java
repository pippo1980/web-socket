/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-15 - 下午04:02:45
 */
package com.rensea.message.server.spi.messagehandler;

import org.springframework.stereotype.Component;

import com.rensea.message.dto.MessageType;
import com.rensea.message.dto.SystemMessage;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageException;

/**
 * @since 2009-12-15
 * @author pippo
 */
@Component
public class SystemMessageHandler extends MessageHandlerTemplate<SystemMessage> {

	@Override
	public void handle(Connection connection, SystemMessage message) throws MessageException {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("resolve system message:" + message + ", the listener is:" + this);
		}
		this.flush(connection, message);
	}

	@Override
	public boolean isSupport(MessageType messageType) {
		return MessageType.SYSTEM.equals(messageType);
	}

}
