/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-12-24 - 上午11:14:20
 */
package com.rensea.message.server.tcp.connection;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.sirius.core.utils.ArrayUtils;

/**
 * @since 2009-12-24
 * @author pippo
 */
public class ConnectionMetaData {

	private final static String[] DEFAULT_MESSAGE_TYPE = { "SYSTEM", "SCORE_CHANGE", "COUNT_UPDATE", "MENTION", "CONVERSATION_MESSAGE", "UPDATE_CONVERSATION_COUNT", "CONVERSATION_DOOR", "STICK_STATUS", "CONVERSATION_READED_MESSAGE", "TAG_FOLLOWING_MESSAGE", "HASH_TAG", "TASK_NOTIFY_MESSAGE",
			"TASK_COMPLETE_MESSAGE" };

	public void subscribe_message(String messageType) {
		this.subscribed_message_types.add(messageType);
	}

	public void unsubscribe_message(String messageType) {
		this.subscribed_message_types.remove(messageType);
	}

	public void subscribe_hash_tag(String tag) {
		this.subscribed_hash_tags.add(tag);
	}

	public void unsubscribe_e_hash_tag(String tag) {
		this.subscribed_hash_tags.remove(tag);
	}

	public boolean is_subscribed_message(String messageType) {
		if (ArrayUtils.contains(DEFAULT_MESSAGE_TYPE, messageType)) {
			return true;
		}
		return this.subscribed_message_types.contains(messageType);
	}

	public boolean is_subscribed_tag(String tag) {
		return this.subscribed_hash_tags.contains(tag);
	}

	private Set<String> subscribed_message_types = new HashSet<String>();

	private Set<String> subscribed_hash_tags = new HashSet<String>();

	public Set<String> getSubscribed_message_types() {
		return this.subscribed_message_types;
	}

	public Set<String> getSubscribed_hash_tags() {
		return this.subscribed_hash_tags;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("subscribed_hash_tags",
				ArrayUtils.toString(this.subscribed_hash_tags.toArray())).append("subscribed_message_types",
				ArrayUtils.toString(this.subscribed_message_types.toArray())).toString();
	}

}
