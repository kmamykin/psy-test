package com.mamykin.psytest.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudyRun implements Serializable {

	private static final long serialVersionUID = -228547564447628663L;
	private String participant;
	private String group;
	private String startingCase;
	private List<StudySlide> slides;

	private int currentSlide;

	public StudyRun() {
		this("", "", "", new ArrayList<StudySlide>());
	}

	public StudyRun(String participant, String group, String startingCase, List<StudySlide> slides) {
		this.participant = participant;
		this.group = group;
		this.startingCase = startingCase;
		this.slides = slides;
		this.currentSlide = 0;

	}

	public StudyRunResults recordResults() {
		StudyRunResults results = new StudyRunResults();
		results.addValue("Participant", participant);
		results.addValue("Group", group);
		results.addValue("Starting Case", startingCase);
		for (StudySlide slide : slides) {
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
