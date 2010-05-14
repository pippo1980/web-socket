/**
 *
 */
package com.rensea.message.server.tcp.connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import net.sf.json.JSONObject;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xsocket.connection.INonBlockingConnection;

import com.rensea.message.dto.Message;
import com.rensea.message.server.spi.dto.converter.MessageConverterUtils;
import com.sirius.core.utils.DateFormatUtils;

/**
 * @author pippo
 */
public class ConnectionProxy implements Connection {

	private static Logger logger = LoggerFactory.getLogger(ConnectionProxy.class);

	public ConnectionProxy(INonBlockingConnection payload) {
		this.connectTime = System.currentTimeMillis();
		this.payload = payload;
	}

	public ConnectionProxy(String userId, INonBlockingConnection connection, ScheduledExecutorService executorService) {
		this.connectTime = System.currentTimeMillis();
		this.userId = userId;
		this.payload = connection;
		this.executorService = executorService;
	}

	private ScheduledExecutorService executorService;

	protected String userId;

	protected INonBlockingConnection payload;

	private long connectTime;

	private Queue<Message> messages = new ConcurrentLinkedQueue<Message>();

	private AtomicBoolean has_flush_task = new AtomicBoolean(false);

	private void mergeMessages() throws IOException {
		if (logger.isTraceEnabled()) {
			logger.trace("begin check and flush message to client:" + this.getUserId());
		}

		final List<String> msgs = new ArrayList<String>();

		synchronized (this.messages) {
			Message message = this.messages.poll();
			int i = 0;
			while (message != null && i < 10) {
				msgs.add(MessageConverterUtils.convert(message));
				message = this.messages.poll();
				i++;
			}
		}

		if (msgs.isEmpty()) {
			if (logger.isTraceEnabled()) {
				logger.trace("no find message for client:" + this.getUserId());
			}
			return;
		}

		String messages = JSONObject.fromObject(new HashMap<String, Object>() {

			private static final long serialVersionUID = 4922259744879678249L;

			{
				this.put("messages", msgs);
				this.put("flushTime", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
			}
		}).toString();

		this.write(messages);

		if (logger.isInfoEnabled()) {
			logger.info("flush messages:" + messages + ", the agent is:" + this.toString());
		}
	}

	@Override
	public String getId() {
		return this.payload != null ? this.payload.getId() : null;
	}

	public String getUserId() {
		return this.userId;
	}

	public INonBlockingConnection getPayload() {
		return this.payload;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setExecutorService(ScheduledExecutorService executorService) {
		this.executorService = executorService;
	}

	public void write(String message) throws IOException {
		ConnectionHelper.write(this.payload, message);
	}

	@Override
	public void write(Message message) throws IOException {
		synchronized (this.messages) {
			this.messages.add(message);
		}

		synchronized (this.has_flush_task) {
			if (this.has_flush_task.get()) {
				return;
			}

			this.has_flush_task.set(true);

			this.executorService.schedule(new Runnable() {

				@Override
				public void run() {
					try {
						ConnectionProxy.this.mergeMessages();
					} catch (IOException e) {
						logger.error("flush message due to error", e);
					} finally {
						ConnectionProxy.this.has_flush_task.set(false);
					}
				}

			}, 200, TimeUnit.MILLISECONDS);

		}
	}

	@Override
	public String read() throws IOException {
		return ConnectionHelper.read(this.payload);
	}

	@Override
	public synchronized boolean isValid() {
		return this.userId != null && this.payload != null && this.payload.isOpen();
	}

	@Override
	public void destory() {
		if (this.payload == null) {
			return;
		}
		synchronized (this.payload) {
			try {
				if (this.payload.isOpen()) {
					this.payload.close();
				}
			} catch (IOException e) {
				logger.error("close tcp connection due to error", e);
			}
		}
		this.payload = null;
		this.userId = null;
	}

	@Override
	public long getConnectedPeriod() {
		return System.currentTimeMillis() - this.connectTime;
	}

	@Override
	public long getConnectionTime() {
		return this.connectTime;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("userId", this.userId)
			.append("id", this.getId())
			.append("connectTime", this.getConnectedPeriod() + "ms")
			.append("meta", this.getMetaData())
			.toString();
	}

	private ConnectionMetaData metaData = new ConnectionMetaData();

	public ConnectionMetaData getMetaData() {
		return this.metaData;
	}

}
