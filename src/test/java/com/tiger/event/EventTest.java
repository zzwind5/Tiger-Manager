package com.tiger.event;

import com.tiger.be.core.TigerModuleManager;
import com.tiger.event.config.ConfigDownloadEvent;
import com.tiger.event.config.ConfigDownloadResult;
import com.tiger.event.config.ImageDownloadEvent;
import com.tiger.util.EventUtils;

public class EventTest {

	public static void main(String... args) throws InterruptedException{
		TigerModuleManager.getInstance().start();
		
		Thread.sleep(1*1000);
		
		ConfigDownloadEvent cfgEvent = new ConfigDownloadEvent();
		cfgEvent.setClis("interface wifi0 ssid zhang_5g");
		
		ImageDownloadEvent imgEvent = new ImageDownloadEvent();
		imgEvent.setImageName("testImage_20140727.img");
		
		EventUtils.sendEvent(imgEvent);
		
		System.out.println("Send synchronized event, waiting...");
		EventUtils.sendSynEvent(cfgEvent);
		ConfigDownloadResult result = cfgEvent.getResult();
		System.out.println("Spend Times: " + result.getSpendTime());
		System.out.println("End!");
	}
}
