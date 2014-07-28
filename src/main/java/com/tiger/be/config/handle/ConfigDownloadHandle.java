package com.tiger.be.config.handle;

import com.tiger.be.core.AbstractTigerHandle;
import com.tiger.event.config.ConfigDownloadEvent;
import com.tiger.event.config.ConfigDownloadResult;

public class ConfigDownloadHandle extends AbstractTigerHandle<ConfigDownloadEvent> {
	
	public void handle(ConfigDownloadEvent event) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " Receive Event: " + event.getClass().getSimpleName());
		System.out.println(event.getClis());
		ConfigDownloadResult result = new ConfigDownloadResult();
		result.setSpendTime(System.currentTimeMillis() - event.getStartTime());
		event.setResult(result);
	}

}
