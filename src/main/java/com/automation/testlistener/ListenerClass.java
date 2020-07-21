package com.automation.testlistener;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.cucumber.listener.Reporter;
import com.automation.drivers.MobileDriverManager;
import com.automation.utilities.FrameWorkUtility;
import com.automation.utilities.MobileActions;


public class ListenerClass implements ITestListener {
	private Logger logger = Logger.getLogger(ListenerClass.class);

	@Override
	public void onFinish(ITestContext arg0) {
		logger.info("Test Finish");
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		logger.info("This is at the start");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		logger.info("onTest Failed But Within Success Percentage");
	}

	@Override
	public void onTestFailure(ITestResult arg0) {

		logger.info("Test  onTestFailure" + arg0);
		
		// Take a snapshot if mobile Driver is created
		if (Boolean.TRUE.equals(MobileDriverManager.isDriverCreated())) {
			MobileActions action = new MobileActions();

			Calendar cal = Calendar.getInstance();
			String strErrFileName = FrameWorkUtility.getreportPathLoctaion() + File.separator + "images"
					+ File.separator + cal.get(Calendar.DAY_OF_MONTH) + "_" + (cal.get(Calendar.MONTH) + 1) + "_"
					+ cal.get(Calendar.YEAR) + "_" + cal.get(Calendar.HOUR_OF_DAY) + "_" + cal.get(Calendar.MINUTE)
					+ "_" + cal.get(Calendar.SECOND) + ".jpg";

			action.takeSnapShot(strErrFileName);
			try {
				Reporter.addScreenCaptureFromPath(strErrFileName);
			} catch (IOException e) {

				logger.error(e);
			}
		}

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		logger.info("Test is skipped" + arg0);
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		logger.info("Test stated successful" + arg0);

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		logger.info("Test is successful" + arg0);

	}

}
