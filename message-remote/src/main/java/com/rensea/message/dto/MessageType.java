/**
 * project : message-remote
 * user created : pippo
 * date created : 2009-11-24 - 下午03:21:43
 */
package com.rensea.message.dto;

/**
 * @since 2009-11-24
 * @author pippo
 */
public enum MessageType {

	/* Status Message */
	STATUS(StatusMessage.class),

	/* 未读消息改变 */
	COUNT_UPDATE(CountUpdateMessage.class),

	/* 跟随通知 */
	FOLLOW(FollowMessage.class),

	/* 跟随通知 */
	FOLLOWED(FollowedMessage.class),

	/* 积分改变 */
	SCORE_CHANGE(ScoreChangeMessage.class),

	/* 提到 */
	MENTION(MentionMessage.class),

	AUDITED_CONVERSATION_MESSAGE(AuditedConversationMessage.class),

	/* 我参与的会话中收到消息通知 */
	CONVERSATION_MESSAGE(ConversationMessage.class),

	/* 有人加入我参与的会话 */
	CONVERSATION_DOOR(ConversationDoorMessage.class),

	/* 悄悄话 */
	//DM(Direct),

	UPDATE_CONVERSATION_COUNT(UpdateConverationCountMessage.class),

	FOR_USER_TAG(UserTagMessage.class),

	STICK_STATUS(StickStatusMessage.class),

	HASH_TAG(HashTagMessage.class),

	SYSTEM(SystemMessage.class),

	/* 圈子精华贴 */
	TAG_FOLLOWING_MESSAGE(TagFollowingMessage.class),

	CONVERSATION_READED_MESSAGE(ConversationReadedMessage.class),

	/* 任务提醒 */
	TASK_NOTIFY_MESSAGE(TaskNotifyMessage.class),

	/* 任务完成提醒 */
	TASK_COMPLETE_MESSAGE(TaskCompleteMessage.class);
	
	

	private MessageType(Class<? extends Message> targetClass) {
		this.targetClass = targetClass;
	}

	private Class<? extends Message> targetClass;

	public Class<? extends Message> getTargetClass() {
		return this.targetClass;
	}

	@Override
	public String toString() {
		return this.name();
	}

	public static Class<? extends Message> getTargetClass(String messageType) {
		MessageType type = MessageType.valueOf(messageType);
		return type != null ? type.getTargetClass() : null;
	}

}
