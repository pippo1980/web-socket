package com.rensea.message.spi;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.rensea.message.dto.Message;

public interface MessageListener<T extends Message> {

	/**
	 * @return listener 注册用户的唯一标识,必须保证在一个Agent上该值是唯一的
	 */
	AgentUser getAgentUser();

	/**
	 * 接收消息
	 * 
	 * @param message
	 * @throws MessageException
	 */
	void onMessage(T message) throws MessageException;

	/**
	 * @return 是否可用
	 */
	boolean isValid();

	/**
	 * 销毁侦听器资源
	 */
	void destory();

	public static class AgentUser {

		public AgentUser(String agentUser) {
			this.agentUser = agentUser;
		}

		private String agentUser;

		public String getAgentUser() {
			return agentUser;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}

		/**
		 * @see java.lang.Object#equals(Object)
		 */
		@Override
		public boolean equals(Object object) {
			if (!(object instanceof AgentUser)) {
				return false;
			}
			AgentUser rhs = (AgentUser) object;
			return new EqualsBuilder().append(this.agentUser, rhs.agentUser).isEquals();
		}

		/**
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return new HashCodeBuilder(1084772497, -1086031639).append(this.agentUser).toHashCode();
		}

	}

}
