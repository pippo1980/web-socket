package com.rensea.message.dto;

import org.apache.commons.lang.ArrayUtils;

/**
 * 用于表示（取消）跟随或被（取消）跟随等动作的消息
 */
public abstract class FollowActionMessage extends MessageAdapter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 777549836977819951L;

	@Override
	public String[] getReceiverIds() {
		return this.userId != null ? new String[] { this.userId.toString() } : ArrayUtils.EMPTY_STRING_ARRAY;
	}

	protected Long userId;
	private String userName;
	private int followingsCount;
	private int followersCount;
	private String direction;

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getFollowingsCount() {
		return this.followingsCount;
	}

	public void setFollowingsCount(int followingsCount) {
		this.followingsCount = followingsCount;
	}

	public int getFollowersCount() {
		return this.followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

}
