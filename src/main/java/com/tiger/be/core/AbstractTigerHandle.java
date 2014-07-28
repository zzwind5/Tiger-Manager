package com.tiger.be.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;

import com.tiger.event.BaseEvent;

public abstract class AbstractTigerHandle<T extends BaseEvent> implements TigerHandleInt<T> {
	
	private BlockingQueue<BaseEvent> queue;
	
	protected Class<?> tClass;
	
	public AbstractTigerHandle() {
		Type genType = this.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		tClass = (Class<?>)params[0];
	}

	@SuppressWarnings("unchecked")
	public void run() {
		while(true) {
			try {
				BaseEvent event = queue.take();
				handle((T)event);
				if(event.isSynEvent()) {
					event.endWaiting();
				}
			} catch (Throwable t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}
	}
	
	public boolean isValid(BaseEvent event) {
		return event.getClass() == tClass;
	}

	public void setQueue(BlockingQueue<BaseEvent> queue) {
		this.queue = queue;
	}
	
}
