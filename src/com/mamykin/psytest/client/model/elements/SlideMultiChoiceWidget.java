package com.mamykin.psytest.client.model.elements;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mamykin.psytest.client.model.SlideElementWidget;

public class SlideMultiChoiceWidget extends SlideElementWidget {
	private final SlideMultiChoiceElement element;
	private final VerticalPanel panel = new VerticalPanel();
	private final Label errorMessage = new Label("Please choose your response below and/or enter text in the text field.");
	private final ListBox choices = new ListBox(true);
	private final TextArea textArea = new TextArea();

	public SlideMultiChoiceWidget(SlideMultiChoiceElement element) {
		super();
		this.element = element;

		panel.add(new Label(element.getQuestion()));

		errorMessage.setVisible(false);
		errorMessage.setStyleName("error");
		panel.add(errorMessage);

		for (String choice : element.getChoices()) {
			choices.addItem(choice);
		}
		choices.setVisibleItemCount(choices.getItemCount() > 10 ? 10 : choices.getItemCount());
		choices.setWidth("250px");
		panel.add(choices);

		textArea.setCharacterWidth(80);
		textArea.setVisibleLines(10);
		panel.add(textArea);

		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		initWidget(panel);
	}

	@Override
	public boolean isValid() {
		if (getSelectedChoices().size() > 0 || textArea.getText().trim().length() > 0) {
			errorMessage.setVisible(false);
			return true;
		} else {
			errorMessage.setVisible(true);
			return false;
		}
	}

	@Override
	public void recordValues() {
		element.setUserSelectedChoices(getSelectedChoices());
		element.setText(textArea.getText());
	}

	private List<String> getSelectedChoices() {
		List<String> selected = new ArrayList<String>();
		for (int i = 0; i < choices.getItemCount(); i++) {
			if (choices.isItemSelected(i)) {
				selected.add(choices.getItemText(i));
			}
		}
		return selected;
	}

}
