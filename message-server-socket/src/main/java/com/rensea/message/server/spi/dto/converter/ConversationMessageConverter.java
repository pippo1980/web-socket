/**
 *
 */
package com.rensea.message.server.spi.dto.converter;

import net.sf.json.JSONObject;

import com.rensea.message.dto.ConversationMessage;
import com.rensea.message.dto.Message;
import com.rensea.message.dto.MessageType;

/**
 * @author pippo
 */
public class ConversationMessageConverter implements MessageConverter {

	@Override
	public String convert(Message message) {
		ConversationMessage conversationMessage = (ConversationMessage) message;
		JSONObject m = new JSONObject();
		m.put("messageType", conversationMessage.getMessageType().toString());
		m.put("conversationId", conversationMessage.getConversationId());
		m.put("refStatusId", conversationMessage.getRefStatusId());
		m.put("refSenderId", conversationMessage.getRefSenderId());
		return m.toString();
	}

	@Override
	public MessageType getSupportType() {
		return MessageType.CONVERSATION_MESSAGE;
	}

	@Override
	public boolean support(MessageType messageType) {
		return this.getSupportType().equals(messageType);
	}

}
