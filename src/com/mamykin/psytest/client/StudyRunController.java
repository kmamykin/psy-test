package com.mamykin.psytest.client;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.mamykin.psytest.client.model.Study;
import com.mamykin.psytest.client.model.StudySlide;

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
		void setContent(String[] elements);

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
	private ArrayList<StudySlide> slides;
	private int currentSlide;
	private String currentPatricipant;

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
		this.currentPatricipant = startView.getParticipantName().getValue();
		startView.getParticipantName().setValue("");
		retrieveStudyRunSlides();
	}

	private void retrieveStudyRunSlides() {
		studyService.getStudyRunSlides(startView.getSelectedGroup(), startView
				.getSelectedCase(), new AsyncCallback<ArrayList<StudySlide>>() {

					public void onFailure(Throwable caught) {
						errorsNotification.displayError(caught.getMessage());
					}

					public void onSuccess(ArrayList<StudySlide> result) {
						displaySlides(result);
					}
		});
	}

	protected void displaySlides(ArrayList<StudySlide> result) {
		this.slides = result;
		this.currentSlide = 0;
		displayCurrentSlide();
	}

	private void displayCurrentSlide() {
		runView.setContent(getCurrentSlide().getElements());
	}

	private StudySlide getCurrentSlide() {
		return slides.get(currentSlide);
	}

	protected void nextSlide() {
		currentSlide++;
		if (currentSlide >= slides.size()) {
			runView.setVisible(false);
			finishView.setVisible(true);
		} else {
			displayCurrentSlide();
		}
	}

	private void finishRun() {
		finishView.setVisible(false);
		startView.setVisible(true);
	}

}
