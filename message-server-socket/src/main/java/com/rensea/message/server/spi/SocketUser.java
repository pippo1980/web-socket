/**
 *
 */
package com.rensea.message.server.spi;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageListener.AgentUser;

/**
 * @author pippo
 */
public class SocketUser extends AgentUser {

	protected SocketUser(String agentUser) {
		super(agentUser);
	}

	public static SocketUser create(Connection connection) {
		String agentUser = connection.getUserId();
		SocketUser socketUser = new SocketUser(agentUser);
		socketUser.connectionId = connection.getId();
		return socketUser;
	}

	private String connectionId;

	public String getConnectionId() {
		return this.connectionId;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SocketUser)) {
			return false;
		}
		SocketUser rhs = (SocketUser) object;
		return new EqualsBuilder().append(this.getAgentUser(), rhs.getAgentUser()).append(this.connectionId,
				rhs.connectionId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-2122859147, 665854045).append(this.getAgentUser())
			.append(this.connectionId)
			.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("agentUser", this.getAgentUser()).append("connectionId",
				this.connectionId).toString();
	}

}
