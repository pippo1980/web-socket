/**
 *
 */
package com.rensea.message.server.mock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.rensea.message.dto.Message;
import com.rensea.message.dto.StatusMessage;
import com.rensea.message.spi.MessageException;
import com.rensea.message.spi.MessageListener;
import com.rensea.message.spi.MessageQueueConsumer;
import com.rensea.message.spi.MessageListener.AgentUser;

/**
 * @author pippo
 */
public class MockMessageQueueConsumer implements MessageQueueConsumer {

	@Override
	public void init() {
		Executors.newScheduledThreadPool(10).scheduleAtFixedRate(new Runnable() {

			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				String messageString = "{statusId:1,followers:['123456','292','4'],messageType:'STATUS',countType:'mention',text:'你好'}";
				Message message = new StatusMessage().fromJson(messageString);
				for (MessageListener listener : MockMessageQueueConsumer.this.listeners) {
					try {
						listener.onMessage(message);
					} catch (MessageException e) {
						e.printStackTrace();
					}
				}

			}
		}, 30, 10, TimeUnit.SECONDS);
	}

	@Override
	public void destory() {

	}

	@Override
	public void addListener(MessageListener<? extends Message> listener) {
		synchronized (this.listeners) {
			this.listeners.add(listener);
		}
	}

	//	@Override
	//	public void removeListener(MessageListener<? extends Message> listener) {
	//		synchronized (this.listeners) {
	//			this.listeners.remove(listener);
	//		}
	//	}

	@Override
	public void removeListener(final AgentUser agentUser) {
		synchronized (this.listeners) {
			for (Iterator<MessageListener<? extends Message>> it = this.listeners.iterator(); it.hasNext();) {
				MessageListener<? extends Message> listener = it.next();
				if (listener.getAgentUser().equals(agentUser)) {
					it.remove();
					listener.destory();
				}
			}
		}
	}

	//	@Override
	//	public void removeListener(AgentUser agentUser, MessageType messageType) {
	//		//To change body of implemented methods use File | Settings | File Templates.
	//	}

	private List<MessageListener<? extends Message>> listeners = new ArrayList<MessageListener<? extends Message>>();

	@Override
	public MessageListener<? extends Message> getMessageListener(AgentUser agentUser) {
		for (MessageListener<? extends Message> listener : this.listeners) {
			if (agentUser.equals(listener.getAgentUser())) {
				return listener;
			}
		}
		return null;
	}

}
