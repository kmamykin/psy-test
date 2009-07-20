package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;

public class StudySlideParagraph extends StudySlideElement implements
		Serializable {

	private static final long serialVersionUID = -2605107804906451484L;
	private String text;

	public StudySlideParagraph() {
		this("", "");
	}

	public StudySlideParagraph(String id, String text) {
		super(id);
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Widget createUIElement() {
		return new Label(text, true);
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
