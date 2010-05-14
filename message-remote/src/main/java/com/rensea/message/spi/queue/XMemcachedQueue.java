package com.rensea.message.spi.queue;

import java.io.Serializable;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.KestrelCommandFactory;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMemcachedQueue implements QueueOperation {

	private static Logger logger = LoggerFactory.getLogger(XMemcachedQueue.class);

	public MemcachedClient xmc = null;

	public void initialize() {
		if (this.xmc == null) {
			try {
				String[] serverlist = this.configuration.getQueueServerList();

				MemcachedClientBuilder builder = newBuilder(StringUtils.join(serverlist, " "));
				builder.setConnectionPoolSize(this.connectionPoolSize);
				this.xmc = builder.build();
				this.xmc.setOpTimeout(this.opTimeout);
				this.xmc.setPrimitiveAsString(true);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Xmemcached initialize error");
			}
		}
	}

	private static MemcachedClientBuilder newBuilder(String server) {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(server));
		builder.getConfiguration().setStatisticsServer(true);
		builder.setCommandFactory(new KestrelCommandFactory());
		return builder;
	}

	@Override
	public <T extends Serializable> void offer(String queueName, T element) {
		if (this.xmc == null) {
			this.initialize();
		}
		try {
			this.xmc.set(queueName, 0, element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> T poll(String queueName, long timeout) {
		if (this.xmc == null) {
			this.initialize();
		}
		try {
			Object object = this.xmc.get(String.format("%s/t=%d", queueName, timeout));
			return (T) object;
		} catch (Exception e) {
			logger.error(String.format("poll message from the queue:%s due to error", String.format("%s/t=%d",
					queueName, timeout)), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> T poll(String queueName) {
		if (this.xmc == null) {
			this.initialize();
		}
		try {
			return (T) this.xmc.get(queueName);
		} catch (Exception e) {
			logger.error(String.format("poll message from the queue:%s due to error", queueName), e);
			return null;
		}
	}

	private QueueConfiguration configuration;

	public void setConfiguration(QueueConfiguration configuration) {
		this.configuration = configuration;
	}

	private long opTimeout = 60000;

	public void setOpTimeout(long opTimeout) {
		this.opTimeout = opTimeout;
	}

	private int connectionPoolSize = 1;

	public void setConnectionPoolSize(int connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
	}

	@SuppressWarnings("unused")
	private String poolName = "comet";

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public static void main(String[] args) {
		QueueConfiguration configuration = new QueueConfiguration();
		configuration.setQueueServers("192.168.1.201:22133");
		XMemcachedQueue queue = new XMemcachedQueue();
		queue.setOpTimeout(1000);
		queue.setConnectionPoolSize(1);
		queue.setConfiguration(configuration);

		for (int i = 0; i < 500; i++) {
			try {
				queue.offer("test_queue", "xxxxxxxxxxxxxxxx");
				Thread.sleep(100);
				System.out.println(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
