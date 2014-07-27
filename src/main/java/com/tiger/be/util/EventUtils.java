package com.tiger.be.util;

import com.tiger.be.core.TigerModuleManager;
import com.tiger.be.event.BaseEvent;

public class EventUtils {

	public static void sendEvent(BaseEvent event){
		TigerModuleManager.getInstance().sendEvent(event);
	}
	
	public static void sendSynEvent(BaseEvent event){
		event.setSynEvent(true);
		TigerModuleManager.getInstance().sendEvent(event);
		event.startWaiting();
	}
}
