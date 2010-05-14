/**
 *
 */
package com.rensea.message.server.tcp.connection;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.rensea.message.server.monitor.ConnectionManagerMBean;
import com.rensea.message.server.spi.ListenerRegistor;

@Service("connectionManager")
public class ConnectionManager implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

	private Map<String, Connection> connections = new ConcurrentHashMap<String, Connection>();

	public Map<String, Connection> getConnections() {
		return this.connections;
	}

	@Resource
	private ScheduledExecutorService scheduledExecutorService;

	@Resource
	private ListenerRegistor listenerRegistor;

	@Resource
	private ConnectionManagerMBean connectionManagerMBean;

	//	private static long max_period = 1000 * 60 * 60 * 8;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				if (logger.isInfoEnabled()) {
					logger.info("the active connection count is:" + ConnectionManager.this.connections.size());
				}
				ConnectionManager.this.connectionManagerMBean.setConnectionCount(ConnectionManager.this.connections.size());
				ConnectionManager.this.closeInvlidConnection();
			}
		}, 10, 30, TimeUnit.SECONDS);
	}

	private void closeInvlidConnection() {
		for (Connection connection : ConnectionManager.this.connections.values()) {
			if (connection.isValid()) {
				continue;
			}

			if (logger.isInfoEnabled()) {
				logger.info("close the invalid connection:" + connection);
			}

			try {
				this.removeConnection(connection);
			} catch (Exception e) {
				logger.error("try to close invalid connection:" + connection + " due to error", e);
			}
		}
	}

	public void registConnection(Connection connection) {
		if (logger.isInfoEnabled()) {
			logger.info("regist new connection:" + connection);
		}
		this.connections.put(connection.getPayload().getId(), connection);
		this.listenerRegistor.regist(connection);
		this.connectionManagerMBean.onAuthentication(connection);
	}

	public void removeConnection(Connection connection) {
		if (logger.isInfoEnabled()) {
			logger.info("remove connection:" + connection);
		}

		Iterator<Connection> iter = this.connections.values().iterator();
		while (iter.hasNext()) {
			Connection con = iter.next();
			if (con.equals(connection)) {
				iter.remove();
			}
		}

		this.connectionManagerMBean.onClosed(connection);
		this.listenerRegistor.deregist(connection);
		connection.destory();
	}

	public void removeConnection(String connectionId) {
		if (connectionId == null) {
			return;
		}
		Connection connection = this.getConnection(connectionId);
		if (connection == null) {
			return;
		}
		if (logger.isInfoEnabled()) {
			logger.info("remove connection:" + connection);
		}
		this.connectionManagerMBean.onClosed(connection);
		this.listenerRegistor.deregist(connection);
		this.connections.remove(connectionId);

		connection.destory();
	}

	public Connection getConnection(String connectionId) {
		return this.connections.get(connectionId);
	}

	public void releaseAll() {
		for (Connection connection : this.connections.values()) {
			connection.destory();
		}
	}

}
