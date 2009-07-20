package com.mamykin.psytest.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.mamykin.psytest.client.StudyService;
import com.mamykin.psytest.client.model.Study;
import com.mamykin.psytest.client.model.StudyRun;
import com.mamykin.psytest.client.model.StudyRunResults;
import com.mamykin.psytest.client.model.StudySlide;
import com.mamykin.psytest.client.model.elements.StudySlideParagraph;
import com.mamykin.psytest.client.model.elements.StudySlideSingleChoice;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.appengine.api.mail.*;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class StudyServiceImpl extends RemoteServiceServlet implements
		StudyService {
	private static final Logger log = Logger.getLogger(StudyServiceImpl.class.getName());

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

	private final String defaultQuestion = "Blah blah blah?";
	
	public StudyRun getStudyRun(String groupName, String caseName,
			String participant) {
		ArrayList<StudySlide> slides = new ArrayList<StudySlide>();

		StudySlide slide1 = new StudySlide();
		slide1.addElement(new StudySlideParagraph("1", loremIpsum)).addElement(
				new StudySlideParagraph("2", loremIpsum)).addElement(
				new StudySlideSingleChoice("3", defaultQuestion, new String[] {
						"Option a", "Option b", "Option c" }, 2));
		slides.add(slide1);

		StudySlide slide2 = new StudySlide();
		slide2.addElement(new StudySlideParagraph("4", loremIpsum)).addElement(
				new StudySlideParagraph("5", loremIpsum)).addElement(
				new StudySlideParagraph("6", loremIpsum)).addElement(
				new StudySlideSingleChoice("7", defaultQuestion, new String[] {
						"Option 1", "Option 2", "Option 3" }, 1));
		slides.add(slide2);

		return new StudyRun(participant, slides);
	}

	public boolean recordRunResults(StudyRunResults results) {
		MailService mailService = MailServiceFactory.getMailService();
		MailService.Message email = new MailService.Message("kmamyk@gmail.com", "kmamyk@gmail.com", "Study results", results.formatAsEmailBody());
		try {
			log.info(email.getTextBody());
			mailService.send(email);
		} catch (IOException e) {
			throw new RuntimeException("Could not deliver email.", e);
		}
		return true;
	}

}
