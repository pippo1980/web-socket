/**
 *
 */
package com.rensea.message.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rensea.message.dto.Message;

/**
 * @author pippo
 *
 */
public class DefaultUnDispatchMessageHandler implements UnDispatchMessageHandler {

	private static Logger logger = LoggerFactory.getLogger(DefaultUnDispatchMessageHandler.class);

	@Override
	public void onError(Message message, Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unDispatch(Message message) {
		if (logger.isInfoEnabled()) {
			logger.info("can not find listener for message:" + message);
		}
	}

}
