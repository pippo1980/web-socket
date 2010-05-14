/**
 *
 */
package com.rensea.message.server.spi.dto.converter;

import java.util.HashMap;
import java.util.Map;

import com.rensea.message.dto.Message;
import com.rensea.message.dto.MessageType;

/**
 * @author pippo
 */
public class MessageConverterUtils {

	public static String convert(Message message) {
		MessageConverter converter = converters.get(message.getMessageType());
		if (converter == null) {
			converter = DEFAULT_MESSAGECONVERTER;
		}
		return converter.convert(message);
	}

	private static MessageConverter DEFAULT_MESSAGECONVERTER = new CommonMessageConverter();

	private static Map<MessageType, MessageConverter> converters = new HashMap<MessageType, MessageConverter>() {

		private static final long serialVersionUID = -8361921406177843336L;

		{
			this.put(MessageType.STATUS, new StatusMessageConverter());
			this.put(MessageType.CONVERSATION_MESSAGE, new ConversationMessageConverter());
			this.put(MessageType.STICK_STATUS, new StickStatusMessageConverter());
		}

	};

}
