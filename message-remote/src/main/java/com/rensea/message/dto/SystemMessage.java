/**
 * project : message-remote
 * user created : pippo
 * date created : 2009-12-15 - 下午03:59:02
 */
package com.rensea.message.dto;


/**
 * @since 2009-12-15
 * @author pippo
 */
public class SystemMessage extends MessageAdapter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7307107375900434866L;

	@Override
	public MessageType getMessageType() {
		return MessageType.SYSTEM;
	}

	private String text;

	private Boolean turnon;

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getTurnon() {
		return this.turnon;
	}

	public void setTurnon(Boolean turnon) {
		this.turnon = turnon;
	}

}
