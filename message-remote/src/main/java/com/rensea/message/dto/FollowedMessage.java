package com.rensea.message.dto;

public class FollowedMessage extends FollowActionMessage {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8174599385230241988L;

	private Long followerId;
	private String followerName;

	@Override
	public MessageType getMessageType() {
		return MessageType.FOLLOWED;
	}

	public Long getFollowerId() {
		return this.followerId;
	}

	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
	}

	public String getFollowerName() {
		return this.followerName;
	}

	public void setFollowerName(String followerName) {
		this.followerName = followerName;
	}

}
