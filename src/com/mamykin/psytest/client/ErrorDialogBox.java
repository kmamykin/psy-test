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
		// setTitle("Error");
		setModal(true);
		setText("Error");
		panel.add(messageLabel);
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		panel.add(ok);
		ok.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				hide();
			}
		});
		setWidget(panel);
	}

	public void displayError(String errorMessage) {
		messageLabel.setText(errorMessage);
		center();
		this.show();
	}

}
