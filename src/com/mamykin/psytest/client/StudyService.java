package com.mamykin.psytest.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mamykin.psytest.client.model.Study;
import com.mamykin.psytest.client.model.StudySlide;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("study")
public interface StudyService extends RemoteService {
	Study getStudy();
	ArrayList<StudySlide> getStudyRunSlides(String groupName, String caseName);
}
