package com.rensea.message.spi;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CommonExecutors {
	public static final ThreadPoolExecutor EXECUTOR = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	public static final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
	
	public static <V> void submit(Callable<V> task){
		EXECUTOR.submit(task);
	}
	
	public static void execute(Runnable task) {
		EXECUTOR.execute(task);
	}
	
	public static void schedule(Runnable task,long delay,long period,TimeUnit unit){
		scheduledExecutor.scheduleAtFixedRate(task, delay, period, unit);
	}
}
