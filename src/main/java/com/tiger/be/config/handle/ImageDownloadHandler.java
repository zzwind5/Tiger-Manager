package com.tiger.be.config.handle;

import com.tiger.be.core.AbstractTigerEventHandler;
import com.tiger.event.config.ImageDownloadEvent;

public class ImageDownloadHandler extends AbstractTigerEventHandler<ImageDownloadEvent> {
	
	public void handle(ImageDownloadEvent event) {
		System.out.println(Thread.currentThread().getName() + " Receive Event: " + event.getClass().getSimpleName());
		System.out.println(event.getImageName());
	}


}
