package com.rensea.message.spi;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA. User: cao Date: 2009-2-15 Time: 23:05:20 To change this template use File | Settings | File
 * Templates.
 */
public class XMemcachedOperation {

	private static Logger logger = LoggerFactory.getLogger(XMemcachedOperation.class);

	private static final XMemcachedOperation instance = new XMemcachedOperation();

	public static MemcachedClient xmc = null;

	private String servers;

	public void setServers(String servers) {
		this.servers = servers;
	}

	public void initialize() {
		try {
			MemcachedClientBuilder builder = newBuilder(StringUtils.join(this.servers.split(","), " "));
			xmc = builder.build();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Do not use this method directly
	 */
	public static XMemcachedClient getClient() {
		return (XMemcachedClient) xmc;
	}

	private static MemcachedClientBuilder newBuilder(String server) {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(server));
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());
		return builder;
	}

	public static XMemcachedOperation getInstance() {
		return instance;
	}

	public boolean delete(String key) {
		try {
			return xmc.delete(key);
		} catch (Exception e) {
			logger.error("delete:" + key + " due to error", e);
		}
		return false;
	}

	public <T> Map<String, T> get(Collection<String> keyCollections) {
		try {
			return xmc.get(keyCollections);
		} catch (Exception e) {
			logger.error("get:" + keyCollections + " due to error", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		try {
			return (T) xmc.get(key);
		} catch (Exception e) {
			logger.error("get:" + key + " due to error", e);
		}
		return null;
	}

	public boolean set(String key, int exp, Object value) {
		try {
			return xmc.set(key, exp, value);
		} catch (Exception e) {
			logger.error("set:" + key + " due to error", e);
		}
		return false;
	}

	public void flushAll() {
		try {
			xmc.flushAll();
		} catch (Exception e) {
			logger.error("flushAll due to error", e);
		}
	}

	public long incr(String key, long num) {
		try {
			return xmc.incr(key, num);
		} catch (Exception e) {
			logger.error("incr:" + key + " due to error", e);
		}
		return num;
	}

}
