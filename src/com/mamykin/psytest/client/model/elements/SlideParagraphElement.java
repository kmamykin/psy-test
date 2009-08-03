package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;

import com.mamykin.psytest.client.model.SlideElementWidget;
import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;

public class SlideParagraphElement implements StudySlideElement, Serializable {

	private static final long serialVersionUID = -2605107804906451484L;
	private String text;

	public SlideParagraphElement() {
		this("");
	}

	public SlideParagraphElement(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public SlideElementWidget createWidget() {
		return new SlideParagraphWidget(this);
	}

	public void recordResult(StudyResultLogger logger) {
		// do nothing
	}

}
