/**
 * project : message-remote
 * user created : pippo
 * date created : 2009-11-12 - 下午01:14:45
 */
package com.rensea.message.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.rensea.message.api.http.HttpStrategyApi;
import com.rensea.message.dto.StatusMessage;

/**
 * @since 2009-11-12
 * @author pippo
 */
public class HttpApiTest {

	private HttpStrategyApi httpStrategyApi = new HttpStrategyApi();

	private static int count;

	@Test
	public void testNewCommand() throws InterruptedException {
		this.httpStrategyApi.setHost("talk.rj8g.com");
		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		for (int i = 0; i < 10000; i++) {

			executorService.execute(new Runnable() {

				@Override
				public void run() {
					StatusMessage message = new StatusMessage();
					message.setSender(1294L);
					message.setStatusType("TEXT");
					message.setText("" + count++);
					message.setSource("message-test");
					try {
						HttpApiTest.this.httpStrategyApi.newStatus(message);
					} catch (ApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}

		while (!executorService.isTerminated()) {
			Thread.sleep(1000);
		}

	}

}
