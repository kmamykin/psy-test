package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;

import com.mamykin.psytest.client.model.SlideElementWidget;
import com.mamykin.psytest.client.model.StudyResultLogger;
import com.mamykin.psytest.client.model.StudySlideElement;

public class StudyPlaceholderElement implements StudySlideElement, Serializable {
	private static final long serialVersionUID = -5232787288721037717L;

	public StudyPlaceholderElement(){
		
	}
	public SlideElementWidget createWidget() {
		throw new UnsupportedOperationException("This placeholder should  have been replaced with a real widget.");
	}

	public void recordResult(StudyResultLogger logger) {
		// do nothin, as the placeholder will be replaced with a real element
		
	}

}
