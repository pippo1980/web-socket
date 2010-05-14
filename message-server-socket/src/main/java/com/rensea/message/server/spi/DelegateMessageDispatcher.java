/**
 *
 */
package com.rensea.message.server.spi;

import java.util.Collection;
import java.util.concurrent.ExecutorService;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.rensea.message.dto.Message;
import com.rensea.message.dto.MessageType;
import com.rensea.message.dto.SystemMessage;
import com.rensea.message.server.monitor.MessageDispatcherMBean;
import com.rensea.message.server.tcp.connection.command.AuthenticationCommandHandler;
import com.rensea.message.spi.DefatulMessageDispatcher;
import com.rensea.message.spi.MessageDispatcher;
import com.rensea.message.spi.MessageListener;

/**
 * @author pippo
 */
@Component
public class DelegateMessageDispatcher implements MessageDispatcher, InitializingBean {

	@Resource
	private MessageDispatcherMBean dispatcherMBean;

	@Resource
	private ExecutorService executorService;

	private DefatulMessageDispatcher dispatcher = null;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.dispatcher = new DefatulMessageDispatcher(this.executorService);
		//		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
		//
		//			@Override
		//			public void run() {
		//				if ((DelegateMessageDispatcher.this.lastMessageReveiveTime.get() + 1000 * 60 * 5) < System
		//						.currentTimeMillis()) {
		//					SystemLog log = new SystemLog();
		//					log.setDateCreated(System.currentTimeMillis());
		//					log.setSystemId("message-server");
		//					log.setRemark("5分钟内没有收到新消息,重启message server");
		//					log.setParams(String
		//							.format("{lastMessageReveiveTime:%s,curTime:%s}",
		//									DelegateMessageDispatcher.this.lastMessageReveiveTime + "", System
		//											.currentTimeMillis()
		//											+ ""));
		//					RenseaLoggerFactory.getLogger().log(log);
		//				}
		//			}
		//		}, 1, 3, TimeUnit.MINUTES);
	}

	@Override
	public boolean dispatch(Collection<MessageListener<? extends Message>> listeners, final Message message) {
		/* monitor */
		DelegateMessageDispatcher.this.dispatcherMBean.onNewMessage(message);
		if (MessageType.SYSTEM.equals(message.getMessageType())) {
			AuthenticationCommandHandler.systemMessage = (SystemMessage) message;
		}
		return this.dispatcher.dispatch(listeners, message);
	}
}
