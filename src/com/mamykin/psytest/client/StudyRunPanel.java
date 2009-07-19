package com.mamykin.psytest.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StudyRunPanel extends VerticalPanel implements StudyRunController.RunView {

	Label content = new Label(); 
	Button continueButton = new Button("Continue");
	
	public StudyRunPanel() {
		super();
		this.add(content);
		this.add(continueButton);
	}
	
	public HasClickHandlers getContinueButton(){
		return continueButton;
	}

	public void setContent(long currentTimeMillis) {
		content.setText(Long.toString(currentTimeMillis));
	}

	public void setVisible(Boolean visible) {
		super.setVisible(visible);
	}

	public void setContent(String[] elements) {
		content.setText(""); // clear the slide
		StringBuilder sbBuilder = new StringBuilder();
		for(String element : elements){
			sbBuilder.append(element).append("<br>");
		}
		content.setText(sbBuilder.toString());
	}

}
