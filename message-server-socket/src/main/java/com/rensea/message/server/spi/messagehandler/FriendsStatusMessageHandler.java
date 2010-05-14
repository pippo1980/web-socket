/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-30 - 上午11:43:23
 */
package com.rensea.message.server.spi.messagehandler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rensea.message.dto.MessageType;
import com.rensea.message.dto.StatusMessage;
import com.rensea.message.server.service.UserService;
import com.rensea.message.server.tcp.connection.Connection;
import com.sirius.core.utils.ArrayUtils;
import com.sirius.core.utils.StringUtils;

/**
 * @since 2009-11-30
 * @author pippo
 */
@Component
public class FriendsStatusMessageHandler extends MessageHandlerTemplate<StatusMessage> {

	@Resource
	private UserService userService;

	@Override
	public void handle(Connection connection, StatusMessage message) {
		String sender = message.getFromId();
		if (StringUtils.isBlank(sender)) {
			return;
		}

		if (connection.getUserId().equals(message.getFromId())) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("the user id is:" + connection.getUserId() + ", the message is:" + message.toJson());
			}
			this.flush(connection, message);
			return;
		}

		List<String> followers = this.userService.getFollower(sender.toString());
		if (ArrayUtils.contains(followers.toArray(), connection.getUserId())) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("the user id is:" + connection.getUserId() + ", the message is:" + message.toJson());
			}
			this.flush(connection, message);
			return;
		}

		if (this.logger.isDebugEnabled()) {
			this.logger.debug("user id is:" + connection.getUserId() + ", not in reveiver:"
					+ ArrayUtils.toString(message.getFollowers()));
		}
	}

	@Override
	public boolean isSupport(MessageType messageType) {
		return MessageType.STATUS.equals(messageType);
	}

}
