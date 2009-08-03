package com.mamykin.psytest.client.model.elements;

import java.io.Serializable;

public class SlideChoiceAnswer implements Serializable {
	private static final long serialVersionUID = 4932441436333394884L;
	private boolean correct;
	private String text;
	private String imageUrl;

	public SlideChoiceAnswer(){
		this.imageUrl = "";
		this.text = "";
		this.correct = false;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String url) {
		this.imageUrl = url;
	}
	
	public boolean hasImage(){
		return getImageUrl().trim().length() > 0;
	}
	
}
