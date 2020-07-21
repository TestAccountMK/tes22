package com.automation.testlistener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.log4j.Logger;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.runtime.ScenarioImpl;
import gherkin.formatter.model.Result;

public class CucumberHooks {

	private static Logger logger = Logger.getLogger(CucumberHooks.class);
	int runId;
	int caseID;

	@Before
	public void startOfReporting() {
		logger.info("@CucumberHooks Before Class trigger");
	}

	@After
	public void cleanUpAndUpdateResults(Scenario scenario) {

		
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private static String logError(Scenario scenario) {
		
		StringBuilder errorMessages = new StringBuilder();
		Field field = FieldUtils.getField(((ScenarioImpl) scenario).getClass(), "stepResults", true);
		field.setAccessible(true);
		try {

			ArrayList<Result> results = (ArrayList<Result>) field.get(scenario);
			for (Result result : results) {
				if (result.getError() != null) {
					errorMessages.append(result.getError());
					errorMessages.append(":");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return errorMessages.toString();
	}
}
