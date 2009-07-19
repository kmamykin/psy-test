package com.mamykin.psytest.client;

import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class StudyRunPanel extends VerticalPanel implements StudyRunController.RunView {

	VerticalPanel main = new VerticalPanel();
	VerticalPanel content = new VerticalPanel(); 
	Button continueButton = new Button("Continue");
	
	public StudyRunPanel() {
		super();
		this.add(main);
		main.setHorizontalAlignment(ALIGN_CENTER);
		main.add(content);
		main.add(continueButton);
	}
	
	public HasClickHandlers getContinueButton(){
		return continueButton;
	}

	public void setVisible(Boolean visible) {
		super.setVisible(visible);
	}

	public void setContent(List<Widget> elements) {
		content.clear();
		for(Widget widget : elements){
			content.add(widget);
		}
	}

}
