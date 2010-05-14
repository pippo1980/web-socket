/**
 *
 */
package com.rensea.message.server.spi.dto.converter;

import com.rensea.message.dto.Message;
import com.rensea.message.dto.MessageType;

/**
 * @author pippo
 */
public interface MessageConverter {

	String convert(Message message);

	boolean support(MessageType messageType);

	MessageType getSupportType();

}
