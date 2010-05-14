/**
 *
 */
package com.rensea.message.spi;

import com.rensea.message.dto.Message;
import com.rensea.message.spi.MessageListener.AgentUser;

/**
 * @author pippo
 */
public interface MessageQueueConsumer {

	/**
	 * 注册侦听器
	 * 
	 * @param listener
	 */
	void addListener(MessageListener<? extends Message> listener);

	//	/**
	//	 * 注销侦听器
	//	 *
	//	 * @param listener
	//	 */
	//	void removeListener(MessageListener<? extends Message> listener);

	/**
	 * 注销用户所有侦听器
	 * 
	 * @param agentUser
	 */
	void removeListener(AgentUser agentUser);

	/**
	 * @param agentUser
	 * @return 用户注册的侦听器,如果没有返回null
	 */
	MessageListener<? extends Message> getMessageListener(AgentUser agentUser);

	//	/**
	//	 * 注销用户对应消息侦听器
	//	 *
	//	 * @param agentUser
	//	 * @param messageType
	//	 */
	//	void removeListener(AgentUser agentUser, MessageType messageType);

	/**
	 * 初始化实例资源
	 */
	void init();

	/**
	 * 销毁实例资源
	 */
	void destory();

}
