/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-4 - 上午11:04:52
 */
package com.rensea.message.server.spi.dto.converter;

import net.sf.json.JSONObject;

import com.rensea.message.dto.Message;
import com.rensea.message.dto.MessageType;
import com.rensea.message.dto.StickStatusMessage;

/**
 * @since 2009-12-4
 * @author pippo
 */
public class StickStatusMessageConverter implements MessageConverter {

	@Override
	public String convert(Message message) {
		StickStatusMessage stickStatusMessage = (StickStatusMessage) message;
		JSONObject m = new JSONObject();
		m.put("messageType", stickStatusMessage.getMessageType().name());
		m.put("status_id", stickStatusMessage.getStatus_id());
		m.put("availability", Boolean.TRUE.equals(stickStatusMessage.getAvailability()));
		m.put("sticker_name", stickStatusMessage.getSticker_name());
		m.put("id", stickStatusMessage.getId());
		return m.toString();
	}

	@Override
	public MessageType getSupportType() {
		return MessageType.STICK_STATUS;
	}

	@Override
	public boolean support(MessageType messageType) {
		return this.getSupportType().equals(messageType);
	}

}
