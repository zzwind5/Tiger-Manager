package com.tiger.be.config;

import com.tiger.be.config.handle.ConfigDownloadHandle;
import com.tiger.be.config.handle.ImageDownloadHandle;
import com.tiger.be.core.AbstractTigerModule;

public class ConfigModuleImpl extends AbstractTigerModule implements ConfigModuleInt {
	
	public void registerHandles() {
		this.registerHandle(ImageDownloadHandle.class);
		this.registerHandle(ConfigDownloadHandle.class);
	}
	
}
