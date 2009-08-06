package com.mamykin.psytest.client;

import java.util.Collection;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class StudyStartPanel extends FlowPanel implements
		StudyRunController.StartView {
	Label studyName = new Label();
	Label groupSelectionLabel = new Label("Select group:");
	ListBox groupSelection = new ListBox();
	Label caseSelectionLabel = new Label("Select case:");
	ListBox caseSelection = new ListBox();
	Label participantNameLabel = new Label("Enter participant name:");
	TextBox participantName = new TextBox();
	
	Button startButton = new Button("Start");

	public StudyStartPanel() {
		super();

		FlexTable t = new FlexTable();
		t.setWidget(0, 0, studyName);
		t.getFlexCellFormatter().setColSpan(0, 0, 2);
		t.getFlexCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
		
		
		t.setWidget(1, 0, groupSelectionLabel);
		t.getFlexCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
		t.setWidget(1, 1, groupSelection);
		t.getFlexCellFormatter().setAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_MIDDLE);
		
		t.setWidget(2, 0, caseSelectionLabel);
		t.getFlexCellFormatter().setAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
		t.setWidget(2, 1, caseSelection);
		t.getFlexCellFormatter().setAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_MIDDLE);
		
		t.setWidget(3, 0, participantNameLabel);
		t.getFlexCellFormatter().setAlignment(3, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
		t.setWidget(3, 1, participantName);
		t.getFlexCellFormatter().setAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_MIDDLE);

		t.setWidget(4, 0, startButton);
		t.getFlexCellFormatter().setColSpan(4, 0, 2);
		t.getFlexCellFormatter().setAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
		this.add(t);
	}

	public HasClickHandlers getStartButton() {
		return startButton;
	}

	public String getSelectedGroup() {
		return groupSelection.getItemText(groupSelection.getSelectedIndex());
	}

	public void setGroupsList(Collection<String> groups) {
		groupSelection.clear();
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
		caseSelection.clear();
		for(String caseName : cases){
			caseSelection.addItem(caseName);
		}
	}

}
