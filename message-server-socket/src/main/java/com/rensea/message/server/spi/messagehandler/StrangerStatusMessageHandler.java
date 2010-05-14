/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-30 - 上午11:50:53
 */
package com.rensea.message.server.spi.messagehandler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rensea.message.dto.MessageType;
import com.rensea.message.dto.StatusMessage;
import com.rensea.message.server.service.UserService;
import com.rensea.message.server.tcp.connection.Connection;

/**
 * @since 2009-11-30
 * @author pippo
 */
@Component
public class StrangerStatusMessageHandler extends MessageHandlerTemplate<StatusMessage> {

	@Resource
	private UserService userService;

	@Override
	public void handle(Connection connection, StatusMessage message) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("reveived all status message");
		}

		/* 如果是本人发送的不进入随便看看列表 */
		if (connection.getUserId().equals(message.getFromId())) {
			return;
		}

		/* 只显示非保护的 */
		if (Boolean.TRUE.equals(message.isUserProtected())) {
			return;
		}

		/* connection 用户的朋友 */
		List<String> followingIds = this.userService.getFollowing(connection.getUserId());

		/* 如果是我的朋友发的那么不进入我的随便看看列表 */
		if (followingIds.contains(message.getFromId())) {
			return;
		}

		this.flush(connection, message);
	}

	@Override
	public boolean isSupport(MessageType messageType) {
		return MessageType.STATUS.equals(messageType);
	}

}
