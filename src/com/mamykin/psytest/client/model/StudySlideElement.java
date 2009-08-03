package com.mamykin.psytest.client.model;

import com.mamykin.psytest.client.widgets.SlideElementWidget;

public interface StudySlideElement {
	public SlideElementWidget createUIElement();
	public void recordResult(StudyResultLogger logger);
}
