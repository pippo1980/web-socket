package com.rensea.message.dto;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageFactory {

	private static Logger logger = LoggerFactory.getLogger(MessageFactory.class);

	@SuppressWarnings("unchecked")
	public static <T extends Message> T fromJson(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		try {
			String messageType = jsonObject.getString("messageType");
			Class<?> clazz = MessageType.getTargetClass(messageType);
			if (clazz == null) {
				logger.error("can not find tyep define:" + messageType);
				return null;
			}
			Object message = JSONObject.toBean(jsonObject, clazz);
			return (T) message;
		} catch (Exception e) {
			logger.error("from json due to error", e);
			throw new MessageCastException(e);
		}
	}

	public static <T extends Message> String toJson(Message message) {
		return JSONObject.fromObject(message).toString();
	}

	public static void main(String[] args) {
		System.out.println(MessageType.valueOf("CONVERSATION_MESSAGE"));
		System.out.println(MessageType.getTargetClass("CONVERSATION_MESSAGE"));
	}
}
