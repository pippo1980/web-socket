/**
 *
 */
package com.rensea.message.spi;

import java.util.Collection;

import com.rensea.message.dto.Message;

/**
 * @author pippo
 */
public interface MessageDispatcher {

	/**
	 * @param listeners
	 * @param message
	 * @return 是否有listener接收该message
	 */
	boolean dispatch(Collection<MessageListener<? extends Message>> listeners, Message message);

}
