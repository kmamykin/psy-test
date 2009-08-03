package com.mamykin.psytest.client.widgets;

import com.google.gwt.user.client.ui.Label;
import com.mamykin.psytest.client.model.elements.StudySlideParagraph;

public class SlideParagraphWidget extends SlideElementWidget {

	private final Label label = new Label();
	public SlideParagraphWidget(StudySlideParagraph paragraph) {
		super();
		label.setText(paragraph.getText());
		label.setWordWrap(true);
		initWidget(label);
	}

}
