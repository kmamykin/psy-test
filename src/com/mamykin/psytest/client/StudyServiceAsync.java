package com.mamykin.psytest.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mamykin.psytest.client.model.Study;
import com.mamykin.psytest.client.model.StudyRun;
import com.mamykin.psytest.client.model.StudyRunResults;

/**
 * The async counterpart of <code>StudyService</code>.
 */
public interface StudyServiceAsync {
	void getStudy(AsyncCallback<Study> callback);

	void getStudyRun(String groupName, String caseName, String participant,
			AsyncCallback<StudyRun> callback);
	void recordRunResults(StudyRunResults results, 
			AsyncCallback<Boolean> callback);
}
