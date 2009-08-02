package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;

public class StudySlideSingleChoice extends StudySlideElement implements
		Serializable {

	private static final long serialVersionUID = -1243708904233988308L;

	private String question;
	private List<String> answers;
	private int correctAnswerIndex;

	private List<RadioButton> radioButtons;

	public StudySlideSingleChoice() {
		this("", "", new ArrayList<String>(), 0);
	}

	public StudySlideSingleChoice(String id, String question,
			List<String> answers, int correctAnswerIndex) {
		super(id);
		this.question = question;
		this.answers = answers;
		this.correctAnswerIndex = correctAnswerIndex;
		this.radioButtons = new ArrayList<RadioButton>();
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void addAnswer(String value) {
		this.answers.add(value);
	}

	public int getCorrectAnswerIndex() {
		return correctAnswerIndex;
	}

	public void setCorrectAnswerIndex(int correctAnswerIndex) {
		this.correctAnswerIndex = correctAnswerIndex;
	}

	@Override
	public Widget createUIElement() {
		VerticalPanel panel = new VerticalPanel();
		panel.add(new Label(question));
		for (String answer : answers) {
			RadioButton radioButton = new RadioButton(getId(), answer);
			radioButtons.add(radioButton);
			panel.add(radioButton);
		}
		return panel;
	}

	@Override
	public boolean isUIValid() {
		return getCurrentSelectionIndex() != -1;
	}

	@Override
	public void recordResult(StudyResultLogger logger) {
		logger.addValue(getId(), "user_selection", Integer
				.toString(getCurrentSelectionIndex()));
		logger.addValue(getId(), "correct_answer", Integer
				.toString(correctAnswerIndex));
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
