/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2010-4-1 - 下午02:10:11
 */
package com.rensea.message.server.spi.messagehandler;

import org.springframework.stereotype.Component;

import com.rensea.message.dto.MessageType;
import com.rensea.message.dto.TaskNotifyMessage;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageException;

/**
 * @since 2010-4-1
 * @author pippo
 */
@Component
public class TaskNotifyMessageHandler extends MessageHandlerTemplate<TaskNotifyMessage> {

	@Override
	public void handle(Connection connection, TaskNotifyMessage message) throws MessageException {
		if (connection.getUserId().equals(message.getUserId())) {
			this.flush(connection, message);
		}
	}

	@Override
	public boolean isSupport(MessageType messageType) {
		return MessageType.TASK_NOTIFY_MESSAGE.equals(messageType);
	}

}
