package com.rensea.message.dto;

import java.util.List;

public class UpdateConverationCountMessage extends MessageAdapter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5947829986004181147L;

	private Long receiverId;

	private List<String> conversations;

	public Long getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public List<String> getConversations() {
		return this.conversations;
	}

	public void setConversations(List<String> conversations) {
		this.conversations = conversations;
	}

	@Override
	public MessageType getMessageType() {
		return MessageType.UPDATE_CONVERSATION_COUNT;
	}

	@Override
	public String[] getReceiverIds() {
		return new String[] { String.valueOf(this.receiverId) };
	}

}
