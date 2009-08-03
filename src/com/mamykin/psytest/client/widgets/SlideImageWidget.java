package com.mamykin.psytest.client.widgets;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mamykin.psytest.client.model.elements.StudySlideImage;

public class SlideImageWidget extends SlideElementWidget {

	private VerticalPanel panel = new VerticalPanel();
	private Image image = new Image();
	
	public SlideImageWidget(StudySlideImage imageElement) {
		super();
		image.setUrl(imageElement.getUrl());
		panel.add(image);
		initWidget(panel);
	}
}
