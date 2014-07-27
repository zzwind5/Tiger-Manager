package com.tiger.be.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tiger.be.config.ConfigModuleImpl;
import com.tiger.be.event.BaseEvent;

public class TigerModuleManager {

	private static TigerModuleManager instance = new TigerModuleManager();
	
	private Map<ModuleType, TigerModulelInt> moduleMaps = new ConcurrentHashMap<ModuleType, TigerModulelInt>();
			
	private TigerModuleManager() {
		init();
	}
	
	private void init(){
		moduleMaps.put(ModuleType.config, new ConfigModuleImpl());
	}
	
	public static TigerModuleManager getInstance() {
		return instance;
	}

	public void start() {
		for(TigerModulelInt moduleIns : moduleMaps.values()) {
			moduleIns.start();
		}
	}
	
	public void sendEvent(BaseEvent event) {
		if(event.getToMod() != null) {
			moduleMaps.get(event.getToMod()).addEvent(event);
		}else {
			for(TigerModulelInt moduleIns : moduleMaps.values()) {
				moduleIns.addEvent(event);
			}
		}
	}
	
	
}
