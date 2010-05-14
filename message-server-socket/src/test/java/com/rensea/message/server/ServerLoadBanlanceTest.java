/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2010-3-31 - 下午02:36:15
 */
package com.rensea.message.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @since 2010-3-31
 * @author pippo
 */
public class ServerLoadBanlanceTest {

	private static List<SocketClientSimulator> simulators = new ArrayList<SocketClientSimulator>();

	private static int thread_size = 1;

	private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(thread_size);

	public static void main(String[] args) {
		for (int i = 0; i < thread_size; i++) {
			final SocketClientSimulator simulator = new SocketClientSimulator("localhost", 9999);
			simulator.connect();
			simulators.add(simulator);
			executorService.scheduleAtFixedRate(new Runnable() {

				@Override
				public void run() {
					byte[] bb = simulator.read();
					if (bb != null && bb.length > 0) {
						System.out.println(new String(bb));
					}
					//	simulator.write("[{'command':'ping','userId':'292','clientType':'flash'}]".getBytes());
				}
			}, 100, 50, TimeUnit.MILLISECONDS);
		}
	}
}
