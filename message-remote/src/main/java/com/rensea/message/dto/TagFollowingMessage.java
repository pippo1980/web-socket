/**
 * project : message-remote
 * user created : pippo
 * date created : 2009-11-24 - 下午03:19:31
 */
package com.rensea.message.dto;

import java.util.List;

public class TagFollowingMessage extends MessageAdapter {

	private static final long serialVersionUID = -4074650953449962016L;

	@Override
	public MessageType getMessageType() {
		return MessageType.TAG_FOLLOWING_MESSAGE;
	}


	private Long originalStatusId;
	
	private Long newStatusId;
	
	private Long senderId;

	private List<String> tags;

	public Long getOriginalStatusId() {
		return originalStatusId;
	}

	public void setOriginalStatusId(Long originalStatusId) {
		this.originalStatusId = originalStatusId;
	}

	public Long getNewStatusId() {
		return newStatusId;
	}

	public void setNewStatusId(Long newStatusId) {
		this.newStatusId = newStatusId;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

}
