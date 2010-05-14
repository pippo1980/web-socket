package com.rensea.message.spi.queue;


public class QueueConfiguration {

	private String serverId;
	private String[] queueServers;

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public void setQueueServers(String queueServers) {
		this.queueServers = queueServers.split(",");
	}

	public String getServerId() {
		return this.serverId;
	}

	public String[] getQueueServerList() {
		return this.queueServers;
	}
}

