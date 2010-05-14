package com.rensea.message.dto;

import org.apache.commons.lang.ArrayUtils;

public class MentionMessage extends MessageAdapter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1331096373913975476L;

	@Override
	public String getFromId() {
		return this.senderId != null ? this.senderId.toString() : null;
	}

	@Override
	public String[] getReceiverIds() {
		return this.mentionedId != null ? new String[] { this.mentionedId.toString() } : ArrayUtils.EMPTY_STRING_ARRAY;
	}

	private Long senderId;

	private Long mentionedId;

	private Long statusId;

	private int newMentionedCount;

	@Override
	public MessageType getMessageType() {
		return MessageType.MENTION;
	}

	public Long getSenderId() {
		return this.senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getMentionedId() {
		return this.mentionedId;
	}

	public void setMentionedId(Long mentionedId) {
		this.mentionedId = mentionedId;
	}

	public Long getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public int getNewMentionedCount() {
		return this.newMentionedCount;
	}

	public void setNewMentionedCount(int newMentionedCount) {
		this.newMentionedCount = newMentionedCount;
	}

}
