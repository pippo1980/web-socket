package com.rensea.message.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

/**
 *表示某人加入或离开了 该会话
 */
public class ConversationDoorMessage extends MessageAdapter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7171707963385529177L;

	@Override
	public MessageType getMessageType() {
		return MessageType.CONVERSATION_DOOR;
	}

	@Override
	public String[] getReceiverIds() {
		return this.subscribers.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
	}

	private Long conversationId;

	private Long userId;

	/**
	 * 参与会话的人
	 */
	private List<String> subscribers = new ArrayList<String>();

	/**
	 * IN or OUT
	 */
	private String direction;

	public Long getConversationId() {
		return this.conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<String> getSubscribers() {
		return this.subscribers;
	}

	public void setSubscribers(List<String> subscribers) {
		this.subscribers = subscribers;
	}

}
