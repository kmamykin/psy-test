package com.mamykin.psytest.client.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Study implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5837029246857886526L;
	String name = "";
	ArrayList<String> groups = new ArrayList<String>();
	ArrayList<String> cases = new ArrayList<String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<String> groups) {
		this.groups = groups;
	}

	public ArrayList<String> getCases() {
		return cases;
	}

	public void setCases(ArrayList<String> cases) {
		this.cases = cases;
	}

}
