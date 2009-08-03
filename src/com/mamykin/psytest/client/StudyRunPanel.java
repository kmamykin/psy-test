package com.mamykin.psytest.client;

import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mamykin.psytest.client.widgets.SlideElementWidget;

public class StudyRunPanel extends VerticalPanel implements
		StudyRunController.RunView {

	private final VerticalPanel main = new VerticalPanel();
	private final VerticalPanel content = new VerticalPanel();
	private final Button continueButton = new Button("Continue");
	private List<SlideElementWidget> elementWidgets;

	public StudyRunPanel() {
		super();
		this.add(main);
		main.setHorizontalAlignment(ALIGN_CENTER);
		main.add(content);
		main.add(continueButton);
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
}
