package com.tiger.be.core;

import com.tiger.event.BaseEvent;

public interface TigerModule {
	
	enum ModuleType {
		config, log, report, capwap, schedule, monitor, alarm
	}
	
	void registerHandles();
	
	void registerHandle(Class<?> handle);
	
	void registerHandle(Class<?> handle, int threadCount);
	
	void addEvent(BaseEvent event);
	
	void start();
	
	void stop();
}
