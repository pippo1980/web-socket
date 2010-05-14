package com.rensea.message.spi;

import com.rensea.message.dto.Message;

public interface MessageQueueAgent {

	void sendMessage(Message message);

	void init();

	void destory();

}
