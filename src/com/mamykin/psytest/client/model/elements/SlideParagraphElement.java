package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;

import com.mamykin.psytest.client.model.SlideElementWidget;
import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;

public class SlideParagraphElement implements StudySlideElement, Serializable {

	private static final long serialVersionUID = -2605107804906451484L;
	private String text;
	private String alignment;
	private String style;

	public SlideParagraphElement() {
		this("", null, null);
	}

	public SlideParagraphElement(String text, String alignment, String style) {
		this.text = text;
		this.alignment = alignment;
		this.style = style;
	}

	public String getText() {
		return text;
	}

	public String getAlignment() {
		return alignment;
	}

	public String getStyle() {
		return style;
	}

	public SlideElementWidget createWidget() {
		return new SlideParagraphWidget(this);
	}

	public void recordResult(StudyResultLogger logger) {
		// do nothing
	}

	public StudySlideElement copyElement() {
		return new SlideParagraphElement(this.getText(), this.getAlignment(), this.getStyle());
	}

}
