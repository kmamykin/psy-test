package com.mamykin.psytest.client.model;

import java.io.Serializable;
import java.util.List;

public class StudyInfo implements Serializable {

	private static final long serialVersionUID = -7328100894878570479L;
	
	String name = "";
	List<String> groups;
	List<String> cases;

	public StudyInfo() {
		this(null, null, null); // just for serialization
	}

	public StudyInfo(String name, List<String> groups, List<String> cases) {
		this.name = name;
		this.groups = groups;
		this.cases = cases;
	}

	public String getName() {
		return name;
	}

	public List<String> getGroups() {
		return groups;
	}

	public List<String> getCases() {
		return cases;
	}
}
