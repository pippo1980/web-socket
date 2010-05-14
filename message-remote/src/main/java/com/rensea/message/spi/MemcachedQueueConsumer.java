package com.rensea.message.spi;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rensea.message.dto.Message;
import com.rensea.message.dto.MessageFactory;
import com.rensea.message.spi.MessageListener.AgentUser;
import com.rensea.message.spi.queue.QueueOperation;

public class MemcachedQueueConsumer implements MessageQueueConsumer {

	private static Logger logger = LoggerFactory.getLogger(MemcachedQueueConsumer.class);

	private Set<ScheduledFuture<?>> scheduledFutures = new HashSet<ScheduledFuture<?>>();

	private Map<AgentUser, MessageListener<? extends Message>> listeners = new ConcurrentHashMap<AgentUser, MessageListener<? extends Message>>();

	public void start() {

		/* 消息消费线程 */
		for (int i = 0; i < this.taskThreadSize; i++) {
			ScheduledFuture<?> future = this.executorService.scheduleAtFixedRate(new Runnable() {

				@Override
				public void run() {
					MemcachedQueueConsumer.this.consumer();
				}

			}, 30 + i * this.interval, this.taskThreadSize * this.interval, TimeUnit.MILLISECONDS);
			this.scheduledFutures.add(future);
		}

		/* 守护线程 */
		ScheduledFuture<?> future = this.executorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				MemcachedQueueConsumer.this.validate();
			}

		}, 60, 60, TimeUnit.SECONDS);
		this.scheduledFutures.add(future);

		if (this.messageDispatcher == null) {
			this.messageDispatcher = new DefatulMessageDispatcher(this.executorService);
		}

		if (this.unDispatchMessageHandler == null) {
			this.unDispatchMessageHandler = new DefaultUnDispatchMessageHandler();
		}
	}

	private void consumer() {
		try {
			if (logger.isTraceEnabled()) {
				logger.trace("begin poll message from queue");
			}

			String jsonString = this.queueOperation.poll(this.queueName);

			if (logger.isTraceEnabled()) {
				logger.trace("end poll message from queue, the message is:" + jsonString);
			}

			if (StringUtils.isBlank(jsonString)) {
				return;
			}

			if (logger.isInfoEnabled()) {
				logger.info("consumer queue message:" + jsonString);
			}

			Message message = MessageFactory.fromJson(jsonString);

			MemcachedQueueConsumer.this.distptchMessage(message);
		} catch (Exception e) {
			logger.error("distptch message due to error", e);
		}
	}

	@SuppressWarnings("unchecked")
	private void validate() {
		for (MessageListener listener : this.listeners.values()) {
			try {
				if (listener.isValid()) {
					continue;
				}
				this.removeListener(listener.getAgentUser());
				logger.warn("the listener:" + listener + " is invalid, remove it");
			} catch (Exception e) {
				logger.error("validate listener:" + listener + " due to error", e);
			}
		}
	}

	private void distptchMessage(Message message) throws MessageException {
		boolean dispatched = this.messageDispatcher.dispatch(this.listeners.values(), message);

		if (!dispatched) {
			this.unDispatchMessageHandler.unDispatch(message);
		}
	}

	@Override
	public void addListener(MessageListener<? extends Message> listener) {
		this.listeners.put(listener.getAgentUser(), listener);
		if (logger.isDebugEnabled()) {
			logger.debug("regist new listener:" + listener);
		}
	}

	@Override
	public void removeListener(AgentUser agentUser) {
		if (!this.listeners.containsKey(agentUser)) {
			return;
		}
		@SuppressWarnings("unchecked")
		MessageListener listener = this.listeners.get(agentUser);
		this.listeners.remove(agentUser);
		listener.destory();
		if (logger.isDebugEnabled()) {
			logger.debug("remove  listener of user:" + agentUser);
		}
	}

	@Override
	public MessageListener<? extends Message> getMessageListener(AgentUser agentUser) {
		return this.listeners.get(agentUser);
	}

	@Override
	public void destory() {
		this.listeners.clear();
		for (ScheduledFuture<?> future : this.scheduledFutures) {
			if (!future.isCancelled()) {
				future.cancel(false);
			}
		}
	}

	@Override
	public void init() {
		this.start();
	}

	private QueueOperation queueOperation;

	public void setQueueOperation(QueueOperation queueOperation) {
		this.queueOperation = queueOperation;
	}

	private ScheduledExecutorService executorService;

	public void setExecutorService(ScheduledExecutorService executorService) {
		this.executorService = executorService;
	}

	/* default 20 thread */
	private int taskThreadSize = 20;

	public void setTaskThreadSize(int taskThreadSize) {
		this.taskThreadSize = taskThreadSize;
	}

	/* default 100 ms */
	private int interval = 100;

	public void setInterval(int interval) {
		this.interval = interval;
	}

	private String queueName;

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	private MessageDispatcher messageDispatcher;

	public void setMessageDispatcher(MessageDispatcher messageDispatcher) {
		this.messageDispatcher = messageDispatcher;
	}

	private UnDispatchMessageHandler unDispatchMessageHandler;

	public void setUnDispatchMessageHandler(UnDispatchMessageHandler unDispatchMessageHandler) {
		this.unDispatchMessageHandler = unDispatchMessageHandler;
	}

}
