package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;

import com.mamykin.psytest.client.model.SlideElementWidget;
import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;

public class SlideTextAreaElement implements StudySlideElement, Serializable {

	private static final long serialVersionUID = -5954010399318894396L;
	private String id;
	private String text;
	private boolean optional;

	public SlideTextAreaElement() {
		this("", null);
	}

	public SlideTextAreaElement(String id, String optional) {
		this.id = id;
		this.optional = (optional == null ? false : Boolean.valueOf(optional));
	}

	public SlideElementWidget createWidget() {
		return new SlideTextAreaWidget(this);
	}

	public String getId() {
		return id;
	}

	public Boolean isOptional() {
		return optional;
	}

	public void recordResult(StudyResultLogger logger) {
		logger.addValue(getId(), text);
	}

	public void setText(String value) {
		this.text = value;
	}

	public String getText() {
		return text;
	}

}
