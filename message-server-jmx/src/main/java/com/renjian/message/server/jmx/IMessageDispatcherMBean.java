/**
 * project : message-server-jmx
 * user created : pippo
 * date created : 2010-1-15 - 下午03:11:31
 */
package com.renjian.message.server.jmx;

/**
 * @since 2010-1-15
 * @author pippo
 */
public interface IMessageDispatcherMBean {

	String getLastMessage();

	String getLastReciveDate();

	long getMessageCount();

}
