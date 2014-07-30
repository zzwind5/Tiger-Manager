package com.tiger.be.core;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import com.tiger.event.BaseEvent;
import com.tiger.util.TigerUtils;

public abstract class AbstractTigerModule implements TigerModule {

	private Map<Class<TigerEventHandler<BaseEvent>>, Integer> handleClass = new ConcurrentHashMap<Class<TigerEventHandler<BaseEvent>>, Integer>();

	private List<Entry<TigerEventHandler<BaseEvent>, BlockingQueue<BaseEvent>>> handleQueues = new ArrayList<Entry<TigerEventHandler<BaseEvent>, BlockingQueue<BaseEvent>>>();

	private List<Thread> handleThreads = new ArrayList<Thread>();
	
	private boolean blnModuleStart;

	public AbstractTigerModule() {
		this.registerHandles();
		init();
	}

	private void init() {
		for (Entry<Class<TigerEventHandler<BaseEvent>>, Integer> entry : handleClass.entrySet()) {
			Class<TigerEventHandler<BaseEvent>> handleClass = entry.getKey();
			int threadCounts = entry.getValue();
			TigerEventHandler<BaseEvent> instance = TigerUtils.createInstance(handleClass);
			if (instance == null || threadCounts < 1) {
				continue;
			}

			// init handleQueues
			BlockingQueue<BaseEvent> blockQueue = new LinkedBlockingDeque<BaseEvent>();
			handleQueues.add(new SimpleEntry<TigerEventHandler<BaseEvent>, BlockingQueue<BaseEvent>>(instance, blockQueue));

			// init handleThreads
			for (int i = 0; i < threadCounts; i++) {
				TigerEventHandler<BaseEvent> instanceRun = TigerUtils.createInstance(handleClass);
				instanceRun.setQueue(blockQueue);
				handleThreads.add(new Thread(instanceRun));
			}
		}
	}

	public void registerHandle(Class<?> handle) {
		registerHandle(handle, 1);
	}

	@SuppressWarnings("unchecked")
	public void registerHandle(Class<?> handle, int threadCount) {
		handleClass.put((Class<TigerEventHandler<BaseEvent>>) handle, threadCount);
	}

	public void start() {
		for (Thread thread : handleThreads) {
			if(!thread.isAlive()) {
				thread.start();
			}
		}
		blnModuleStart = true;
	}
	
	public void stop() {
		blnModuleStart = false;
		for(Entry<TigerEventHandler<BaseEvent>, BlockingQueue<BaseEvent>> entry : handleQueues) {
			entry.getValue().clear();
		}
	}

	public void addEvent(BaseEvent event) {
		if(!blnModuleStart) {
			return;
		}
		for (Entry<TigerEventHandler<BaseEvent>, BlockingQueue<BaseEvent>> entry : handleQueues) {
			if (entry.getKey().isValid(event)) {
				entry.getValue().offer(event);
				if (!event.isBroadcast()) {
					break;
				}
			}
		}
	}

}
