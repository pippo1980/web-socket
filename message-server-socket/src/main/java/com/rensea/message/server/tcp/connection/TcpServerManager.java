/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2010-1-15 - 上午10:23:43
 */
package com.rensea.message.server.tcp.connection;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CustomizableThreadCreator;
import org.xsocket.connection.IHandler;
import org.xsocket.connection.Server;

/**
 * @since 2010-1-15
 * @author pippo
 */
public class TcpServerManager {

	private Logger logger = LoggerFactory.getLogger(TcpServerManager.class);

	private Collection<Server> servers = new CopyOnWriteArraySet<Server>();

	public void init() throws Throwable {
		String[] _address_list = this.address_list.split(";");
		for (String address : _address_list) {
			String[] ip_port = address.split(":");
			if (logger.isInfoEnabled()) {
				logger.info("start tcp server ip:" + ip_port[0] + ",port:" + NumberUtils.toInt(ip_port[1], 9999));
			}
			Server server = new Server(ip_port[0], NumberUtils.toInt(ip_port[1], 9999), this.handler);
			server.setServerName("tcp_server_" + address);
			server.setStartUpLogMessage("tcp_server_" + address + " start successful");

			server.setAutoflush(this.autoflush);
			server.setConnectionTimeoutMillis(this.connectionTimeoutMillis);
			server.setIdleTimeoutMillis(this.idleTimeoutMillis);
			server.setMaxConcurrentConnections(this.maxConcurrentConnections);

			ThreadPoolExecutor executor = new ThreadPoolExecutor(this.maxConcurrentConnections,
				this.maxConcurrentConnections * 2,
				60,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
			executor.setThreadFactory(new TcpServerThreadFactory("message_tcp_server_" + address + "_"));
			server.setWorkerpool(executor);
			server.start();
			this.servers.add(server);
		}
	}

	public void destory() {
		for (Server server : this.servers) {
			server.close();
		}
	}

	private boolean autoflush;

	private long connectionTimeoutMillis;

	private long idleTimeoutMillis;

	private int maxConcurrentConnections;

	private String address_list;

	private IHandler handler;

	public boolean isAutoflush() {
		return this.autoflush;
	}

	public void setAutoflush(boolean autoflush) {
		this.autoflush = autoflush;
	}

	public long getConnectionTimeoutMillis() {
		return this.connectionTimeoutMillis;
	}

	public void setConnectionTimeoutMillis(long connectionTimeoutMillis) {
		this.connectionTimeoutMillis = connectionTimeoutMillis;
	}

	public long getIdleTimeoutMillis() {
		return this.idleTimeoutMillis;
	}

	public void setIdleTimeoutMillis(long idleTimeoutMillis) {
		this.idleTimeoutMillis = idleTimeoutMillis;
	}

	public int getMaxConcurrentConnections() {
		return this.maxConcurrentConnections;
	}

	public void setMaxConcurrentConnections(int maxConcurrentConnections) {
		this.maxConcurrentConnections = maxConcurrentConnections;
	}

	public String getAddress_list() {
		return this.address_list;
	}

	public void setAddress_list(String addressList) {
		this.address_list = addressList;
	}

	public IHandler getHandler() {
		return this.handler;
	}

	public void setHandler(IHandler handler) {
		this.handler = handler;
	}

	private class TcpServerThreadFactory extends CustomizableThreadCreator implements ThreadFactory {

		public TcpServerThreadFactory(String threadNamePrefix) {
			super(threadNamePrefix);
			this.setThreadGroupName(threadNamePrefix);
		}

		@Override
		public Thread newThread(Runnable runnable) {
			return this.createThread(runnable);
		}

	}

}
