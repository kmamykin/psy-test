package com.mamykin.psytest.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class StudySlide implements Serializable {

	private static final long serialVersionUID = 1950612878896366169L;

	private String name;
	private ArrayList<StudySlideElement> elements;
	private int timeLimitInSec;

	public StudySlide() {
		this.elements = new ArrayList<StudySlideElement>();
	}

	public String getName(){
		return name;
	}
	
	public void setName(String value){
		name = value;
	}
	
	public ArrayList<StudySlideElement> getElements() {
		return elements;
	}

	public StudySlide addElement(StudySlideElement element) {
		elements.add(element);
		return this;
	}

	public List<SlideElementWidget> createUIElements() {
		ArrayList<SlideElementWidget> widgets = new ArrayList<SlideElementWidget>();
		for (StudySlideElement element : elements) {
			widgets.add(element.createWidget());
		}
		return widgets;
	}

	public void recordResults(StudyResultLogger logger) {
		for(StudySlideElement element: elements){
			element.recordResult(logger);
		}
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

}
