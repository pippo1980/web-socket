package com.rensea.message.dto;

@SuppressWarnings("serial")
public class ConversationReadedMessage extends MessageAdapter {


	private Long conversationId;
	
	private Long userId;

	public Long getConversationId() {
		return conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public MessageType getMessageType() {
		return MessageType.CONVERSATION_READED_MESSAGE;
	}

}
