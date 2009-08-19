package com.mamykin.psytest.client.model.elements;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.mamykin.psytest.client.model.SlideElementWidget;

public class SlideParagraphWidget extends SlideElementWidget {

	private final Label label = new Label();

	public SlideParagraphWidget(SlideParagraphElement paragraph) {
		super();
		label.setText(paragraph.getText());
		label.setWordWrap(true);
		label.setHorizontalAlignment(convertAlignment(paragraph.getAlignment()));
		initWidget(label);
	}

	private HorizontalAlignmentConstant convertAlignment(String alignment) {
		// application default alignment is center
		if (alignment == null || alignment.length() == 0)
			return HasHorizontalAlignment.ALIGN_CENTER;
		if (alignment.equalsIgnoreCase("left"))
			return HasHorizontalAlignment.ALIGN_LEFT;
		if (alignment.equalsIgnoreCase("right"))
			return HasHorizontalAlignment.ALIGN_RIGHT;
		if (alignment.equalsIgnoreCase("center"))
			return HasHorizontalAlignment.ALIGN_CENTER;
		return HasHorizontalAlignment.ALIGN_DEFAULT;
	}

}
