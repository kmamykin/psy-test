package com.mamykin.psytest.client.model;

import java.util.List;

public class StudyCase {
	private String id;
	private List<StudySlide> slides;

	public StudyCase(String id, List<StudySlide> slides){
		this.id = id;
		this.slides = slides;
	}

	public String getId(){
		return id;
	}
	public List<StudySlide> getSlides() {
		return slides;
	}
}
