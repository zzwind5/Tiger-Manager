package com.tiger.util;

import com.tiger.be.core.TigerModuleManager;
import com.tiger.event.BaseEvent;

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
