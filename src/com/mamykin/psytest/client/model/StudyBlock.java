package com.mamykin.psytest.client.model;

import java.util.List;

public class StudyBlock {

	private List<StudyCase> cases;
	private String id;

	public StudyBlock(String id, List<StudyCase> cases) {
		this.id = id;
		this.cases = cases;
	}

	public String getId() {
		return id;
	}
	
	public List<StudyCase> getCases(){
		return cases;
	}

}

