/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2010-1-13 - 上午11:53:26
 */
package com.rensea.message.server.monitor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;
import javax.management.Notification;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Service;

import com.renjian.message.server.jmx.IConnectionManagerMBean;
import com.rensea.message.server.tcp.connection.Connection;
import com.sirius.core.utils.StringUtils;

/**
 * @since 2010-1-13
 * @author pippo
 */
@Service("connectionManagerMBean")
@ManagedResource(objectName = "bean:name=connectionManager", description = "socket connection manager bean", log = true, logFile = "jmx.log", currencyTimeLimit = 5)
public class ConnectionManagerMBean implements NotificationPublisherAware, IConnectionManagerMBean {

	@Resource
	private ExecutorService executorService;

	private NotificationPublisher publisher;

	private static AtomicLong notification_count = new AtomicLong(0);

	private static AtomicInteger connection_count = new AtomicInteger(0);

	@Override
	public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
		this.publisher = notificationPublisher;
	}

	public void onConnected(final String connection) {
		//		this.executorService.submit(new Runnable() {
		//
		//			@Override
		//			public void run() {
		//				ConnectionManagerMBean.this.publisher.sendNotification(new Notification("Connected", connection,
		//						notification_count.addAndGet(1)));
		//			}
		//		});
	}

	public void onAuthentication(final Connection connection) {
		this.executorService.execute(new Runnable() {

			@Override
			public void run() {
				ConnectionManagerMBean.this.publisher.sendNotification(new Notification("Authentication", String
						.format("{'connectionId':'%s','userId':'%s'}", connection.getId(), connection.getUserId()),
						notification_count.addAndGet(1)));
			}
		});
	}

	public void onClosed(final Connection connection) {
		final String connectionId = connection.getId();
		final String userId = connection.getUserId();
		this.executorService.execute(new Runnable() {

			@Override
			public void run() {
				if (StringUtils.isBlank(userId)) {
					return;
				}
				ConnectionManagerMBean.this.publisher.sendNotification(new Notification("Closed", String.format(
						"{'connectionId':'%s','userId':'%s'}", connectionId, userId), notification_count.addAndGet(1)));
			}
		});
	}

	public void setConnectionCount(int count) {
		connection_count.set(count);
	}

	@ManagedAttribute(description = "获取当前活跃socket链接数", defaultValue = "0")
	@Override
	public int getConnectionCount() {
		return connection_count.get();
	}

}
