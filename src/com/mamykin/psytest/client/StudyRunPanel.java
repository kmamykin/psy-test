package com.mamykin.psytest.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StudyRunPanel extends VerticalPanel{

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

}
