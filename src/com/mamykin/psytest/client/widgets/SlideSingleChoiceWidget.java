package com.mamykin.psytest.client.widgets;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mamykin.psytest.client.model.elements.StudySlideSingleChoice;

public class SlideSingleChoiceWidget extends SlideElementWidget {

	private final StudySlideSingleChoice element;
	private final VerticalPanel panel = new VerticalPanel();
	private final List<RadioButton> radioButtons;

	public SlideSingleChoiceWidget(StudySlideSingleChoice element) {
		super();
		this.element = element;
		this.radioButtons = new ArrayList<RadioButton>();
		panel.add(new Label(element.getQuestion()));
		for (String answer : element.getAnswers()) {
			RadioButton radioButton = new RadioButton(element.getId(), answer);
			radioButtons.add(radioButton);
			panel.add(radioButton);
		}
		initWidget(panel);
	}

	@Override
	public boolean isValid() {
		return getCurrentSelectionIndex() != -1;
	}

	@Override
	public void recordValues() {
		element.setUserSelectedAnswerIndex(getCurrentSelectionIndex());
	}

	private int getCurrentSelectionIndex() {
		for (int i = 0; i < radioButtons.size(); i++) {
			RadioButton rb = radioButtons.get(i);
			if (rb.getValue() == true) {
				// something is selected
				return i;
			}
		}
		// nothing is selected
		return -1;
	}

}
