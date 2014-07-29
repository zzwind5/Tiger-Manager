package com.tiger.be.config;

import com.tiger.be.config.handle.ConfigDownloadHandler;
import com.tiger.be.config.handle.ImageDownloadHandler;
import com.tiger.be.core.AbstractTigerModule;

public class ConfigModuleImpl extends AbstractTigerModule implements ConfigModuleInt {
	
	public void registerHandles() {
		this.registerHandle(ImageDownloadHandler.class);
		this.registerHandle(ConfigDownloadHandler.class);
	}
	
}
