package com.mamykin.psytest.client.model;

import java.util.List;

public class StudyGroup {

	private List<String> blockRefs;
	private String name;

	public StudyGroup(String name, List<String> blockRefs){
		this.name = name;
		this.blockRefs = blockRefs;
	}
	
	public String getName() {
		return this.name;
	}
	public List<String> getBlockRefs(){
		return blockRefs;
	}

}
