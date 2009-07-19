package com.mamykin.psytest.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;

public class StudyRunController {
	interface StartView {
		void setGroupsList(String[] groups);

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

	private StartView startView;
	private RunView runView;
	private FinishView finishView;
	private int slideCount;

	public StudyRunController(StartView startView, RunView runView,
			FinishView finishView) {
		this.startView = startView;
		this.runView = runView;
		this.finishView = finishView;
		wireEvents();
	}

	public void start() {
		startView.setVisible(true);
		startView.setGroupsList(new String[] { "Group1", "Group2", "Group3" });
		runView.setVisible(false);
		finishView.setVisible(false);
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
