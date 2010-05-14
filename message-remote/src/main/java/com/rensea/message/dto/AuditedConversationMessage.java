/**
 * project : message-remote
 * user created : pippo
 * date created : 2009-11-20 - 上午10:31:55
 */
package com.rensea.message.dto;

/**
 * @since 2009-11-20
 * @author pippo
 */
public class AuditedConversationMessage extends ConversationMessage {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5684153101782464083L;

	@Override
	public MessageType getMessageType() {
		return MessageType.AUDITED_CONVERSATION_MESSAGE;
	}

	private Boolean verify;

	public Boolean getVerify() {
		return this.verify;
	}

	public void setVerify(Boolean verify) {
		this.verify = verify;
	}

}
