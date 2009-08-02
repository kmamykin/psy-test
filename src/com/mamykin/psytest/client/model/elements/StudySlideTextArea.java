package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;

import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;

public class StudySlideTextArea extends StudySlideElement implements
		Serializable {

	private static final long serialVersionUID = -5954010399318894396L;
	private TextArea textArea;

	public StudySlideTextArea() {
		this("");
	}

	public StudySlideTextArea(String id) {
		super(id);
	}

	@Override
	public Widget createUIElement() {
		//this.textArea = new TextArea();
		return new TextArea();
	}

	@Override
	public boolean isUIValid() {
		return true; //(textArea.getText().trim().length() > 0);
	}

	@Override
	public void recordResult(StudyResultLogger logger) {
		logger.addValue(getId(), "text", ""); //textArea.getText());

	}

}
