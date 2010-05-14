/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-2 - 上午10:41:41
 */
package com.rensea.message.server.tcp.connection.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.xsocket.connection.INonBlockingConnection;

import com.rensea.message.server.tcp.connection.command.AuthenticationCommandHandler;
import com.rensea.message.server.tcp.connection.command.CommandHandler;
import com.rensea.message.server.tcp.connection.command.HashTagCommandHandler;
import com.rensea.message.server.tcp.connection.command.PingCommandHandler;
import com.rensea.message.server.tcp.connection.command.SubscriptionCommandHandler;
import com.rensea.message.server.tcp.connection.command.UnsubscriptonCommandHandler;

/**
 * @since 2009-12-2
 * @author pippo
 */
@Component("commandFilter")
public class CommandFilter implements ConnectionFilter, InitializingBean {

	private Logger logger = LoggerFactory.getLogger(CommandFilter.class);

	@Override
	public boolean doFilter(String result, INonBlockingConnection connection) throws IOException {
		try {
			JSONArray commands = JSONArray.fromObject(result);
			for (int i = 0; i < commands.size(); i++) {
				JSONObject command = commands.getJSONObject(i);
				String command_name = command.getString("command");
				CommandHandler commandHandler = this.shell.get(command_name);
				if (commandHandler != null) {
					commandHandler.handle(connection, command);
				}
			}
		} catch (Exception e) {
			this.logger.error("authentication due to error:", e);
			return false;
		}

		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.shell.put("authentication", this.authenticationCommandHandler);
		this.shell.put("subscription", this.subscriptionCommandHandler);
		this.shell.put("unsubscription", this.unsubscriptonCommandHandler);
		this.shell.put("hashtag", this.hashTagCommandHandler);
		this.shell.put("ping", this.pingCommandHandler);
	}

	private Map<String, CommandHandler> shell = new HashMap<String, CommandHandler>();

	@Resource
	private AuthenticationCommandHandler authenticationCommandHandler;

	@Resource
	private SubscriptionCommandHandler subscriptionCommandHandler;

	@Resource
	private UnsubscriptonCommandHandler unsubscriptonCommandHandler;

	@Resource
	private HashTagCommandHandler hashTagCommandHandler;

	@Resource
	private PingCommandHandler pingCommandHandler;

}
