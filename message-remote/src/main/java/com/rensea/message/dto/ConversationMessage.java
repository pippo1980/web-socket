package com.rensea.message.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

/**
 * 每当有一条某个会话关联的status产生时，发送此类型消息， usersInRoom表示这个会话中的所有的人，并将这条消息发送 给每一个人，如果user在线，则由用户向服务器发起请求，获得相应的操作： 如：
 * 1、用户已经打开这个会话，则更只更新消息 2、用户是第一次加入这个会话，只发送通知 3、用户没有打开当前会话，只更新未读数 4、用户已经取消了这个会话，不做任何操作 OR:: 此消息只用作更新消息用（1），其余的另外做消息类型
 */
public class ConversationMessage extends MessageAdapter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1151510697400250950L;

	@Override
	public MessageType getMessageType() {
		return MessageType.CONVERSATION_MESSAGE;
	}

	@Override
	public String[] getReceiverIds() {
		return this.usersInRoom.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
	}

	/**
	 * 会话Id
	 */
	private Long conversationId;

	/**
	 * 参与会话的人
	 */
	private List<String> usersInRoom = new ArrayList<String>();

	/**
	 * 对应的status Id
	 */
	private Long refStatusId;

	/**
	 * status发送者的Id
	 */
	private Long refSenderId;

	public List<String> getUsersInRoom() {
		return this.usersInRoom;
	}

	public void setUsersInRoom(List<String> usersInRoom) {
		this.usersInRoom = usersInRoom;
	}

	public Long getRefStatusId() {
		return this.refStatusId;
	}

	public void setRefStatusId(Long refStatusId) {
		this.refStatusId = refStatusId;
	}

	public Long getRefSenderId() {
		return this.refSenderId;
	}

	public void setRefSenderId(Long refSenderId) {
		this.refSenderId = refSenderId;
	}

	public Long getConversationId() {
		return this.conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}

}
