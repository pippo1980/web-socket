package com.rensea.message.dto;

public class TaskCompleteMessage extends MessageAdapter {

	private static final long serialVersionUID = 8489552485264278220L;
	private Long id;
	private Long award;
	private Integer level;

	@Override
	public MessageType getMessageType() {
		return MessageType.TASK_COMPLETE_MESSAGE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAward() {
		return award;
	}

	public void setAward(Long award) {
		this.award = award;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
