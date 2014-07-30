package com.tiger.be.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;

import com.tiger.event.BaseEvent;

public abstract class AbstractTigerEventHandler<T extends BaseEvent> implements TigerEventHandler<T> {
	
	private BlockingQueue<BaseEvent> queue;
	
	protected Class<?> tClass;
	
	public AbstractTigerEventHandler() {
		Type genType = this.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		tClass = (Class<?>)params[0];
	}

	@SuppressWarnings("unchecked")
	public void run() {
		while(true) {
			BaseEvent event = null;
			try {
				event = queue.take();
				handle((T)event);
			} catch (Throwable t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			} finally {
				if(event != null && event.isSynEvent()) {
					event.endWaiting();
				}
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
