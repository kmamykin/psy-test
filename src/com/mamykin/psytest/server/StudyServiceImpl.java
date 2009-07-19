package com.mamykin.psytest.server;

import java.util.ArrayList;

import com.mamykin.psytest.client.StudyService;
import com.mamykin.psytest.client.model.Study;
import com.mamykin.psytest.client.model.StudySlide;
import com.mamykin.psytest.client.model.elements.StudySlideParagraph;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class StudyServiceImpl extends RemoteServiceServlet implements
		StudyService {

	public Study getStudy() {
		Study newStudy = new Study();
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

	private final String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, "
			+ "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
			+ "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
			+ "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in "
			+ "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla "
			+ "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa "
			+ "qui officia deserunt mollit anim id est laborum.";

	public ArrayList<StudySlide> getStudyRunSlides(String groupName,
			String caseName) {
		ArrayList<StudySlide> slides = new ArrayList<StudySlide>();

		StudySlide slide1 = new StudySlide();
		slide1.addElement(new StudySlideParagraph(loremIpsum)).addElement(
				new StudySlideParagraph(loremIpsum)).addElement(
				new StudySlideParagraph(loremIpsum));
		slides.add(slide1);

		StudySlide slide2 = new StudySlide();
		slide2.addElement(new StudySlideParagraph(loremIpsum)).addElement(
				new StudySlideParagraph(loremIpsum)).addElement(
				new StudySlideParagraph(loremIpsum));
		slides.add(slide2);

		return slides;
	}
}
