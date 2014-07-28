package com.tiger.event.config;

import com.tiger.event.BaseEvent;

public class ImageDownloadEvent extends BaseEvent {

	private static final long serialVersionUID = 1L;

	private String imageName;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}
