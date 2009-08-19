package com.mamykin.psytest.client;

import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mamykin.psytest.client.model.SlideElementWidget;

public class StudyRunPanel extends VerticalPanel implements StudyRunController.RunView {

	private static final int EVERY_SECOND = 1000; // in milliseconds
	private final VerticalPanel main = new VerticalPanel();
	private final VerticalPanel content = new VerticalPanel();
	private final Button continueButton = new Button("Continue");
	private final Label timeLeftLabel = new Label();
	private List<SlideElementWidget> elementWidgets;
	private Timer timer;
	private long slideEndTime;

	public StudyRunPanel() {
		super();
		content.setHorizontalAlignment(ALIGN_CENTER);
		content.setSpacing(15);
		main.add(content);

		timeLeftLabel.setVisible(false);
		main.setHorizontalAlignment(ALIGN_CENTER);
		main.setSpacing(15);
		main.add(timeLeftLabel);
		main.add(continueButton);
		this.add(main);

		this.timer = new Timer() {
			@Override
			public void run() {
				updateTimer();
			}
		};
	}

	public HasClickHandlers getContinueButton() {
		return continueButton;
	}

	public void setVisible(Boolean visible) {
		super.setVisible(visible);
	}

	public void setContent(List<SlideElementWidget> elements) {
		this.elementWidgets = elements;
		content.clear();
		for (SlideElementWidget widget : elementWidgets) {
			content.add(widget);
		}
	}

	public boolean contentIsValid() {
		boolean allElementsValid = true;
		for (SlideElementWidget element : elementWidgets) {
			if (!element.isValid()) {
				allElementsValid = false;
				// don't break here, invoke isValid for all elements
			}
		}
		return allElementsValid;
	}

	public void recordResults() {
		for (SlideElementWidget widget : elementWidgets) {
			widget.recordValues();
		}
	}

	public void cancelTimer() {
		timer.cancel();
		timeLeftLabel.setVisible(false);
	}

	public void setTimer(int timeLimitInMillis) {
		slideEndTime = System.currentTimeMillis() + timeLimitInMillis;
		updateTimer();
		timer.scheduleRepeating(EVERY_SECOND);
	}

	private void updateTimer() {
		long millisLeft = slideEndTime - System.currentTimeMillis();
		if (millisLeft <= 0) {
			continueButton.click();
		} else {
			timeLeftLabel.setText(getFormattedTimeLeft(millisLeft));
			timeLeftLabel.setVisible(true);
		}
	}

	private String getFormattedTimeLeft(long millisLeft) {
		return "Time left: " + Integer.toString((int) (millisLeft / 1000));
	}

}
