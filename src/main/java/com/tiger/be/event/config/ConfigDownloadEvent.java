package com.tiger.be.event.config;

import com.tiger.be.event.BaseEvent;

public class ConfigDownloadEvent extends BaseEvent {

	private static final long serialVersionUID = 1L;
	
	private String clis;
	
	private long startTime;
	
	public ConfigDownloadEvent(){
		startTime = System.currentTimeMillis();
	}

	public String getClis() {
		return clis;
	}

	public void setClis(String clis) {
		this.clis = clis;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
}
