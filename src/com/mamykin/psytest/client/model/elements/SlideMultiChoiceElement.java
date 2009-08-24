package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;
import java.util.List;

import com.mamykin.psytest.client.model.SlideElementWidget;
import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;

public class SlideMultiChoiceElement implements StudySlideElement, Serializable {

	private static final long serialVersionUID = -6864204896525737282L;
	private List<String> userSelectedChoices;
	private String id;
	private String question;
	private List<String> choices;
	private String text;

	public SlideMultiChoiceElement() {
		this("", "", null);
	}

	public SlideMultiChoiceElement(String id, String question, List<String> choices) {
		this.id = id;
		this.question = question;
		this.choices = choices;
	}

	public SlideElementWidget createWidget() {
		return new SlideMultiChoiceWidget(this);
	}

	public void recordResult(StudyResultLogger logger) {
		logger.addValue(getId() + "Tags", formatAsString(getUserSelectedChoices()));
		logger.addValue(getId() + "Text", getText());
	}

	private String formatAsString(List<String> list) {
		if (list.size() == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (String element : list) {
			sb.append(element).append(";");
		}
		return sb.deleteCharAt(sb.length() - 1).toString();
	}

	public String getQuestion() {
		return question;
	}

	public List<String> getChoices() {
		return choices;
	}

	public String getId() {
		return id;
	}

	public void setUserSelectedChoices(List<String> selectedChoices) {
		userSelectedChoices = selectedChoices;
	}

	public List<String> getUserSelectedChoices() {
		return userSelectedChoices;
	}

	public void setText(String value) {
		text = value;
	}

	public String getText() {
		return text;
	}

}
