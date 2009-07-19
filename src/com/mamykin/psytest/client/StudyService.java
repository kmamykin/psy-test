package com.mamykin.psytest.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mamykin.psytest.client.model.Study;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("study")
public interface StudyService extends RemoteService {
	Study retrieveStudy();
}
