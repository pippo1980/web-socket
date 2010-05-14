/**
 * project : message-remote
 * user created : pippo
 * date created : 2009-12-24 - 上午10:50:53
 */
package com.rensea.message.dto;

/**
 * @since 2009-12-24
 * @author pippo
 */
public class HashTagMessage extends MessageAdapter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -77505892759815977L;

	@Override
	public MessageType getMessageType() {
		return MessageType.HASH_TAG;
	}

	@Override
	public String getFromId() {
		return this.senderId;
	}

	private String tag;

	private String statusId;

	private String senderId;

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

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

}
