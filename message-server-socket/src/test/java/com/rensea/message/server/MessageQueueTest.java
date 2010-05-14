/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2010-3-31 - 下午03:56:45
 */
package com.rensea.message.server;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.rensea.message.dto.Message;
import com.rensea.message.dto.MessageFactory;
import com.rensea.message.dto.TaskCompleteMessage;
import com.rensea.message.dto.TaskNotifyMessage;
import com.rensea.message.spi.queue.QueueConfiguration;
import com.rensea.message.spi.queue.XMemcachedQueue;

/**
 * @since 2010-3-31
 * @author pippo
 */
public class MessageQueueTest {

	public static void main(String[] args) {
		QueueConfiguration configuration = new QueueConfiguration();
		configuration.setServerId("new_comet_server_preview");
		configuration.setQueueServers("192.168.1.202:22133");
		final XMemcachedQueue memcachedQueue = new XMemcachedQueue();
		memcachedQueue.setConfiguration(configuration);
		memcachedQueue.initialize();

		memcachedQueue.setConnectionPoolSize(1);

		final TaskNotifyMessage message = new TaskNotifyMessage();
		message.setUserId("5");
		message.setTaskId("44581");
		message.setLevel(2);
		message.setComplete(false);

		TaskCompleteMessage completeMessage = new TaskCompleteMessage();
		completeMessage.setAward(1L);
		completeMessage.setLevel(2);
		System.out.println(completeMessage.toJson());

		Message mm = MessageFactory.fromJson(completeMessage.toJson());

		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				memcachedQueue.offer("new_comet_server_preview", message.toJson());
			}
		}, 10, 30, TimeUnit.SECONDS);

	}

}
