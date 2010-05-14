/**
 *
 */
package com.rensea.message.server.spi.dto.converter;

import net.sf.json.JSONObject;

import com.rensea.message.dto.Message;
import com.rensea.message.dto.MessageType;
import com.rensea.message.dto.StatusMessage;

/**
 * @author pippo
 */
public class StatusMessageConverter implements MessageConverter {

	@Override
	public String convert(Message message) {
		StatusMessage statusMessage = (StatusMessage) message;
		JSONObject m = new JSONObject();
		m.put("messageType", statusMessage.getMessageType().toString());
		m.put("statusId", statusMessage.getStatusId());
		m.put("statusType", statusMessage.getStatusType());
		return m.toString();
	}

	@Override
	public MessageType getSupportType() {
		return MessageType.STATUS;
	}

	@Override
	public boolean support(MessageType messageType) {
		return this.getSupportType().equals(messageType);
	}

}
