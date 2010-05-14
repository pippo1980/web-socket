package com.rensea.message.dto;

import org.apache.commons.lang.ArrayUtils;

public class ScoreChangeMessage extends MessageAdapter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -682827837295907399L;

	private Long userId;
	private long score;

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public long getScore() {
		return this.score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	@Override
	public MessageType getMessageType() {
		return MessageType.SCORE_CHANGE;
	}

	@Override
	public String[] getReceiverIds() {
		return this.userId != null ? new String[] { this.userId.toString() } : ArrayUtils.EMPTY_STRING_ARRAY;
	}

}
