package com.mamykin.psytest.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mamykin.psytest.client.model.StudyInfo;
import com.mamykin.psytest.client.model.StudyRun;
import com.mamykin.psytest.client.model.StudyRunResults;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("study")
public interface StudyService extends RemoteService {
	StudyInfo getStudyInfo();
	StudyRun getStudyRun(String groupName, String caseName, String participant);
	boolean recordRunResults(StudyRunResults results);
}
