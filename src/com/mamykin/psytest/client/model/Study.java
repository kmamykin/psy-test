package com.mamykin.psytest.client.model;

import java.util.ArrayList;
import java.util.List;

public class Study {
	String name = "";
	List<String> groups = new ArrayList<String>();
	List<String> cases = new ArrayList<String>();
	List<StudySlide> slides = new ArrayList<StudySlide>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void addGroup(String group) {
		this.groups.add(group);
	}

	public List<String> getCases() {
		return cases;
	}

	public void addCase(String aCase) {
		this.cases.add(aCase);
	}
	
	public List<StudySlide> getSlides(){
		return slides;
	}
	
	public void addSlide(StudySlide slide){
		slides.add(slide);
	}
	
	public StudyInfo getStudyInfo(){
		return new StudyInfo(getName(), getGroups(), getCases());
	}

	public StudyRun createRun(String groupName, String caseName,
			String participant) {
		return new StudyRun(participant, getSlides());
	}
}
