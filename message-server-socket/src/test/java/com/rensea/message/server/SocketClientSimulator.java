/**
 *
 */
package com.rensea.message.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sirius.core.utils.ArrayUtils;

/**
 * @author pippo
 */
public class SocketClientSimulator {

	private Logger logger = LoggerFactory.getLogger(SocketClientSimulator.class);

	public SocketClientSimulator(String host, int port) {
		this.host = host;
		this.port = port;
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				if (connected == false) {
					close();
					connect();
				}
			}
		}, 10, 2, TimeUnit.SECONDS);
	}

	public boolean isConnected() {
		return this.connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public long getConnect_time() {
		return this.connect_time;
	}

	public void setConnect_time(long connectTime) {
		this.connect_time = connectTime;
	}

	public long getConnected_time() {
		return this.connected_time;
	}

	public void setConnected_time(long connectedTime) {
		this.connected_time = connectedTime;
	}

	private String host;

	private int port;

	private Socket socket = null;

	private boolean connected = false;

	private long connect_time;

	private long connected_time;

	public void connect() {
		try {
			connect_time = System.currentTimeMillis();
			socket = new Socket();
			socket.connect(new InetSocketAddress(host, port), 30 * 1000);
			socket.getOutputStream()
				.write(("[{command:'authentication',userId:'" + 292 + "',messageTypes:['STATUS'],clientType:'flash'}]").getBytes());
			connected = true;
			connected_time = System.currentTimeMillis();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connected = false;
		}
	}

	public void close() {
		if (this.socket == null) {
			return;
		}
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.socket = null;
	}

	public byte[] read() {
		if (!this.connected) {
			return ArrayUtils.EMPTY_BYTE_ARRAY;
		}

		try {
			int i = socket.getInputStream().available();
			if (i <= 0) {
				return ArrayUtils.EMPTY_BYTE_ARRAY;
			}
			byte[] bb = new byte[i];
			socket.getInputStream().read(bb);
			return bb;
		} catch (Exception e) {
			logger.error("read socket due to error");
			connected = false;
		}
		return ArrayUtils.EMPTY_BYTE_ARRAY;
	}

	public void write(byte[] command) {
		try {
			socket.getOutputStream().write(command);
		} catch (IOException e) {
			logger.error("write command to socket due to error");
			connected = false;
		}
	}

}
