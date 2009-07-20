package com.mamykin.psytest.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudyRun implements Serializable {

	private static final long serialVersionUID = -228547564447628663L;
	private List<StudySlide> slides;
	private StudyRunResults results;
	private String participant;
	private int currentSlide;

	public StudyRun() {
		this("", new ArrayList<StudySlide>());
	}

	public StudyRun(String participant, List<StudySlide> slides) {
		this.participant = participant;
		this.slides = slides;
		this.currentSlide = 0;
		this.results = new StudyRunResults();
	}

	public StudyRunResults getResults() {
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

	public void currentSlideRecordResults() {
		getCurrentSlide().recordResults(results);
	}

	public boolean currentSlideValid() {
		return getCurrentSlide().isValid();
	}
}
