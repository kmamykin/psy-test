package com.mamykin.psytest.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudyRun implements Serializable {

	private static final long serialVersionUID = -228547564447628663L;
	private List<StudySlide> slides;
	private String participant;
	private int currentSlide;

	public StudyRun() {
		this("", new ArrayList<StudySlide>());
	}

	public StudyRun(String participant, List<StudySlide> slides) {
		this.participant = participant;
		this.slides = slides;
		this.currentSlide = 0;
		
	}

	public StudyRunResults getResults() {
		StudyRunResults results = new StudyRunResults();
		for(StudySlide slide : slides){
			slide.recordResults(results);
		}
		return results;
	}

	public String getParticipant() {
		return participant;
	}

	public void moveToNextSlide() {
		currentSlide++;
	}

	public StudySlide getCurrentSlide() {
		return slides.get(currentSlide);
	}

	public boolean hasMoreSlides() {
		return currentSlide < (slides.size() - 1);
	}
}
