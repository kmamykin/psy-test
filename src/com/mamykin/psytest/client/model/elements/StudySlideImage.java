package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;

public class StudySlideImage extends StudySlideElement implements Serializable {

	private static final long serialVersionUID = -327405498439968945L;
	private String imageRelativeUrl;

	public StudySlideImage() {
		this("", "");
	}

	public StudySlideImage(String id, String imageRelativeUrl) {
		super(id);
		this.imageRelativeUrl = imageRelativeUrl;
	}

	@Override
	public Widget createUIElement() {
		Image image = new Image();
		image.setUrl(imageRelativeUrl);
		return image;
	}

	@Override
	public boolean isUIValid() {
		return true;
	}

	@Override
	public void recordResult(StudyResultLogger logger) {
		// do nothing
	}

}
