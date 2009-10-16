package com.mamykin.psytest.client.model;

public interface StudySlideElement {
	public SlideElementWidget createWidget();

	public void recordResult(StudyResultLogger logger);

	public StudySlideElement copyElement();
}
