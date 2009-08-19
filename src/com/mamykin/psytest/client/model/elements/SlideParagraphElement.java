package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;

import com.mamykin.psytest.client.model.SlideElementWidget;
import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;

public class SlideParagraphElement implements StudySlideElement, Serializable {

	private static final long serialVersionUID = -2605107804906451484L;
	private String text;
	private String alignment;

	public SlideParagraphElement() {
		this("", null);
	}

	public SlideParagraphElement(String text, String alignment) {
		this.text = text;
		this.alignment = alignment;
	}

	public String getText() {
		return text;
	}

	public String getAlignment() {
		return alignment;
	}

	public SlideElementWidget createWidget() {
		return new SlideParagraphWidget(this);
	}

	public void recordResult(StudyResultLogger logger) {
		// do nothing
	}

}
