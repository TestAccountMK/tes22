package com.automation.drivers;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

@SuppressWarnings("rawtypes")
public class MobileDriverManager {

	private static Logger logger = Logger.getLogger(MobileDriverManager.class);

	private static AppiumDriver driver;
	
	public static AppiumDriver getDriver() {
		if (driver != null)
			return driver;
		return getDriverInstance();
	}

	public static boolean isTargetAndroid() {
		// TODO Auto-generated method stub
		return true;
	}

	private MobileDriverManager() {
	}

	/**
	 * Get the driver instance
	 * 
	 * @return
	 */
	public static boolean isDriverCreated() {
		if (null != driver)
			return true;
		return false;
	}

	private static AppiumDriver getDriverInstance() {
		AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder().usingAnyFreePort();
		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(serviceBuilder);

		service.start();

		driver = (isTargetAndroid()) ? getAndroidDriver(service.getUrl(), false, false)
				: getIOSDriver(service.getUrl(), false, false);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return driver;

	}

	private static AppiumDriver getAndroidDriver(URL appiumUrl, boolean fullReset, boolean noReset)  {
			
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "ebay");
		
		capabilities.setCapability("udid", "R9EMA0BAPNJ");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		
		capabilities.setCapability("appPackage", "com.ebay.mobile");
		capabilities.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");	
		
		capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, true);
		capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true); // set unicodekeyboard in order
																						// to support umlauts
		capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true); // reset keyboard after tests

		if (fullReset) {
			capabilities.setCapability("fullReset", fullReset);
		}
		if (noReset) {
			capabilities.setCapability("noReset", noReset);
		}

		return new AndroidDriver(appiumUrl, capabilities);
	}


	private static AppiumDriver getIOSDriver(URL appiumUrl, boolean fullReset, boolean noReset) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
		capabilities.setCapability(MobileCapabilityType.APP, "");
		capabilities.setCapability("bundleId", "**TBD**");
		capabilities.setCapability("locationServicesAuthorized", true);
		capabilities.setCapability("locationServicesEnabled", true);
		capabilities.setCapability("autoAcceptAlerts", true);

		if (fullReset) {
			capabilities.setCapability("fullReset", fullReset);
		}
		if (noReset) {
			capabilities.setCapability("noReset", noReset);
		}
		return new IOSDriver(appiumUrl, capabilities);
	}

	/**
	 * Close the current window, quitting the browser if it's the last window
	 * currently open.
	 */
	public static void closeSession() {
		if (null != driver) {
			driver.close();
			driver = null;
		} else {
			logger.info("No driver instance to close");
		}
	}

	/**
	 * Quits this driver, closing every associated window.
	 */
	public static void closeAllSession() {
		if (null != driver) {
			driver.quit();
			driver = null;
		} else {
			logger.info("No driver instance to close");
		}
	}

}