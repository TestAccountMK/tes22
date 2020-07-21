package com.automation.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.drivers.MobileDriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class MobileActions {
	@SuppressWarnings("rawtypes")
	AppiumDriver driver;
	@SuppressWarnings("rawtypes")
	static AndroidDriver d;
	static WebDriverWait wait;
	private static Logger logger =  Logger.getLogger(MobileActions.class);
	Actions action;
	@SuppressWarnings("rawtypes")
	TouchAction tActions;

	@SuppressWarnings("rawtypes")
	public MobileActions(AppiumDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		action = new Actions(driver);
		tActions = new TouchAction(driver);
	}

	@SuppressWarnings("rawtypes")
	public MobileActions() {
		this.driver = MobileDriverManager.getDriver();
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		tActions = new TouchAction(driver);
	}

	public void clickOnElement(MobileElement element) {
		element.click();
	}

	public void clearTextField(MobileElement element) {
		element.clear();
	}

	public String getElementText(MobileElement element) {
		return element.getText();
	}

	public void inputText(MobileElement element, String value) {
		element.sendKeys(value);
	}
	
	public void validateTitle(String title) {
		System.out.println(driver.getTitle());
		if (driver.getTitle().equalsIgnoreCase(title)) {
			logger.info("Page title matched");
		} else {
			Assert.fail();
		}
	}

	public void validateText(MobileElement element, String expectedText) {
		if (element.getText().equalsIgnoreCase(expectedText)) {
			logger.info("text matched");
		} else {
			Assert.fail();
		}
	}
	
	 public void WaitAppToLoad(){
	  try {
	      driver.wait(4000);
	      } catch (InterruptedException e) {
	            e.printStackTrace();
	      }
	    }

	public void waitForVisibilityOfElement(MobileElement e) {
		wait.until(ExpectedConditions.visibilityOf(e));
		logger.info("Waiting for visiblity of element");
	}

	public void waitForElementToBeClickable(MobileElement e) {
		wait.until(ExpectedConditions.elementToBeClickable(e));
		logger.info("Waiting for Element to get click");

	}

	public void waitForElementToBeInvisible(MobileElement ele) {
		wait.until(ExpectedConditions.invisibilityOf(ele));
		logger.info("Waiting for Element to be invisible");
	}



	public void waitForElementToBeSelected(MobileElement e) {
		wait.until(ExpectedConditions.elementToBeSelected(e));
		logger.info("Waiting for Element to be Selected");
	}

	public void waitForTextToBePresentInElement(MobileElement e, String s) {
		wait.until(ExpectedConditions.textToBePresentInElement(e, s));
		logger.info("Waiting for Text to be present in element");
	}

	public boolean isElementDisplayed(MobileElement e) {
		return e.isDisplayed();
	}

	public boolean isElementSelected(MobileElement e) {
		return e.isSelected();
	}

	public boolean isElementEnabled(MobileElement e) {
		return e.isDisplayed();
	}

	public void moveToElement(MobileElement element) {
		action.moveToElement(element);
		action.perform();
	}

	public void doubleClickElement(MobileElement element) {
		action.doubleClick().perform();

	}

	public String context() {
		return driver.getContext();
	}

	@SuppressWarnings("unchecked")
	public Set<String> allContext() {
		Set<String> contextNames = driver.getContextHandles();
		return contextNames;
	}

	@SuppressWarnings("unchecked")
	public void setContext(String context) {
		Set<String> availableContext = driver.getContextHandles();
		for (String requireContext : availableContext) {
			if (requireContext.equalsIgnoreCase(context)) {
				driver.context(requireContext);
				break;
			}
		}
	}
	
	 public void GetCurrentRotation(){
	        logger.info("Current rotation of screen is ="+driver.getOrientation());
	    
	 }
	   
	 public void SetRotationPortait(){
	        logger.info("Current rotation set to PORTRAIT=");
	        driver.rotate(ScreenOrientation.PORTRAIT);
	 }
	   
	 public void SetRotationLandscape(){
	        logger.info("Current rotation set to PORTRAIT=");
	        driver.rotate(ScreenOrientation.LANDSCAPE);
	 }

	public void takeSnapShot(String destinationPath) {
		File src = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File(destinationPath);
		try {
			FileUtils.copyFile(src, destination);
			logger.info("Screen shot captured and save at '" + destination + "'");
		} catch (IOException e) {
			logger.error("Failed to capture the screenshot");

		}
	}
	
	
	@SuppressWarnings("rawtypes")
	public static void scrollDown(){
        Dimension dimension = MobileDriverManager.getDriver().manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.5);
        int scrollEnd = (int) (dimension.getHeight() * 0.2);
        new TouchAction((PerformsTouchActions) MobileDriverManager.getDriver())
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release().perform();
    }
	
	public static void scrollNClick(WebElement el){
        int retry = 0;
        while(retry <= 5){
            try{
                el.click();
                break;
            }catch (org.openqa.selenium.NoSuchElementException e){
                scrollDown();
                retry++;
            }
        }
    }
	
	 @SuppressWarnings("unchecked")
	public static void scrollIntoView(String Text){
	        ((AndroidDriver<MobileElement>) MobileDriverManager.getDriver()).findElementByAndroidUIAutomator(
	                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + Text + "\").instance(0))").click();
	    }
	
	/*public static void scrollNClick(By listItems, String Text){
    boolean flag = false;

     while(true){
       for(WebElement el: MobileDriverManager.getDriver().findElements(listItems)){
           if(el.getAttribute("text").equals(Text)){
               el.click();
               flag=true;
               break;
           }
       }
       if(flag)
           break;
       else
           scrollDown();
   }
}*/
	 
	 
	//Changing screen Orientation to PORTRAIT
	 public void screenorientationPORTRAIT() {	
		 driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
		 System.out.println("*--*--*-- Current screen orientation Is : " + driver.getOrientation());
	 }
	 
	//Changing screen Orientation to Landscape
		 public void screenorientationLandscape() {	
			 driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
			 System.out.println("*--*--*-- Current screen orientation Is : " + driver.getOrientation());
		 }
	 
	

}
