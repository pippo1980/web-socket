/**
 *
 */
package com.rensea.message.server.mock;

import com.rensea.message.dto.Message;
import com.rensea.message.spi.MessageQueueAgent;

/**
 * @author pippo
 */
public class MockMessageQueueAgent implements MessageQueueAgent {

	@Override
	public void init() {

	}

	@Override
	public void destory() {

	}

	@Override
	public void sendMessage(Message message) {
		System.err.println("send message:" + message);
	}

}
