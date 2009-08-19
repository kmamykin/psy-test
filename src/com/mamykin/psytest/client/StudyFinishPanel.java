package com.mamykin.psytest.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StudyFinishPanel extends VerticalPanel implements StudyRunController.FinishView {
	Button finishButton = new Button("Finish");

	public StudyFinishPanel() {
		this.add(finishButton);
		this.setSpacing(15);
	}

	public HasClickHandlers getFinishButton() {
		return finishButton;
	}

	public void setVisible(Boolean visible) {
		super.setVisible(visible);
	}
}
