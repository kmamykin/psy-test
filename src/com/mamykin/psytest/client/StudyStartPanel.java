package com.mamykin.psytest.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StudyStartPanel extends VerticalPanel implements StudyRunController.StartView{
	Label welcomeMessage = new Label();
	ListBox groupSelection = new ListBox();
	Button startButton = new Button("Start");
	public StudyStartPanel() {
		super();
		this.setHorizontalAlignment(ALIGN_CENTER);
		this.setSpacing(5);
		
		this.add(welcomeMessage);

		this.add(groupSelection);
		
		this.add(startButton);
	}
	
	public HasClickHandlers getStartButton(){
		return startButton;
	}

	public String getSelectedGroup() {
		return groupSelection.getItemText(groupSelection.getSelectedIndex());
	}

	public void setGroupsList(String[] groups) {
		for(String group: groups){
			groupSelection.addItem(group);
		}
	}

	public void setVisible(Boolean visible) {
		super.setVisible(visible);
	}

}
