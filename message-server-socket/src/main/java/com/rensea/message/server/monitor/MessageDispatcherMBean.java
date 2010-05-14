/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2010-1-13 - 下午02:35:18
 */
package com.rensea.message.server.monitor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;
import javax.management.Notification;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Service;

import com.renjian.message.server.jmx.IMessageDispatcherMBean;
import com.rensea.message.dto.Message;
import com.sirius.core.utils.DateFormatUtils;

/**
 * @since 2010-1-13
 * @author pippo
 */
@Service("messageDispatcherMBean")
@ManagedResource(objectName = "bean:name=messageDispatcher", description = "message dispatcher bean", log = true, logFile = "jmx.log", currencyTimeLimit = 5)
public class MessageDispatcherMBean implements NotificationPublisherAware, IMessageDispatcherMBean {

	private static String last_message;

	private static AtomicLong last_message_recived_date = new AtomicLong(System.currentTimeMillis());

	private static AtomicLong message_count = new AtomicLong(0);

	@Resource
	private ExecutorService executorService;

	private NotificationPublisher publisher;

	@Override
	public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
		this.publisher = notificationPublisher;
	}

	@ManagedAttribute(description = "接收到的最后一条消息")
	public String getLastMessage() {
		return last_message;
	}

	@ManagedAttribute(description = "接收到最后一条消息的时间")
	public String getLastReciveDate() {
		return DateFormatUtils.format(last_message_recived_date.get(), "yyyy-MM-dd HH:mm:ss");
	}

	@ManagedAttribute(description = "分发消息总数")
	public long getMessageCount() {
		return message_count.get();
	}

	public void onNewMessage(final Message message) {
		this.executorService.execute(new Runnable() {

			@Override
			public void run() {
				last_message = message.toJson();
				last_message_recived_date.set(System.currentTimeMillis());
				MessageDispatcherMBean.this.publisher.sendNotification(new Notification("Message", last_message,
						message_count.addAndGet(1), message.getMessageType().name()));
			}
		});
	}

}
