/**
 * project : message-remote
 * user created : pippo
 * date created : 2010-4-1 - 下午01:38:35
 */
package com.rensea.message.dto;

/**
 * 任务提醒
 *
 * @since 2010-4-1
 * @author pippo
 */
public class TaskNotifyMessage extends MessageAdapter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6990974837233497247L;

	@Override
	public MessageType getMessageType() {
		return MessageType.TASK_NOTIFY_MESSAGE;
	}

	private String userId;

	private String taskId;

	private Integer level;

	/* 是否完成:{false:待完成,true:已完成} */
	private Boolean complete;

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getComplete() {
		return this.complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

}
