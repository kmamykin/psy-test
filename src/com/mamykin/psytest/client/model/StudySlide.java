package com.mamykin.psytest.client.model;

import java.io.Serializable;

public class StudySlide implements Serializable{

	private static final long serialVersionUID = 1950612878896366169L;

	private String[] elements;
	
	public StudySlide() {
		this.elements = new String[]{};
	}
	public StudySlide(String... elements) {
		this.elements = elements;
	}

	public String[] getElements() {
		return elements;
	}

	public void setElements(String[] elements) {
		this.elements = elements;
	}

}
