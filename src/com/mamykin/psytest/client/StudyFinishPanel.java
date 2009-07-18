package com.mamykin.psytest.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StudyFinishPanel extends VerticalPanel{
	Button finishButton = new Button("Finish");
	
	public StudyFinishPanel(){
		this.add(finishButton);
	}
	
	public HasClickHandlers getFinishButton(){
		return finishButton;
	}
}
