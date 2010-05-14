/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-16 - 下午03:52:57
 */
package com.rensea.message.server.spi.dto;

import com.rensea.message.dto.Message;

/**
 * @since 2009-11-16
 * @author pippo
 */
public interface MessageReceiverInjector<M extends Message> {

	void inject(M message);

}
