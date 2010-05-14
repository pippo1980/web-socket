/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-16 - 下午04:06:15
 */
package com.rensea.message.server.spi.dto;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rensea.message.dto.StatusMessage;
import com.rensea.message.server.service.UserService;
import com.sirius.core.utils.ArrayUtils;

/**
 * @since 2009-11-16
 * @author pippo
 */
@Component("statusMessageReceiverInjector")
public class StatusMessageReceiverInjector implements MessageReceiverInjector<StatusMessage> {

	@Override
	public void inject(StatusMessage message) {
		message
				.setReceiverIds(this.userService.getFollower(message.getFromId())
						.toArray(ArrayUtils.EMPTY_STRING_ARRAY));
	}

	@Resource
	private UserService userService;

}
