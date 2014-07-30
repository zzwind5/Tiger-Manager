package com.tiger.be.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tiger.be.config.ConfigModuleImpl;
import com.tiger.be.core.TigerModule.ModuleType;
import com.tiger.event.BaseEvent;

public class TigerModuleManager {
	
	private Map<ModuleType, TigerModule> moduleMaps = new ConcurrentHashMap<ModuleType, TigerModule>();
	
	private TigerModuleManager() {
		registerModule();
	}
	
	private void registerModule(){
		moduleMaps.put(ModuleType.config, new ConfigModuleImpl());
	}
	
	private static class TigerModuleManagerHolder {
		private static TigerModuleManager instance = new TigerModuleManager();
	}
	
	public static TigerModuleManager getInstance() {
		return TigerModuleManagerHolder.instance;
	}

	public void startAll() {
		for(TigerModule moduleIns : moduleMaps.values()) {
			moduleIns.start();
		}
	}
	
	public void stopAll() {
		for(TigerModule moduleIns : moduleMaps.values()) {
			moduleIns.stop();
		}
	}
	
	public void sendEvent(BaseEvent event) {
		if(event.getToMod() != null) {
			moduleMaps.get(event.getToMod()).addEvent(event);
		}else {
			for(TigerModule moduleIns : moduleMaps.values()) {
				moduleIns.addEvent(event);
			}
		}
	}
	
	
}
