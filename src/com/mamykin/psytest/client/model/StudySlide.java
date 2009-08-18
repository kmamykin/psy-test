package com.mamykin.psytest.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mamykin.psytest.client.model.elements.StudyPlaceholderElement;

public class StudySlide implements Serializable {

	private static final long serialVersionUID = 1950612878896366169L;

	private String name;
	private ArrayList<StudySlideElement> elements;
	private int timeLimitInSec;
	private String template;

	private String block;
	private String caseIndex;

	private long displayStartTime;

	private long displayEndTime;

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getCaseIndex() {
		return caseIndex;
	}

	public void setCaseIndex(String caseIndex) {
		this.caseIndex = caseIndex;
	}

	public StudySlide() {
		this.elements = new ArrayList<StudySlideElement>();
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		name = value;
	}

	public ArrayList<StudySlideElement> getElements() {
		return elements;
	}

	public StudySlide addElement(StudySlideElement element) {
		elements.add(element);
		return this;
	}

	public boolean isTimed() {
		return getTimeLimitInMillis() > 0;
	}

	public int getTimeLimitInSec() {
		return timeLimitInSec;
	}

	public void setTimeLimitInSec(int value) {
		this.timeLimitInSec = value;
	}

	public int getTimeLimitInMillis() {
		return timeLimitInSec * 1000;
	}

	public void setTemplate(String value) {
		this.template = value;
	}

	public String getTemplate() {
		return template;
	}

	public List<SlideElementWidget> createUIElements() {
		ArrayList<SlideElementWidget> widgets = new ArrayList<SlideElementWidget>();
		for (StudySlideElement element : elements) {
			widgets.add(element.createWidget());
		}
		return widgets;
	}

	public void recordResults(StudyResultLogger logger) {
		logger.addValue("Block", block);
		logger.addValue("Case", caseIndex);
		logger.addValue("Template", template);
		logger.addValue("Display Time", Long.toString(getDisplayDurationInSeconds()));
		for (StudySlideElement element : elements) {
			element.recordResult(logger);
		}
	}

	public StudySlide buildFrom(StudySlide slideTemplate) {
		StudySlide result = new StudySlide();
		result.setName(slideTemplate.getName());
		result.setTimeLimitInSec(slideTemplate.getTimeLimitInSec());
		int placeholderReplacementIndex = 0;
		for (StudySlideElement element : slideTemplate.getElements()) {
			if (element instanceof StudyPlaceholderElement) {
				result.addElement(getElements().get(placeholderReplacementIndex++));
			} else {
				result.addElement(element);
			}
		}
		return result;
	}

	public void setDisplayStartTime(long currentMillis) {
		this.displayStartTime = currentMillis;
	}

	public void setDisplayEndTime(long currentMillis) {
		this.displayEndTime = currentMillis;
	}

	private long getDisplayDurationInSeconds() {
		return (displayEndTime - displayStartTime) / 1000;
	}

}
