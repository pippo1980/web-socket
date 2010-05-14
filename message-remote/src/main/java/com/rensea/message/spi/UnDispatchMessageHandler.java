/**
 *
 */
package com.rensea.message.spi;

import com.rensea.message.dto.Message;

/**
 * @author pippo
 *
 */
public interface UnDispatchMessageHandler {

	void unDispatch(Message message);

	void onError(Message message, Throwable t);

}
