package com.mamykin.psytest.client;

import java.util.Collection;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StudyStartPanel extends VerticalPanel implements
		StudyRunController.StartView {
	Label studyName = new Label();
	ListBox groupSelection = new ListBox();
	ListBox caseSelection = new ListBox();
	TextBox participantName = new TextBox();
	
	Button startButton = new Button("Start");

	public StudyStartPanel() {
		super();
		this.setHorizontalAlignment(ALIGN_CENTER);
		this.setSpacing(5);

		this.add(studyName);

		this.add(groupSelection);
		
		this.add(caseSelection);
		
		this.add(participantName);

		this.add(startButton);
	}

	public HasClickHandlers getStartButton() {
		return startButton;
	}

	public String getSelectedGroup() {
		return groupSelection.getItemText(groupSelection.getSelectedIndex());
	}

	public void setGroupsList(Collection<String> groups) {
		for (String group : groups) {
			groupSelection.addItem(group);
		}
	}

	public void setVisible(Boolean visible) {
		super.setVisible(visible);
	}

	public HasText getStudyName() {
		return studyName;
	}

	public HasValue<String> getParticipantName() {
		return participantName;
	}

	public String getSelectedCase() {
		return caseSelection.getItemText(caseSelection.getSelectedIndex());
	}

	public void setCasesList(Collection<String> cases) {
		for(String caseName : cases){
			caseSelection.addItem(caseName);
		}
	}

}
