package com.mamykin.psytest.client;

import java.util.Collection;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.mamykin.psytest.client.model.Study;
import com.mamykin.psytest.client.model.StudyRun;

public class StudyRunController {

	interface StartView {
		HasText getStudyName();

		void setGroupsList(Collection<String> groups);

		String getSelectedGroup();

		void setCasesList(Collection<String> cases);

		String getSelectedCase();

		HasValue<String> getParticipantName();

		HasClickHandlers getStartButton();

		void setVisible(Boolean visible);
	}

	interface RunView {
		void setContent(List<Widget> elements);

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
	// private static final String SERVER_ERROR = "An error occurred while "
	// + "attempting to contact the server. Please check your network "
	// + "connection and try again.";
	private StartView startView;
	private RunView runView;
	private FinishView finishView;
	private StudyServiceAsync studyService;
	private ErrorsNotification errorsNotification;
	private StudyRun currentRun;

	public StudyRunController(StartView startView, RunView runView,
			FinishView finishView, ErrorsNotification errorsNotification,
			StudyServiceAsync studyService) {
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
		retrieveStudy();
	}

	private void retrieveStudy() {
		studyService.getStudy(new AsyncCallback<Study>() {

			public void onFailure(Throwable caught) {
				errorsNotification.displayError(caught.getMessage());
			}

			public void onSuccess(Study result) {
				displayStudy(result);
			}
		});
	}

	private void displayStudy(Study study) {
		startView.getStudyName().setText(study.getName());
		startView.setGroupsList(study.getGroups());
		startView.setCasesList(study.getCases());
		startView.getParticipantName().setValue("");
	}

	private void wireEvents() {
		startView.getStartButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				startButtonPressed();
			}
		});

		runView.getContinueButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				nextSlideButtonPressed();
			}

		});

		finishView.getFinishButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				finishButtonPressed();
			}

		});
	}

	private void startButtonPressed() {
		retrieveStudyRunSlides();
		startView.setVisible(false);
		runView.setVisible(true);
	}

	private void retrieveStudyRunSlides() {
		studyService.getStudyRun(startView.getSelectedGroup(), startView
				.getSelectedCase(), startView.getParticipantName().getValue(),
				new AsyncCallback<StudyRun>() {

					public void onFailure(Throwable caught) {
						errorsNotification.displayError(caught.getMessage());
					}

					public void onSuccess(StudyRun result) {
						displayStudyRun(result);
					}
				});
	}

	protected void displayStudyRun(StudyRun run) {
		this.currentRun = run;
		displayNextSlide();
	}

	private void displayNextSlide() {
		runView.setContent(currentRun.getNextSlide().createUIElements());
	}

	protected void nextSlideButtonPressed() {
		if (currentRun.hasMoreSlides()) {
			displayNextSlide();
		} else {
			runView.setVisible(false);
			finishView.setVisible(true);
		}
	}

	private void finishButtonPressed() {
		finishView.setVisible(false);
		startView.setVisible(true);
	}

}
