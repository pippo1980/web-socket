/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-30 - 上午11:59:26
 */
package com.rensea.message.server.spi.messagehandler;

import org.springframework.stereotype.Component;

import com.rensea.message.dto.Message;
import com.rensea.message.dto.MessageType;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageException;
import com.sirius.core.utils.ArrayUtils;

/**
 * @since 2009-11-30
 * @author pippo
 */
@Component
public class DefaultMessageHandler extends MessageHandlerTemplate<Message> {

	@Override
	public void handle(Connection connection, Message message) throws MessageException {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("resolve message:" + message + ", the listener is:" + this);
		}

		if (ArrayUtils.contains(message.getReceiverIds(), connection.getUserId())) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("the user id is:" + connection.getUserId() + ", the message is:" + message.toJson());
			}
			this.flush(connection, message);
		} else {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("user id is:" + connection.getUserId() + ", not in reveiver:"
						+ ArrayUtils.toString(message.getReceiverIds()));
			}
		}
	}

	@Override
	public boolean isSupport(MessageType messageType) {
		return true;
	}

}
