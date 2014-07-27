package com.tiger.be.core;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import com.tiger.be.event.BaseEvent;
import com.tiger.be.util.TigerUtils;

public abstract class AbstractTigerModule implements TigerModulelInt {

	private Map<Class<TigerHandleInt<BaseEvent>>, Integer> handleClass = new ConcurrentHashMap<Class<TigerHandleInt<BaseEvent>>, Integer>();

	private List<Entry<TigerHandleInt<BaseEvent>, BlockingQueue<BaseEvent>>> handleQueues = new ArrayList<Entry<TigerHandleInt<BaseEvent>, BlockingQueue<BaseEvent>>>();

	private List<Thread> handleThreads = new ArrayList<Thread>();

	public AbstractTigerModule() {
		this.registerHandles();
		init();
	}

	private void init() {
		for (Entry<Class<TigerHandleInt<BaseEvent>>, Integer> entry : handleClass.entrySet()) {
			Class<TigerHandleInt<BaseEvent>> handleClass = entry.getKey();
			int threadCounts = entry.getValue();
			TigerHandleInt<BaseEvent> instance = TigerUtils.createInstance(handleClass);
			if (instance == null || threadCounts < 1) {
				continue;
			}

			// init handleQueues
			BlockingQueue<BaseEvent> blockQueue = new LinkedBlockingDeque<BaseEvent>();
			handleQueues.add(new SimpleEntry<TigerHandleInt<BaseEvent>, BlockingQueue<BaseEvent>>(instance, blockQueue));

			// init handleThreads
			for (int i = 0; i < threadCounts; i++) {
				TigerHandleInt<BaseEvent> instanceRun = TigerUtils
						.createInstance(handleClass);
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
		handleClass.put((Class<TigerHandleInt<BaseEvent>>) handle, threadCount);
	}

	public void start() {
		for (Thread thread : handleThreads) {
			thread.start();
		}
	}

	public void addEvent(BaseEvent event) {
		for (Entry<TigerHandleInt<BaseEvent>, BlockingQueue<BaseEvent>> entry : handleQueues) {
			if (entry.getKey().isValid(event)) {
				entry.getValue().offer(event);
				if (!event.isBroadcast()) {
					break;
				}
			}
		}
	}

}
