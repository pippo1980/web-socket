package com.rensea.message.server.spi;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rensea.message.server.spi.messagehandler.MessageHandler;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.spi.MessageQueueConsumer;
import com.rensea.message.spi.MessageListener.AgentUser;

/**
 * Created by IntelliJ IDEA. User: pippo Date: 2009-8-28 Time: 11:46:48 To change this template use File | Settings |
 * File Templates.
 */
@Component
public class ListenerRegistor {

	//	private List<MessageHandlerAdapter> handlers = new ArrayList<MessageHandlerAdapter>();
	//
	//	@Override
	//	public void afterPropertiesSet() throws Exception {
	//		this.handlers.add(new MessageHandlerAdapter(this.friendsStatusMessageHandler, "STATUS", MessageType.STATUS));
	//		this.handlers
	//				.add(new MessageHandlerAdapter(this.strangerStatusMessageHandler, "ALL_STATUS", MessageType.STATUS));
	//		this.handlers.add(new MessageHandlerAdapter(this.userTagMessageHandler, "FOR_USER_TAG", MessageType.STATUS));
	//		this.handlers.add(new MessageHandlerAdapter(this.stickStatusMessageHandler, MessageType.STICK_STATUS.name(),
	//				MessageType.STICK_STATUS));
	//		this.handlers.add(new MessageHandlerAdapter(this.systemMessageHandler, MessageType.SYSTEM.name(),
	//				MessageType.SYSTEM));
	//		this.handlers.add(new MessageHandlerAdapter(this.auditedConversationMessageHandler,
	//				MessageType.AUDITED_CONVERSATION_MESSAGE.name(), MessageType.AUDITED_CONVERSATION_MESSAGE));
	//	}

	//	public void regist(String messageTypeStr, Connection con) {
	//		if ("ALL_STATUS".equals(messageTypeStr)) {
	//			this.regist(con, "ALL_STATUS");
	//		} else {
	//			MessageType messageType = MessageType.valueOf(messageTypeStr);
	//			this.regist(con, messageType.name());
	//		}
	//	}

	public void regist(Connection con) {
		AgentUser agentUser = SocketUser.create(con);
		SocketMessageDelegateListener listener = (SocketMessageDelegateListener) this.messageQueueConsumer
				.getMessageListener(agentUser);
		if (listener == null) {
			listener = new SocketMessageDelegateListener(con);
			listener.setMessage_handler_mapping(this.message_handler_mapping);
			this.messageQueueConsumer.addListener(listener);
		}
	}

	//	public void deregist(Connection con, String messageType) {
	//		AgentUser agentUser = SocketUser.create(con);
	//		SocketMessageDelegateListener listener = (SocketMessageDelegateListener) this.messageQueueConsumer
	//				.getMessageListener(agentUser);
	//		if (listener == null) {
	//			return;
	//		}
	//		listener.unsubscribe(messageType);
	//	}

	public void deregist(Connection con) {
		AgentUser agentUser = SocketUser.create(con);
		this.messageQueueConsumer.removeListener(agentUser);
	}

	@Resource
	private MessageQueueConsumer messageQueueConsumer;

	@SuppressWarnings("unchecked")
	@Resource(name = "message_handler_mapping")
	private Map<String, MessageHandler> message_handler_mapping;

}
