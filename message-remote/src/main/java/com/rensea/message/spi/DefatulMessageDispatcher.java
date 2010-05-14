/**
 *
 */
package com.rensea.message.spi;

import java.util.Collection;
import java.util.concurrent.ExecutorService;

import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rensea.message.dto.Message;

/**
 * @author pippo
 */
public class DefatulMessageDispatcher implements MessageDispatcher {

	private static Logger logger = LoggerFactory.getLogger(DefatulMessageDispatcher.class);

	public DefatulMessageDispatcher(ExecutorService executorService) {
		this.executorService = executorService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean dispatch(Collection<MessageListener<? extends Message>> listeners, Message message) {
		int i = 0;
		for (MessageListener listener : listeners) {
			if (logger.isDebugEnabled()) {
				logger.debug("send message:" + message + " to listener:" + listener);
			}
			this.dispatch(listener, message);
			i++;
		}
		return i > 0;
	}

	@SuppressWarnings("unchecked")
	private void dispatch(final MessageListener listener, final Message message) {
		this.executorService.execute(new Runnable() {

			@Override
			public void run() {
				if (!listener.isValid()) {
					logger.warn("the listener is valid, ingron message");
					return;
				}

				try {
					Message payload = (Message) SerializationUtils.clone(message);
					listener.onMessage(payload);
				} catch (Exception e) {
					logger.error("distptchMessage message:" + message + " due to error", e);
				}
			}
		});
	}

	private ExecutorService executorService;

}
