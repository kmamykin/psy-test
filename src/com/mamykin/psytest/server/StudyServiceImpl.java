package com.mamykin.psytest.server;

import java.util.ArrayList;

import com.mamykin.psytest.client.StudyService;
import com.mamykin.psytest.client.model.Study;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class StudyServiceImpl extends RemoteServiceServlet implements
		StudyService {

	public Study retrieveStudy() {
		Study newStudy  = new Study();
		newStudy.setName("Some Study");
		ArrayList<String> groups = new ArrayList<String>();
		groups.add("Group 1");
		groups.add("Group 2");
		groups.add("Group 3");
		groups.add("Group 4");
		newStudy.setGroups(groups);
		return newStudy;
	}
}
