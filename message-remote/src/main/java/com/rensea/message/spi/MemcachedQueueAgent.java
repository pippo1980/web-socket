package com.rensea.message.spi;

import com.rensea.message.dto.Message;
import com.rensea.message.spi.queue.QueueOperation;

public class MemcachedQueueAgent implements MessageQueueAgent {

	@Override
	public void sendMessage(final Message message) {
		this.queueOperation.offer(queueName, message.toJson());
	}

	@Override
	public void destory() {

	}

	@Override
	public void init() {

	}

	private QueueOperation queueOperation;

	public void setQueueOperation(QueueOperation queueOperation) {
		this.queueOperation = queueOperation;
	}

	private String queueName;

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

}
