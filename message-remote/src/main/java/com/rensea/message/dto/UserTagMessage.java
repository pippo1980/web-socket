/**
 * project : message-remote
 * user created : pippo
 * date created : 2009-11-24 - 下午03:19:31
 */
package com.rensea.message.dto;

/**
 * @since 2009-11-24
 * @author pippo
 */
public class UserTagMessage extends MessageAdapter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8944465173374177539L;

	@Override
	public MessageType getMessageType() {
		return MessageType.FOR_USER_TAG;
	}

	@Override
	public String getFromId() {
		return this.senderId;
	}

	private String statusId;

	private String senderId;

	private String userTags;

	public String getStatusId() {
		return this.statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getSenderId() {
		return this.senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getUserTags() {
		return this.userTags;
	}

	public void setUserTags(String userTags) {
		this.userTags = userTags;
	}

}
