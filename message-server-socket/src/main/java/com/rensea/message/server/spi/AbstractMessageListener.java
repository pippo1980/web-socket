/**
 *
 */
package com.rensea.message.server.spi;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rensea.message.dto.Message;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageException;
import com.rensea.message.spi.MessageListener;

/**
 * @author pippo
 */
public abstract class AbstractMessageListener<T extends Message> implements MessageListener<T> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public AbstractMessageListener(Connection connection) {
		this.connection = connection;
		this.agentUser = SocketUser.create(connection);
	}

	private AgentUser agentUser;

	@Override
	public AgentUser getAgentUser() {
		return this.agentUser;
	}

	@Override
	public final void onMessage(T message) throws MessageException {
		if (this.connection == null) {
			this.logger.warn("the connection is null, set the listener:" + this + " to invalid");
			this.inactive();
			return;
		}

		if (this.connection.getPayload() == null) {
			this.logger.warn("the connection payload is null, set the listener:" + this + " to invalid");
			this.inactive();
			return;
		}

		if (this.connection.getPayload().isOpen() == false) {
			this.logger.warn("the connection:" + this.connection.getPayload() + " has been close, set the listener:"
					+ this + " to invalid");
			this.inactive();
			return;
		}

		this.handle(message);
	}

	protected abstract void handle(T message) throws MessageException;

	private AtomicBoolean valid = new AtomicBoolean(true);

	@Override
	public boolean isValid() {
		return this.valid.get();
	}

	public void inactive() {
		this.valid.set(false);
	}

	public void active() {
		this.valid.set(true);
	}

	@Override
	public void destory() {
		if (this.connection != null) {
			this.connection.destory();
		}
		this.connection = null;
		this.inactive();
	}

	protected Connection connection;

	public Connection getConnection() {
		return this.connection;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("agentUser", this.getAgentUser()).toString();
	}

}
