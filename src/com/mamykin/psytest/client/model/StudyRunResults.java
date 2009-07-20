package com.mamykin.psytest.client.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class StudyRunResults implements Serializable, StudyResultLogger {

	private static final long serialVersionUID = 2483633428126648582L;

	private HashMap<String, HashMap<String, String>> results;

	public StudyRunResults() {
		results = new HashMap<String, HashMap<String, String>>();

	}

	public void addValue(String element, String key, String value) {
		HashMap<String, String> elementProps;
		if (results.containsKey(element)) {
			elementProps = results.get(element);
		} else {
			elementProps = new HashMap<String, String>();
			results.put(element, elementProps);
		}
		elementProps.put(key, value);
	}

	public String formatAsEmailBody() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, HashMap<String, String>> elementEntry : results
				.entrySet()) {
			for (Map.Entry<String, String> valueEntry : elementEntry.getValue()
					.entrySet()) {
				sb.append("Element: ").append(elementEntry.getKey()).append(
						" Key: ").append(valueEntry.getKey())
						.append(" Value: ").append(valueEntry.getValue()).append("\n");
			}
		}
		return sb.toString();
	}

}
