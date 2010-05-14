/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-30 - 下午12:40:00
 */
package com.rensea.message.server.spi.messagehandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rensea.message.dto.MessageType;
import com.rensea.message.dto.TagFollowingMessage;
import com.rensea.message.server.service.TagService;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageException;

/**
 * @since 2009-11-30
 * @author pippo
 */
@Component
public class TagFollowingMessageHandler extends MessageHandlerTemplate<TagFollowingMessage> {

	@Resource
	private TagService tagService;

	@Override
	public void handle(Connection connection, TagFollowingMessage message) throws MessageException {
		List<String> tags = message.getTags();
		Set<Long> userIds = new HashSet<Long>();
		for (String tag : tags) {
			userIds.addAll(this.tagService.getTagFollowing(tag));
		}
		if (userIds.contains(Long.valueOf(connection.getUserId()))) {
			this.flush(connection, message);
		}
	}

	@Override
	public boolean isSupport(MessageType messageType) {
		return MessageType.TAG_FOLLOWING_MESSAGE.equals(messageType);
	}
}
