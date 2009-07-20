package com.mamykin.psytest.client.model;

import java.io.Serializable;

import com.google.gwt.user.client.ui.Widget;

public abstract class StudySlideElement implements Serializable {
	private static final long serialVersionUID = 2463722739964506452L;
	private String id;

	public abstract Widget createUIElement();

	public abstract boolean isUIValid();

	public abstract void recordResult(StudyResultLogger logger);

	protected StudySlideElement(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String value) {
		id = value;
	}
}
