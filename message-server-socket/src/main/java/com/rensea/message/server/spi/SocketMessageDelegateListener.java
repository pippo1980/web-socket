/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-30 - 上午11:32:39
 */
package com.rensea.message.server.spi;

import java.util.Map;

import com.rensea.message.dto.Message;
import com.rensea.message.server.spi.messagehandler.MessageHandler;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageException;

/**
 * @since 2009-11-30
 * @author pippo
 */
public class SocketMessageDelegateListener extends AbstractMessageListener<Message> {

	public SocketMessageDelegateListener(Connection connection) {
		super(connection);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void handle(Message message) throws MessageException {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("the user:" + this.getConnection().getUserId() + " subscribed message type is:"
					+ this.connection.getMetaData().getSubscribed_message_types());
		}

		for (String message_type : this.message_handler_mapping.keySet()) {
			MessageHandler handler = this.message_handler_mapping.get(message_type);
			if (!handler.isSupport(message.getMessageType())) {
				continue;
			}
			if (this.connection.getMetaData().is_subscribed_message(message_type)) {
				handler.handle(this.connection, message);
				return;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Map<String, MessageHandler> message_handler_mapping;

	@SuppressWarnings("unchecked")
	public void setMessage_handler_mapping(Map<String, MessageHandler> messageHandlerMapping) {
		this.message_handler_mapping = messageHandlerMapping;
	}

}
