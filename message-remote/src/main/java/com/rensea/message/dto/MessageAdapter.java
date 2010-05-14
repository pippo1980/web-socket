/**
 *
 */
package com.rensea.message.dto;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author pippo
 */
public abstract class MessageAdapter implements Message {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9095947078264568740L;

	private String[] receiverIds = ArrayUtils.EMPTY_STRING_ARRAY;

	@Deprecated
	public String[] getReceiverIds() {
		return this.receiverIds;
	}

	@Deprecated
	public void setReceiverIds(String[] receiverIds) {
		this.receiverIds = receiverIds;
	}

	public String toJson() {
		return MessageFactory.toJson(this);
	}

	@SuppressWarnings("unchecked")
	public <T extends Message> T fromJson(String jsonStr) {
		return (T) MessageFactory.fromJson(jsonStr);
	}

	@Override
	public String toString() {
		return this.toJson();
	}

	@Override
	public String getFromId() {
		return null;
	}

	@Deprecated
	@Override
	public boolean isFor(String receiverId) {
		return ArrayUtils.contains(this.getReceiverIds(), receiverId);
	}
}
