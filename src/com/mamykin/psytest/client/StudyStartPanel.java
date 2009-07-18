package com.mamykin.psytest.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StudyStartPanel extends VerticalPanel{
	Label welcomeMessage = new Label();
	ListBox groupSelection = new ListBox();
	Button startButton = new Button("Start");
	public StudyStartPanel() {
		super();
		this.setHorizontalAlignment(ALIGN_CENTER);
		this.setSpacing(5);
		
		this.add(welcomeMessage);
		
		groupSelection.addItem("Group 1");
		groupSelection.addItem("Group 2");
		groupSelection.addItem("Group 3");
		groupSelection.addItem("Group 4");
		
		this.add(groupSelection);
		
		this.add(startButton);
	}
	
	public HasClickHandlers getStartButton(){
		return startButton;
	}

}
