package com.rensea.message.dto;

import org.apache.commons.lang.ArrayUtils;

public class CountUpdateMessage extends MessageAdapter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5050613749255829037L;

	@Override
	public String[] getReceiverIds() {
		return this.userId != null ? new String[] { this.userId.toString() } : ArrayUtils.EMPTY_STRING_ARRAY;
	}

	private Long userId;
	private int count;
	private String countType;

	public enum CountType {
		MENTION, DM, SYSTEM
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCountType() {
		return this.countType;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}

	@Override
	public MessageType getMessageType() {
		return MessageType.COUNT_UPDATE;
	}

}
