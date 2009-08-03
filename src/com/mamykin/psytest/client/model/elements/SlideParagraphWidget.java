package com.mamykin.psytest.client.model.elements;

import com.google.gwt.user.client.ui.Label;
import com.mamykin.psytest.client.model.SlideElementWidget;

public class SlideParagraphWidget extends SlideElementWidget {

	private final Label label = new Label();
	public SlideParagraphWidget(SlideParagraphElement paragraph) {
		super();
		label.setText(paragraph.getText());
		label.setWordWrap(true);
		initWidget(label);
	}

}
