package com.mamykin.psytest.server;

import java.io.IOException;
import java.util.logging.Logger;

import com.google.appengine.api.mail.MailService;
import com.google.appengine.api.mail.MailServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mamykin.psytest.client.StudyService;
import com.mamykin.psytest.client.model.Study;
import com.mamykin.psytest.client.model.StudyInfo;
import com.mamykin.psytest.client.model.StudyRun;
import com.mamykin.psytest.client.model.StudyRunResults;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class StudyServiceImpl extends RemoteServiceServlet implements StudyService {
	private static final String STUDY_XML = "http://localhost:8080/study.xml";
	// private static final String STUDY_XML =
	// "http://psy-test.appspot.com/study.xml";
	private static final Logger log = Logger.getLogger(StudyServiceImpl.class.getName());

	public StudyInfo getStudyInfo() {
		StudyFactory factory = new StudyFactory();
		Study study = factory.createFromXml(STUDY_XML);
		return study.getStudyInfo();
	}

	public StudyRun getStudyRun(String groupName, String caseName, String participant) {
		StudyFactory factory = new StudyFactory();
		Study study = factory.createFromXml(STUDY_XML);
		return study.createRun(groupName, caseName, participant);
	}

	public boolean recordRunResults(StudyRunResults results) {
		MailService mailService = MailServiceFactory.getMailService();
		MailService.Message email = new MailService.Message("kmamyk@gmail.com", "kmamyk@gmail.com", "Study results", results.formatAsEmailBody());
		log.info(email.getTextBody());
		try {
			mailService.send(email);
		} catch (IOException e) {
			throw new RuntimeException("Could not deliver email.", e);
		}
		return true;
	}

}
