package com.mamykin.psytest.server;

import java.util.ArrayList;

import com.mamykin.psytest.client.StudyService;
import com.mamykin.psytest.client.model.Study;
import com.mamykin.psytest.client.model.StudySlide;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class StudyServiceImpl extends RemoteServiceServlet implements
		StudyService {

	public Study getStudy() {
		Study newStudy  = new Study();
		newStudy.setName("Some Study");
		ArrayList<String> groups = new ArrayList<String>();
		groups.add("Group 1");
		groups.add("Group 2");
		groups.add("Group 3");
		groups.add("Group 4");
		newStudy.setGroups(groups);
		
		ArrayList<String> cases = new ArrayList<String>();
		cases.add("Case1");
		cases.add("Case2");
		cases.add("Case3");
		cases.add("Case4");
		newStudy.setCases(cases);
		return newStudy;
	}

	public ArrayList<StudySlide> getStudyRunSlides(String groupName,
			String caseName) {
		ArrayList<StudySlide> slides = new ArrayList<StudySlide>();
		StudySlide slide1 = new StudySlide("element1", "element2");
		slides.add(slide1);
		StudySlide slide2 = new StudySlide("element3", "element4");
		slides.add(slide2);
		
		return slides;
	}
}
