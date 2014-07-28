package com.tiger.be.config.handle;

import com.tiger.be.core.AbstractTigerHandle;
import com.tiger.event.config.ImageDownloadEvent;

public class ImageDownloadHandle extends AbstractTigerHandle<ImageDownloadEvent> {
	
	public void handle(ImageDownloadEvent event) {
		System.out.println(Thread.currentThread().getName() + " Receive Event: " + event.getClass().getSimpleName());
		System.out.println(event.getImageName());
	}


}
