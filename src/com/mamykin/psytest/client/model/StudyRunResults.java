package com.mamykin.psytest.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudyRunResults implements Serializable, StudyResultLogger {

	private static final long serialVersionUID = 2483633428126648582L;

	private List<ResultRowValue> results;

	public StudyRunResults() {
		this.results = new ArrayList<ResultRowValue>();
	}

	public String formatAsEmailBody() {
		StringBuilder sb = new StringBuilder();
		for (ResultRowValue value : results) {
			sb.append(value.Key).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length()).append("\n");
		for (ResultRowValue value : results) {
			sb.append(quoteForCsv(value.Value)).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length()).append("\n");
		return sb.toString();
	}

	public void addValue(String key, String value) {
		ResultRowValue rowValue = new ResultRowValue();
		rowValue.Key = key;
		rowValue.Value = value;
		results.add(rowValue);
	}

	private String quoteForCsv(String value) {
		if (value == null) {
			return "\"n/a\"";
		} else {
			return "\"" + value.replaceAll("\"", "\"\"").replaceAll("\n", " ") + "\"";
		}
	}

	public static class ResultRowValue implements Serializable {
		private static final long serialVersionUID = -6113152041410868810L;
		public String Key;
		public String Value;
	}

}
