/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-10-28 - 上午11:08:47
 */
package com.rensea.message.server;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @since 2009-10-28
 * @author pippo
 */
public class ScheduleThreadPoolExecutorFactoryBean extends ThreadPoolTaskExecutor implements FactoryBean {

	@Override
	public Object getObject() throws Exception {
		return this.executor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class getObjectType() {
		return ScheduledExecutorService.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void initialize() {
		if (this.logger.isInfoEnabled()) {
			this.logger.info("Initializing ThreadPoolExecutor"
					+ (this.beanName != null ? " '" + this.beanName + "'" : ""));
		}
		if (this.getThreadNamePrefix() == null && this.beanName != null) {
			this.setThreadNamePrefix(this.beanName + "-");
		}

		this.executor = new ScheduledThreadPoolExecutor(this.getCorePoolSize());
		this.executor.setMaximumPoolSize(this.getMaxPoolSize());
		this.executor.setKeepAliveTime(this.getKeepAliveSeconds(), TimeUnit.SECONDS);
		this.executor.setThreadFactory(this);
		this.executor.setRejectedExecutionHandler(this.rejectedExecutionHandler);

		if (this.allowCoreThreadTimeOut) {
			this.executor.allowCoreThreadTimeOut(this.allowCoreThreadTimeOut);
		}

	}

	@Override
	public void shutdown() {
		if (this.logger.isInfoEnabled()) {
			this.logger.info("Shutting down ThreadPoolExecutor"
					+ (this.beanName != null ? " '" + this.beanName + "'" : ""));
		}
		if (this.waitForTasksToCompleteOnShutdown) {
			this.getThreadPoolExecutor().shutdown();
		} else {
			this.getThreadPoolExecutor().shutdownNow();
		}
	}

	@Override
	public ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException {
		return this.executor;
	}

	private ScheduledThreadPoolExecutor executor;

	private String beanName;

	private int queueCapacity = Integer.MAX_VALUE;

	public String getBeanName() {
		return this.beanName;
	}

	@Override
	public void setBeanName(String beanName) {
		super.setBeanName(beanName);
		this.beanName = beanName;
	}

	public int getQueueCapacity() {
		return this.queueCapacity;
	}

	@Override
	public void setQueueCapacity(int queueCapacity) {
		super.setQueueCapacity(queueCapacity);
		this.queueCapacity = queueCapacity;
	}

	private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

	public RejectedExecutionHandler getRejectedExecutionHandler() {
		return this.rejectedExecutionHandler;
	}

	@Override
	public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
		this.rejectedExecutionHandler = rejectedExecutionHandler;
	}

	private boolean allowCoreThreadTimeOut = false;

	public boolean isAllowCoreThreadTimeOut() {
		return this.allowCoreThreadTimeOut;
	}

	@Override
	public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
		this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
	}

	private boolean waitForTasksToCompleteOnShutdown = false;

	public boolean isWaitForTasksToCompleteOnShutdown() {
		return this.waitForTasksToCompleteOnShutdown;
	}

	@Override
	public void setWaitForTasksToCompleteOnShutdown(boolean waitForTasksToCompleteOnShutdown) {
		this.waitForTasksToCompleteOnShutdown = waitForTasksToCompleteOnShutdown;
	}

}
