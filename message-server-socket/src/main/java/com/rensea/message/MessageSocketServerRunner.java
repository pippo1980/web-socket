/**
 * project : des-rss-fetcher
 * user created : pippo
 * date created : 2009-8-30 - 下午07:24:49
 */
package com.rensea.message;

import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.commons.lang.math.NumberUtils;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @since 2009-8-30
 * @author pippo
 */
public class MessageSocketServerRunner {

	public static void main(String[] args) throws Exception {
		//		System.setProperty("log4j.configuration", "log4j.properties");
		//		context = new ClassPathXmlApplicationContext("classpath*:*.context.xml");
		//		context.start();
		//		BeanLocator.setApplicationContext(context);
		//
		//		while (true) {
		//			Thread.sleep(1000 * 60);
		//		}

		String setting_file = null;
		if (args == null || args.length == 0) {
			setting_file = "/develop.properties";
		} else {
			setting_file = ("/" + args[0] + ".properties").replaceAll("-P", "");
		}
		settings.load(Thread.currentThread().getClass().getResourceAsStream(setting_file));
//		createMBeanServer();
		start();
	}

	public static void destory() throws Exception {
		context.destroy();
		context = null;
	}

	private static ClassPathXmlApplicationContext context = null;

	private static Properties settings = new Properties();

	private static Server server = new Server();

	private static MBeanServer mBeanServer = MBeanServerFactory.createMBeanServer(null);

	private static void createMBeanServer() throws Exception {
		LocateRegistry.createRegistry(NumberUtils.toInt(settings.getProperty("message.server.jmx.port"), 10990));
		/* init connector */
		Map<String, Object> environment = new HashMap<String, Object>();
		environment.put("jmx.remote.x.password.file", "jmx.remote.x.password.file");
		environment.put("jmx.remote.x.access.file", "jmx.remote.x.access.file");
		JMXServiceURL url = new JMXServiceURL(settings.getProperty("message.server.jmx.connector.url"));
		JMXConnectorServer jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, environment,
				mBeanServer);
		LoggerFactory.getLogger(MessageSocketServerRunner.class).warn(
				settings.getProperty("message.server.jmx.connector.url"));
		/* connector */
		mBeanServer.registerMBean(jmxConnectorServer, new ObjectName("connector:name=message_server"));
		/* system monitor */
		mBeanServer.registerMBean(ManagementFactory.getClassLoadingMXBean(), new ObjectName("system:name=classLoading"));
		mBeanServer.registerMBean(ManagementFactory.getMemoryMXBean(), new ObjectName("system:name=memory"));
		mBeanServer.registerMBean(ManagementFactory.getThreadMXBean(), new ObjectName("system:name=thread"));
		mBeanServer.registerMBean(ManagementFactory.getOperatingSystemMXBean(),
				new ObjectName("system:name=operatingSystem"));
		mBeanServer.registerMBean(ManagementFactory.getRuntimeMXBean(), new ObjectName("system:name=runtime"));
		/* start connector */
		jmxConnectorServer.start();
	}

	public static void start() throws Exception {

		QueuedThreadPool boundedThreadPool = new QueuedThreadPool();
		boundedThreadPool.setMaxThreads(5);
		server.setThreadPool(boundedThreadPool);

		//		Connector connector = new SelectChannelConnector();
		//		connector.setPort(8097);
		//		server.addConnector(connector);

		WebAppContext context = new WebAppContext("src/main/webapp", "/");
		server.setHandler(context);

		server.setStopAtShutdown(true);
		server.setSendServerVersion(true);

		/* jmx support */
//		MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
//		server.getContainer().addEventListener(mBeanContainer);
//		mBeanContainer.start();

		server.start();
		server.join();
	}

	public static void stop() throws Exception {
		server.stop();
	}
}
