/**
 * project : server
 * user created : pippo
 * date created : 2009-8-16 - 上午11:00:33
 */
package com.rensea.message.server.tcp.connection.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.xsocket.connection.INonBlockingConnection;

import com.rensea.message.server.tcp.connection.ConnectionHelper;

/**
 * @since 2009-8-16
 * @author Administrator
 */
@Component("policyFilter")
public class ConnectionPolicyFilter implements ConnectionFilter {

	private static String policy_request = "<policy-file-request/>";

	private static String policy = "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\" /></cross-domain-policy>\0";

	@Override
	public boolean doFilter(String result, INonBlockingConnection connection) throws IOException {
		if (policy_request.equals(result)) {
			ConnectionHelper.write(connection, policy);
			return true;
		} else {
			return false;
		}
	}

}
