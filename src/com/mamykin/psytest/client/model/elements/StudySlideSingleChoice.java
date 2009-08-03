package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;
import com.mamykin.psytest.client.widgets.SlideElementWidget;
import com.mamykin.psytest.client.widgets.SlideSingleChoiceWidget;

public class StudySlideSingleChoice implements StudySlideElement, Serializable {

	private static final long serialVersionUID = -1243708904233988308L;

	private String id;
	private String question;
	private List<String> answers;
	private int correctAnswerIndex;
	private int userSelectedAnswerIndex;

	public StudySlideSingleChoice() {
		this("", "", new ArrayList<String>(), 0);
	}

	public StudySlideSingleChoice(String id, String question,
			List<String> answers, int correctAnswerIndex) {
		this.id = id;
		this.question = question;
		this.answers = answers;
		this.correctAnswerIndex = correctAnswerIndex;
	}

	public String getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public int getCorrectAnswerIndex() {
		return correctAnswerIndex;
	}

	public int getUserSelectedAnswerIndex() {
		return userSelectedAnswerIndex;
	}

	public void setUserSelectedAnswerIndex(int value) {
		this.userSelectedAnswerIndex = value;
	}

	public SlideElementWidget createUIElement() {
		return new SlideSingleChoiceWidget(this);
	}

	public void recordResult(StudyResultLogger logger) {
		logger.addValue(getId(), "user_selection", Integer
				.toString(getUserSelectedAnswerIndex()));
		logger.addValue(getId(), "correct_answer", Integer
				.toString(getCorrectAnswerIndex()));
	}

}
