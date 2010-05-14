package com.rensea.message.dto;

/**
 * 置顶消息
 *
 * @since 2010-3-31
 * @author pippo
 */
public class StickStatusMessage extends MessageAdapter {

	private static final long serialVersionUID = 8513363160548901664L;

	@Override
	public MessageType getMessageType() {
		return MessageType.STICK_STATUS;
	}

	@Override
	public String getFromId() {
		return this.userId;
	}

	private String id;

	private String userId;

	private String sticker_name;

	private String status_id;

	private Boolean availability;

	public String getStatus_id() {
		return this.status_id;
	}

	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}

	public Boolean getAvailability() {
		return this.availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public String getSticker_name() {
		return sticker_name;
	}

	public void setSticker_name(String sticker_name) {
		this.sticker_name = sticker_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
