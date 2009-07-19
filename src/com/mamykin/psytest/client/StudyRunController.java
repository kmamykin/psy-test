package com.mamykin.psytest.client;

import java.util.Collection;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mamykin.psytest.client.model.Study;

public class StudyRunController {

	interface StartView {
		void setGroupsList(Collection<String> groups);

		String getSelectedGroup();

		HasClickHandlers getStartButton();

		void setVisible(Boolean visible);
	}

	interface RunView {
		void setContent(long content);

		HasClickHandlers getContinueButton();

		void setVisible(Boolean visible);
	}

	interface FinishView {
		HasClickHandlers getFinishButton();

		void setVisible(Boolean visible);
	}

	interface ErrorsNotification {
		void displayError(String message);
	}

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
//	private static final String SERVER_ERROR = "An error occurred while "
//			+ "attempting to contact the server. Please check your network "
//			+ "connection and try again.";
	private StartView startView;
	private RunView runView;
	private FinishView finishView;
	private int slideCount;
	private StudyServiceAsync studyService;
	private ErrorsNotification errorsNotification;

	public StudyRunController(StartView startView, RunView runView,
			FinishView finishView, ErrorsNotification errorsNotification, StudyServiceAsync studyService) {
		this.startView = startView;
		this.runView = runView;
		this.finishView = finishView;
		this.errorsNotification = errorsNotification;
		this.studyService = studyService;
		wireEvents();
	}

	public void start() {
		startView.setVisible(true);
		runView.setVisible(false);
		finishView.setVisible(false);
		getGroupList();
	}

	private void getGroupList() {
		studyService.retrieveStudy( new AsyncCallback<Study>() {

			public void onFailure(Throwable caught) {
				errorsNotification.displayError(caught.getMessage());
			}

			public void onSuccess(Study result) {
				processGroupsResults(result);
			}
		});
	}
	
	private void processGroupsResults(Study study){
		startView.setGroupsList(study.getGroups());
	}

	private void wireEvents() {
		startView.getStartButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				startRun();
			}
		});

		runView.getContinueButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				nextSlide();
			}

		});

		finishView.getFinishButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				finishRun();
			}

		});
	}

	private void startRun() {
		startView.setVisible(false);
		runView.setVisible(true);
	}

	protected void nextSlide() {
		slideCount++;
		if (slideCount >= 5) {
			slideCount = 0;
			runView.setVisible(false);
			finishView.setVisible(true);
		} else {
			runView.setContent(System.currentTimeMillis());
		}
	}

	private void finishRun() {
		finishView.setVisible(false);
		startView.setVisible(true);
	}

}
