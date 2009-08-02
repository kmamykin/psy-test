package com.mamykin.psytest.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public class StudySlide implements Serializable {

	private static final long serialVersionUID = 1950612878896366169L;

	private String name;
	private ArrayList<StudySlideElement> elements;

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

	public void setElements(ArrayList<StudySlideElement> elements) {
		this.elements = elements;
	}

	public StudySlide addElement(StudySlideElement element) {
		elements.add(element);
		return this;
	}

	public List<Widget> createUIElements() {
		List<Widget> widgets = new ArrayList<Widget>();
		for (StudySlideElement element : elements) {
			widgets.add(element.createUIElement());
		}
		return widgets;
	}

	public boolean allElementsValid() {
		boolean allElementsValid = true;
		for (StudySlideElement element : elements) {
			if (!element.isUIValid()) {
				allElementsValid = false;
				// don't break here, invoke isUIvalid for all elements
			}
		}
		return allElementsValid;
	}

	public void recordResults(StudyResultLogger logger) {
		for(StudySlideElement element: elements){
			element.recordResult(logger);
		}
	}

}
