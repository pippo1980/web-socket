package com.rensea.message.dto;

public class FollowMessage extends FollowActionMessage {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 600175992422258448L;

	private Long followingId;
	private String followingName;

	@Override
	public MessageType getMessageType() {
		return MessageType.FOLLOW;
	}

	public Long getFollowingId() {
		return this.followingId;
	}

	public void setFollowingId(Long followingId) {
		this.followingId = followingId;
	}

	public String getFollowingName() {
		return this.followingName;
	}

	public void setFollowingName(String followingName) {
		this.followingName = followingName;
	}

}
