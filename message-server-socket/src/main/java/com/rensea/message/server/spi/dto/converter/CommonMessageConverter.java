/**
 *
 */
package com.rensea.message.server.spi.dto.converter;

import com.rensea.message.dto.Message;
import com.rensea.message.dto.MessageType;

/**
 * @author pippo
 */
public class CommonMessageConverter implements MessageConverter {

	@Override
	public String convert(Message message) {
		return message.toJson();
	}

	@Override
	public MessageType getSupportType() {
		return null;
	}

	@Override
	public boolean support(MessageType messageType) {
		return true;
	}

}
