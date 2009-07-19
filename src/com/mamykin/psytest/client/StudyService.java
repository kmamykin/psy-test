package com.mamykin.psytest.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mamykin.psytest.client.model.Study;
import com.mamykin.psytest.client.model.StudyRun;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("study")
public interface StudyService extends RemoteService {
	Study getStudy();
	StudyRun getStudyRun(String groupName, String caseName, String participant);
}
