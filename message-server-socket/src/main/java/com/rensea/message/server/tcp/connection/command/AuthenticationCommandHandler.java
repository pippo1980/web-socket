/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-2 - 上午10:48:42
 */
package com.rensea.message.server.tcp.connection.command;

import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xsocket.connection.INonBlockingConnection;

import com.rensea.message.dto.SystemMessage;
import com.rensea.message.server.tcp.connection.Connection;
import com.rensea.message.server.tcp.connection.ConnectionManager;
import com.rensea.message.server.tcp.connection.ConnectionProxy;
import com.sirius.core.utils.StringUtils;

/**
 * 用户认证命令
 *
 * @since 2009-12-2
 * @author pippo
 */
@Component("authenticationCommandHandler")
public class AuthenticationCommandHandler implements CommandHandler {

	private Logger logger = LoggerFactory.getLogger(AuthenticationCommandHandler.class);

	public static SystemMessage systemMessage = null;

	private static String success_command = "{'userId':'%s','connected':%s,'time':%s,'clientType':'%s'}";

	@Resource
	private ScheduledExecutorService scheduledExecutorService;

	@Resource
	private ConnectionManager connectionManager;

	@Override
	public void handle(INonBlockingConnection connection, JSONObject command) throws Exception {
		if (StringUtils.isBlank(command.getString("userId"))) {
			connection.close();
			this.logger.error("not invalidate authentication:" + command.toString() + ", close the connection");
			return;
		}

		/* 忽略不提供clientType的客户端请求,虽然目前没有用处 */
		String clientType = command.get("clientType") + "";
		if (StringUtils.isBlank(clientType)) {
			this.logger.error("not invalidate authentication:" + command.toString()
					+ ", the client type is null, close the connection");
			return;
		}

		Connection con = this.connectionManager.getConnection(connection.getId());
		if (con == null) {
			con = new ConnectionProxy(command.getString("userId"), connection, this.scheduledExecutorService);
			this.connectionManager.registConnection(con);
		} else {
			con.setUserId(command.getString("userId"));
			con.setExecutorService(this.scheduledExecutorService);
		}

		JSONArray messageTypes = command.getJSONArray("messageTypes");
		for (int i = 0; i < messageTypes.size(); i++) {
			con.getMetaData().subscribe_message(messageTypes.getString(i));
		}

		/* 验证通过,返回成功信息 */
		con.write(String.format(success_command,
				command.getString("userId"),
				"true",
				System.currentTimeMillis(),
				clientType));

		/* smell bad 每个新连接验证通过,重新发送系统消息 */
		if (systemMessage != null && StringUtils.isNotBlank(systemMessage.getText()) && systemMessage.getTurnon()) {
			con.write(systemMessage);
		}
	}

}
