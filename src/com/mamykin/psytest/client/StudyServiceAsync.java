package com.mamykin.psytest.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mamykin.psytest.client.model.Study;
import com.mamykin.psytest.client.model.StudySlide;

/**
 * The async counterpart of <code>StudyService</code>.
 */
public interface StudyServiceAsync {
	void getStudy(AsyncCallback<Study> callback);

	void getStudyRunSlides(String groupName, String caseName,
			AsyncCallback<ArrayList<StudySlide>> callback);

}
