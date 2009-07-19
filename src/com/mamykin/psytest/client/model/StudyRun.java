package com.mamykin.psytest.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudyRun implements Serializable {

	private static final long serialVersionUID = -228547564447628663L;
	private List<StudySlide> slides;
	private String participant;
	private int nextSlide;

	public StudyRun() {
		this("", new ArrayList<StudySlide>());
	}

	public StudyRun(String participant, List<StudySlide> slides) {
		this.participant = participant;
		this.slides = slides;
		this.nextSlide = 0;
	}
	
	public StudySlide getNextSlide(){
		return slides.get(nextSlide++);
	}
	
	public boolean hasMoreSlides(){
		return nextSlide < slides.size();
	}
}
