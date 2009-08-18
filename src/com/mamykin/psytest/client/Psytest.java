package com.mamykin.psytest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Psytest implements EntryPoint, StudyRunController.ErrorsNotification, StudyRunController.ProgressNotification {

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final StudyServiceAsync studyService = GWT.create(StudyService.class);

	private final StudyStartPanel startTestPanel = new StudyStartPanel();
	private final StudyRunPanel studyRunPanel = new StudyRunPanel();
	private final StudyFinishPanel studyFinishPanel = new StudyFinishPanel();
	private final StudyLoadingPanel loadingPanel = new StudyLoadingPanel();

	private StudyRunController controller;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel mainRootPanel = RootPanel.get("main");
		mainRootPanel.add(startTestPanel);
		mainRootPanel.add(studyRunPanel);
		mainRootPanel.add(studyFinishPanel);
		this.controller = new StudyRunController(startTestPanel, studyRunPanel, studyFinishPanel, this, this, studyService);
		this.controller.start();
	}

	public void displayError(String message) {
		ErrorDialogBox errorDialogBox = new ErrorDialogBox();
		errorDialogBox.displayError(message);
	}

	public void start() {
		loadingPanel.show();
	}

	public void stop() {
		loadingPanel.hide();
	}
}
