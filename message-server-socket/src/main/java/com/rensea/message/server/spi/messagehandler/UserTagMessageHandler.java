/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-30 - 下午12:40:00
 */
package com.rensea.message.server.spi.messagehandler;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rensea.message.dto.MessageType;
import com.rensea.message.dto.StatusMessage;
import com.rensea.message.dto.UserTagMessage;
import com.rensea.message.server.service.UserService;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageException;
import com.sirius.core.utils.StringUtils;

/**
 * @since 2009-11-30
 * @author pippo
 */
@Component
public class UserTagMessageHandler extends MessageHandlerTemplate<StatusMessage> {

	@Resource
	private UserService userService;

	@Override
	public void handle(Connection connection, StatusMessage message) throws MessageException {
		String senderId = message.getFromId();
		String senderTags = this.userService.getUserTags(senderId);
		if (StringUtils.isBlank(senderTags)) {
			return;
		}

		String agentTags = this.userService.getUserTags(connection.getUserId());
		if (StringUtils.isBlank(agentTags)) {
			return;
		}

		if (this.checkTags(senderTags, agentTags)) {
			this.flushMessage(connection, message, senderTags);
		}
	}

	private boolean checkTags(String senderTags, String agentTags) {
		String _agentTags = "," + agentTags + ",";
		String[] tags = senderTags.split(",");
		boolean receive = false;
		for (String tag : tags) {
			if (StringUtils.contains(_agentTags, "," + tag + ",")) {
				receive = true;
				break;
			}
		}
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("the sender tags is:" + senderTags + ", the agent tags is:" + agentTags
					+ ", the check result is:" + receive);
		}
		return receive;
	}

	private void flushMessage(Connection connection, StatusMessage message, String senderTags) {
		UserTagMessage userTagMessage = new UserTagMessage();
		userTagMessage.setStatusId(message.getStatusId() + "");
		userTagMessage.setUserTags(senderTags);
		this.flush(connection, userTagMessage);
	}

	@Override
	public boolean isSupport(MessageType messageType) {
		return MessageType.FOR_USER_TAG.equals(messageType);
	}
}
