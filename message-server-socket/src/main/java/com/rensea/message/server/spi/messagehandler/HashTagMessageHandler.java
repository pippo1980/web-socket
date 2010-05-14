/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-24 - 上午11:09:31
 */
package com.rensea.message.server.spi.messagehandler;

import org.springframework.stereotype.Component;

import com.rensea.message.dto.HashTagMessage;
import com.rensea.message.dto.MessageType;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageException;

/**
 * @since 2009-12-24
 * @author pippo
 */
@Component
public class HashTagMessageHandler extends MessageHandlerTemplate<HashTagMessage> {

	//	private static Logger logger = LoggerFactory.getLogger(HashTagMessageHandler.class);

	@Override
	public void handle(Connection connection, HashTagMessage message) throws MessageException {
		//		if ("292".equals(connection.getUserId())) {
		//			logger.warn("hashtag###" + connection.toString() + "##" + message.getTag());
		//			logger.warn("hashtag###" + connection.getMetaData() + "##" + message.getTag());
		//			logger.warn("hashtag###" + connection.getMetaData().getSubscribed_hash_tags().contains(message.getTag()));
		//		}
		if (connection.getMetaData().is_subscribed_tag(message.getTag())) {
			this.flush(connection, message);
		}
	}

	@Override
	public boolean isSupport(MessageType messageType) {
		return MessageType.HASH_TAG.equals(messageType);
	}
}
