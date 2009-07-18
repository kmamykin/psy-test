package com.mamykin.psytest.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class StudyRunController {
	interface StartView {

	}

	private StudyStartPanel startView;
	private StudyRunPanel runView;
	private StudyFinishPanel finishView;
	private int slideCount;

	public StudyRunController(StudyStartPanel startView,
			StudyRunPanel runPanel, StudyFinishPanel finishPanel) {
		this.startView = startView;
		this.runView = runPanel;
		this.finishView = finishPanel;
		initializeViews();
	}

	private void initializeViews() {
		startView.setVisible(true);
		startView.getStartButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				startRun();
			}
		});
		runView.setVisible(false);
		runView.getContinueButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				nextSlide();
			}

		});
		finishView.setVisible(false);
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
