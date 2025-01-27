package com.elorBase.server.elorBaseServer.socketIO.model;

public class AbstractMessage {
	private String message = null;

	public AbstractMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
