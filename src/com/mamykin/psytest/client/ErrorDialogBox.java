package com.mamykin.psytest.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ErrorDialogBox extends DialogBox {
	VerticalPanel panel = new VerticalPanel();
	Label messageLabel = new Label();
	Button ok = new Button("Ok");

	public ErrorDialogBox() {
		super();
		setTitle("Error");
		setModal(true);
		add(panel);
		panel.add(messageLabel);
		panel.add(ok);
		ok.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				hide();
			}
		});
	}
	
	public void displayError(String errorMessage){
		messageLabel.setText(errorMessage);
		this.show();
	}

}
