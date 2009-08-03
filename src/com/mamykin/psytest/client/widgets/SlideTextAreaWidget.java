package com.mamykin.psytest.client.widgets;

import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mamykin.psytest.client.model.elements.StudySlideTextArea;

public class SlideTextAreaWidget extends SlideElementWidget {

	private final VerticalPanel panel = new VerticalPanel();
	private final TextArea textArea = new TextArea();
	private final StudySlideTextArea element;

	public SlideTextAreaWidget(StudySlideTextArea element) {
		super();
		this.element = element;
		panel.add(textArea);
		initWidget(panel);
	}

	@Override
	public boolean isValid() {
		return (textArea.getText().trim().length() > 0);
	}
	
	@Override
	public void recordValues() {
		element.setText(textArea.getText());
	}
}
