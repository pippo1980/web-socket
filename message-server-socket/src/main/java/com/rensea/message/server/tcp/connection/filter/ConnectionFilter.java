/**
 * project : server
 * user created : pippo
 * date created : 2009-8-16 - 上午10:56:45
 */
package com.rensea.message.server.tcp.connection.filter;

import java.io.IOException;

import org.xsocket.connection.INonBlockingConnection;

/**
 * @since 2009-8-16
 * @author Administrator
 */
public interface ConnectionFilter {

	boolean doFilter(String result, INonBlockingConnection connection) throws IOException;

}
