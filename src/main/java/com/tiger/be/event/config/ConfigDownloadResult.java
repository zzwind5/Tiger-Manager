package com.tiger.be.event.config;

import com.tiger.be.event.EventResult;

public class ConfigDownloadResult extends EventResult {
	
	private static final long serialVersionUID = 1L;
	
	private long spendTime;

	public long getSpendTime() {
		return spendTime;
	}

	public void setSpendTime(long spendTime) {
		this.spendTime = spendTime;
	}

}
