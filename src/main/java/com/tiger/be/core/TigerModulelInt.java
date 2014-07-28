package com.tiger.be.core;

import com.tiger.event.BaseEvent;

public interface TigerModulelInt {
	
//	void init();
	
	void registerHandles();
	
	void registerHandle(Class<?> handle);
	
	void registerHandle(Class<?> handle, int threadCount);
	
	void addEvent(BaseEvent event);
	
	void start();
}
