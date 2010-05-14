package com.rensea.message.dto;

import java.io.Serializable;

public interface Message extends Serializable {

	enum FollowDirection {
		ON, //means follow(ed) occured
		OFF
		//means unfollow(ed) occured
	}

	enum ConversationDoorDirection {
		IN, //means sb. join the conversations
		OUT, //means sb. leavs the conversations
	}

	/**
	 * @return 将消息转换成Json字符串
	 */
	String toJson();

	/**
	 * @return 消息类型
	 */
	MessageType getMessageType();

	/**
	 * @return 发送者id,如不存在,如COUNT_UPDATE消息类型,则返回null
	 */
	String getFromId();

	/**
	 * @return 接收者id
	 */
	@Deprecated
	String[] getReceiverIds();

	/**
	 * @param receiverId
	 * @return 所给用户是否接受该消息
	 */
	@Deprecated
	boolean isFor(String receiverId);

}
