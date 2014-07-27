package com.tiger.be.event;

import java.io.Serializable;

import com.tiger.be.core.ModuleType;

public class BaseEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected boolean broadcast = false;

	private ModuleType fromMod;
	
	private ModuleType toMod;
	
	private boolean synEvent;
	
	private EventResult result;

	public ModuleType getFromMod() {
		return fromMod;
	}

	public void setFromMod(ModuleType fromMod) {
		this.fromMod = fromMod;
	}

	public ModuleType getToMod() {
		return toMod;
	}

	public void setToMod(ModuleType toMod) {
		this.toMod = toMod;
	}

	public boolean isBroadcast() {
		return broadcast;
	}

	public void setBroadcast(boolean broadcast) {
		this.broadcast = broadcast;
	}

	public boolean isSynEvent() {
		return synEvent;
	}

	public void setSynEvent(boolean synEvent) {
		this.synEvent = synEvent;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends EventResult> T getResult() {
		return (T)result;
	}

	public <T extends EventResult> void setResult(T result) {
		this.result = result;
	}

	public synchronized void startWaiting(){
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void endWaiting(){
		this.notifyAll();
	}
}
