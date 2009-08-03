package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;

import com.mamykin.psytest.client.model.SlideElementWidget;
import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;

public class SlideImageElement implements StudySlideElement, Serializable {

	private static final long serialVersionUID = -327405498439968945L;
	private String id;
	private String imageRelativeUrl;

	public SlideImageElement() {
		this("", "");
	}

	public SlideImageElement(String id, String imageRelativeUrl) {
		this.id = id;
		this.imageRelativeUrl = imageRelativeUrl;
	}

	public SlideElementWidget createWidget() {
		return new SlideImageWidget(this);
	}

	public void recordResult(StudyResultLogger logger) {
		// do nothing
	}

	public String getUrl() {
		return imageRelativeUrl;
	}

	public String getId() {
		return id;
	}
}
