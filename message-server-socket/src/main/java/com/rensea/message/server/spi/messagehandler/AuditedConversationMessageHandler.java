/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-17 - 下午01:51:27
 */
package com.rensea.message.server.spi.messagehandler;

import org.springframework.stereotype.Component;

import com.rensea.message.dto.AuditedConversationMessage;
import com.rensea.message.dto.MessageType;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageException;

/**
 * @since 2009-12-17
 * @author pippo
 */
@Component
public class AuditedConversationMessageHandler extends MessageHandlerTemplate<AuditedConversationMessage> {

	@Override
	public void handle(Connection connection, AuditedConversationMessage message) throws MessageException {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("resolve audited conversation message:" + message + ", the listener is:" + this);
		}
		this.flush(connection, message);
	}

	@Override
	public boolean isSupport(MessageType messageType) {
		return MessageType.AUDITED_CONVERSATION_MESSAGE.equals(messageType);
	}

}
