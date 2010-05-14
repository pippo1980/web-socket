package com.rensea.message.spi.queue;

import java.io.Serializable;


public interface QueueOperation {

	<T extends Serializable> void offer(String queueName, T element);

	<T extends Serializable> T poll(String queueName,long timeout);

	<T extends Serializable> T poll(String queueName);

}
