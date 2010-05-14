package com.rensea.message.spi.queue;

import java.io.Serializable;

import com.alisoft.xplatform.asf.cache.memcached.client.MemCachedClient;
import com.alisoft.xplatform.asf.cache.memcached.client.SockIOPool;

public class MemcachedQueue implements QueueOperation {

	private static MemCachedClient mcc;
	private static SockIOPool pool = null;

	public void initialize() {
		if (pool == null) {
			mcc = new MemCachedClient(poolName);
			mcc.setCompressEnable(true);
			mcc.setCompressThreshold(64 * 1024);
			mcc.setSanitizeKeys(false);
			mcc.setPrimitiveAsString(true);

			String[] serverlist = configuration.getQueueServerList();
			pool = SockIOPool.getInstance(poolName);
			pool.setServers(serverlist);
			pool.setInitConn(5);
			pool.setMinConn(5);
			pool.setMaxConn(50);
			pool.setMaxIdle(1000 * 60 * 60 * 6);
			pool.setMaintSleep(1000 * 60 * 5);
			pool.setNagle(false);
			pool.setSocketTO(60000);
			pool.setSocketConnectTO(60000);
			pool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);
			pool.initialize();
		}
	}

	@Override
	public <T extends Serializable> void offer(String queueName, T element) {
		if (mcc == null)
			initialize();
		mcc.set(queueName, element);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> T poll(String queueName, long timeout) {
		if (mcc == null)
			initialize();
		Object object = mcc.get(String.format("%s/t=%d", queueName, timeout));
		return (T) object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> T poll(String queueName) {
		if (mcc == null)
			initialize();
		return (T) mcc.get(queueName);
	}

	private QueueConfiguration configuration;

	public void setConfiguration(QueueConfiguration configuration) {
		this.configuration = configuration;
	}

	private String poolName = "comet";

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

}
