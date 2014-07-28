package com.tiger.be.core;

import java.util.concurrent.BlockingQueue;

import com.tiger.event.BaseEvent;

public interface TigerHandleInt<T extends BaseEvent> extends Runnable {
	
	boolean isValid(BaseEvent event);

	void handle(T event);
	
	void setQueue(BlockingQueue<BaseEvent> queue);
}
