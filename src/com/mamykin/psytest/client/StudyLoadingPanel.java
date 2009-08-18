package com.mamykin.psytest.client;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

public class StudyLoadingPanel extends PopupPanel {

	// private DockPanel centeredPanel = new DockPanel();
	private Image loadingImage = new Image();

	public StudyLoadingPanel() {
		setModal(true);

		// setVisible(false);
		loadingImage.setUrl("/images/ajax-loader.gif");
		// centeredPanel.add(loadingImage, DockPanel.CENTER);
		// centeredPanel.setSize("100%", "100%");
		setWidget(loadingImage);
		this.center();
	}
}
