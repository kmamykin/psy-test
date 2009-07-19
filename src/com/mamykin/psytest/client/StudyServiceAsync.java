package com.mamykin.psytest.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mamykin.psytest.client.model.Study;

/**
 * The async counterpart of <code>StudyService</code>.
 */
public interface StudyServiceAsync {
	void retrieveStudy(AsyncCallback<Study> callback);
}
