/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-30 - 下午12:40:00
 */
package com.rensea.message.server.spi.messagehandler;

import org.springframework.stereotype.Component;

import com.rensea.message.dto.ConversationReadedMessage;
import com.rensea.message.dto.MessageType;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageException;

/**
 * @since 2009-11-30
 * @author pippo
 */
@Component
public class ConversationReadedMessageHandler extends MessageHandlerTemplate<ConversationReadedMessage> {

	@Override
	public void handle(Connection connection, ConversationReadedMessage message) throws MessageException {
		if (connection.getUserId().equals(String.valueOf(message.getUserId()))) {
			this.flush(connection, message);
		}
	}

	@Override
	public boolean isSupport(MessageType messageType) {
		return MessageType.CONVERSATION_READED_MESSAGE.equals(messageType);
	}
}
