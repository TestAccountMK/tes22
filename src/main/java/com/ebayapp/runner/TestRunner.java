package com.ebayapp.runner;

import java.io.File;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import com.cucumber.listener.Reporter;
import com.automation.drivers.MobileDriverManager;
import com.automation.utilities.FrameWorkUtility;
import com.automation.utilities.LoggerConfig;
import com.cucumber.listener.ExtentProperties;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/*
 *  User can use CucumberOptions 
 *                      to execute any feature file
 */

@CucumberOptions(features = "src/test/java/com/ebay/feature/", tags = {"@ebay_search"},
	            glue = { "com.ebay.stepDefinition","com.automation.testlistener" }, 
	             plugin = {"json:Output/reports/cucumber-report/CucumberTestReport.json",
				                                                                 "com.cucumber.listener.ExtentCucumberFormatter:" })

@Listeners({com.automation.testlistener.ListenerClass.class})

public class TestRunner extends AbstractTestNGCucumberTests {
	
	private static Logger logger = Logger.getLogger(TestRunner.class);

	@BeforeClass()
	public void setUp() {
		LoggerConfig.configuringlog4jXmlFile();
		System.setProperty("logPath", FrameWorkUtility.baseReportFolder());		

		// Setting up the report path
		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
		extentProperties.setReportPath(FrameWorkUtility.getExtentReportFileLocation());
	}

	@AfterClass()
	public void reportSetup() {
		
		Reporter.loadXMLConfig(new File("src"+File.separator+"main"+File.separator+"resources"+File.separator+"extent-config.xml"));
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));        
        Reporter.setSystemInfo("Operating System", System.getProperty("os.name").toLowerCase());
        
	}

	@AfterSuite()
	public void tearDown() {
		logger.info("@AfterSuite : Closing All sessions");

		try {
			MobileDriverManager.closeAllSession();
			
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
	}
	@AfterMethod
	public void runCleanUpAfterMethod() {
		logger.info("@After Method : Closing All sessions");
		MobileDriverManager.closeAllSession();
	}

}
