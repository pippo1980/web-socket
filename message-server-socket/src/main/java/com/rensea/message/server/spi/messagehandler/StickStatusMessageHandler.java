/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-4 - 上午11:17:43
 */
package com.rensea.message.server.spi.messagehandler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rensea.message.dto.MessageType;
import com.rensea.message.dto.StickStatusMessage;
import com.rensea.message.server.service.UserService;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageException;
import com.sirius.core.utils.ArrayUtils;
import com.sirius.core.utils.StringUtils;

/**
 * @since 2009-12-4
 * @author pippo
 */
@Component
public class StickStatusMessageHandler extends MessageHandlerTemplate<StickStatusMessage> {

	@Resource
	private UserService userService;

	@Override
	public void handle(Connection connection, StickStatusMessage message) throws MessageException {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("resolve message:" + message + ", the listener is:" + this);
		}

		if (StringUtils.isBlank(message.getUserId())) {
			return;
		}

		/* 发送给自己 */
		if (message.getUserId().equals(connection.getUserId())) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("the user id is:" + connection.getUserId() + ", the message is:" + message.toJson());
			}
			this.flush(connection, message);
			return;
		}

		/* 发送给跟随者 */
		List<String> followers = userService.getFollower(message.getUserId());
		if (ArrayUtils.contains(followers.toArray(), connection.getUserId())) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("the user id is:" + connection.getUserId() + ", the message is:" + message.toJson());
			}
			this.flush(connection, message);
		}
	}

	@Override
	public boolean isSupport(MessageType messageType) {
		return MessageType.STICK_STATUS.equals(messageType);
	}

}
