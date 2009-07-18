package com.mamykin.psytest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Psytest implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	private final StudyStartPanel startTestPanel = new StudyStartPanel();
	private final StudyRunPanel studyRunPanel = new StudyRunPanel();
	private final StudyFinishPanel studyFinishPanel = new StudyFinishPanel();
	private StudyRunController controller;
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel mainRootPanel = RootPanel.get("main");
		mainRootPanel.add(startTestPanel);
		mainRootPanel.add(studyRunPanel);
		mainRootPanel.add(studyFinishPanel);
		this.controller = new StudyRunController(startTestPanel, studyRunPanel, studyFinishPanel);
	}
}
