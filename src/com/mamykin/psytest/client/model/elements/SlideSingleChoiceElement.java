package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mamykin.psytest.client.model.SlideElementWidget;
import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;

public class SlideSingleChoiceElement implements StudySlideElement, Serializable {
	private static final long serialVersionUID = -1243708904233988308L;

	protected String id;
	protected String question;
	protected List<SlideChoiceAnswer> answers;
	private int userSelectedAnswerIndex;

	public SlideSingleChoiceElement() {
		this("", "", new ArrayList<SlideChoiceAnswer>());
	}

	public SlideSingleChoiceElement(String id, String question, List<SlideChoiceAnswer> answers) {
		this.id = id;
		this.question = question;
		this.answers = answers;
		this.userSelectedAnswerIndex = -1;
	}

	public String getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public List<SlideChoiceAnswer> getAnswers() {
		return answers;
	}

	public int getCorrectAnswerIndex() {
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).isCorrect())
				return i;
		}
		return -1;
	}

	public int getUserSelectedAnswerIndex() {
		return userSelectedAnswerIndex;
	}

	public void setUserSelectedAnswerIndex(int value) {
		this.userSelectedAnswerIndex = value;
	}

	public SlideElementWidget createWidget() {
		return new SlideSingleChoiceWidget(this);
	}

	public void recordResult(StudyResultLogger logger) {
		logger.addValue(getId(), correctAnswerSelected());
	}

	public StudySlideElement copyElement() {
		return new SlideSingleChoiceElement(this.getId(), this.getQuestion(), this.getAnswers());
	}

	private String correctAnswerSelected() {
		return getCorrectAnswerIndex() == getUserSelectedAnswerIndex() ? "Correct" : "Incorrect";
	}

}
