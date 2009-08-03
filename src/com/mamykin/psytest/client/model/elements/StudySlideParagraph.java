package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;

import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;
import com.mamykin.psytest.client.widgets.SlideElementWidget;
import com.mamykin.psytest.client.widgets.SlideParagraphWidget;

public class StudySlideParagraph implements StudySlideElement, Serializable {

	private static final long serialVersionUID = -2605107804906451484L;
	private String text;

	public StudySlideParagraph() {
		this("");
	}

	public StudySlideParagraph(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public SlideElementWidget createUIElement() {
		return new SlideParagraphWidget(this);
	}

	public void recordResult(StudyResultLogger logger) {
		// do nothing
	}

}
