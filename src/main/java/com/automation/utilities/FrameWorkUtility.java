package com.automation.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;

import cucumber.api.DataTable;

public class FrameWorkUtility {

	private static String reportFile;
	private static String reportPath;

	private static String folder = null;

	private static final String USR_DIR = "user.dir";
	private static Logger logger = Logger.getLogger(FrameWorkUtility.class);

	private FrameWorkUtility() {
		// should be accessed static way
	}

	public static Map<String, String> getDataFromScenarioDataTableAsMap(DataTable table) {
		Map<String, String> dataAsMap = table.asMap(String.class, String.class);
		Map<String, String> hMapData = new LinkedHashMap<>(dataAsMap);
		logger.info("this is from hMap"+hMapData);
		
		return hMapData;
	}

	public static List<String> getDataFromScenarioDataTableAsList(DataTable table) {
		List<String> listData = table.asList(String.class);
		return new ArrayList<>(listData);

	}

	public static List<LinkedHashMap<String, String>> getDataFromScenariosDataTableAsListOfMaps(DataTable table) {
		// The asMaps returns an unmodifiable list of Maps ,so we need to convert
		// this to a List of Maps which can be modified
		List<Map<String, String>> unmodifiableListOfmaps = table.asMaps(String.class, String.class);
		List<LinkedHashMap<String, String>> listOfMaps = new ArrayList<>();

		for (Map<String, String> m : unmodifiableListOfmaps) {
			listOfMaps.add(new LinkedHashMap<String, String>(m));
		}
		return listOfMaps;
	}

	public static String getExtentReportFileLocation() {

		Date d = new Date();
		String reportTimeStamp = (String.valueOf(d)).replaceAll("[ |:]+", "_");

		// Multiple class are initializing the report folder need to Optimize
		if (reportPath == null || reportPath.isEmpty())
			reportPath = System.getProperty(USR_DIR) + File.separator + "Output" + File.separator + baseReportFolder();

		if (reportFile == null || reportFile.isEmpty())
			reportFile = reportPath + File.separator + "report" + reportTimeStamp + ".html";

		logger.info("report path is :" + reportPath);
		logger.info("report file is :" + reportFile);

		return reportFile;
	}

	public static String getreportPathLoctaion() {

		if (reportPath == null || reportPath.isEmpty())
			reportPath = System.getProperty(USR_DIR) + File.separator + "Output" + File.separator + baseReportFolder();

		logger.info("report path is :" + reportPath);

		return reportPath;
	}

	@SuppressWarnings("deprecation")
	public static String baseReportFolder() {
		if (folder != null) {
			return folder;
		} else {
			Date d = new Date();
			String[] date = (String.valueOf(d)).split(" ");
			String reportTimeStamp = date[3].replace(":", "_");
			String year = date[date.length - 1];
			String month = date[1];
			folder = year + "-" + d.getDate() + "-" + month + "_" + reportTimeStamp;
			return folder;
		}
	}


	public static void verifyingTheActualAndExpected(String expectedValue, Object actualValue) {
		if (actualValue instanceof Boolean) {
			logger.info("Actual Value boolean: " + actualValue);
			logger.info("Value is a Boolean");
			Assert.assertEquals(Boolean.parseBoolean(expectedValue), actualValue, "Actual Value do not match expected");
		} else if (actualValue instanceof String) {
			logger.info("Value is a String");
			Assert.assertEquals(expectedValue, actualValue, "Actual Value do not match expected");
		} else if (actualValue instanceof Integer) {
			logger.info("Value is an Integer");
			Assert.assertEquals(Integer.parseInt(expectedValue), actualValue, "Actual Value do not match expected");

		} else if (actualValue == null) {
			logger.info("Value is null");
			if (expectedValue.equalsIgnoreCase("null")) {
				expectedValue = null;

				Assert.assertTrue(expectedValue == actualValue, "Actual value do not match with expected");
			} else {
				Assert.assertEquals(expectedValue, actualValue, "Actual Value do not match expected");
			}
		} else if (actualValue instanceof Long) {
			logger.info("Value is of type Long");
			Assert.assertEquals(Long.parseLong(expectedValue), actualValue, "Actual Value do not match expected");
		} else if (actualValue instanceof Double) {
			logger.info("Value is of type Double");
			Assert.assertEquals(Double.parseDouble(expectedValue), actualValue, "Actual Value do not match expected");
		} else if (actualValue instanceof Float) {
			logger.info("Value is of type Float");
			Assert.assertEquals(Float.parseFloat(expectedValue), actualValue, "Actual Value do not match expected");
		}
	}

	

}
