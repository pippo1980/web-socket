package com.rensea.message.dto;

import java.util.Arrays;
import java.util.List;

public class StatusMessage extends MessageAdapter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2627814426075438512L;

	@Override
	public MessageType getMessageType() {
		return MessageType.STATUS;
	}

	//	@Override
	//	public String[] getReceiverIds() {
	//		return this.followers.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
	//	}

	@Override
	public String getFromId() {
		return this.sender != null ? this.sender.toString() : "";
	}

	private Long statusId;
	private long createaAt;
	private List<String> followers;
	private boolean userProtected;
	private Long sender;
	private boolean protect;
	private String statusType;
	private String appName;
	private String text;
	private String originalUrl;
	private String linkTitle;
	private String source;
	private String replyToId;

	public long getCreateaAt() {
		return this.createaAt;
	}

	public void setCreateaAt(long createaAt) {
		this.createaAt = createaAt;
	}

	public boolean isUserProtected() {
		return this.userProtected;
	}

	public void setUserProtected(boolean userProtected) {
		this.userProtected = userProtected;
	}

	public Long getSender() {
		return this.sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}

	public boolean isProtect() {
		return this.protect;

	}

	public void setProtect(boolean protect) {
		this.protect = protect;
	}

	public String getAppName() {
		return this.appName;

	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Long getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public List<String> getFollowers() {
		return this.followers;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}

	public String getStatusType() {
		return this.statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOriginalUrl() {
		return this.originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getLinkTitle() {
		return this.linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getReplyToId() {
		return this.replyToId;
	}

	public void setReplyToId(String replyToId) {
		this.replyToId = replyToId;
	}

	public enum StatusType {

		TEXT, PICTURE, LINK, NONE;

		public static boolean isNone(StatusType statusType) {
			return statusType == null || statusType.equals(StatusType.NONE);
		}

		public static List<StatusType> allStatusTypes() {
			return Arrays.asList(TEXT, PICTURE, LINK, NONE);
		}

	}

}
