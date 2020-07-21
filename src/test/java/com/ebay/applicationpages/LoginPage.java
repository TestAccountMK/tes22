package com.ebay.applicationpages;


import com.automation.utilities.DataRead;
import com.automation.utilities.MobileActions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import jxl.read.biff.BiffException;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	@SuppressWarnings({ "rawtypes", "unused" })
	private AppiumDriver d;
	private MobileActions action;
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(LoginPage.class);
	
	public LoginPage() {
		// this.d = MobileDriverManager.getApmDriverInstance();
	}
	
	@SuppressWarnings("rawtypes")
	public LoginPage(AppiumDriver d) {
		this.d = d;
		action = new MobileActions(d);
		PageFactory.initElements(new AppiumFieldDecorator(d), this);
	}

	
	 @FindBy(id="com.ebay.mobile:id/edit_text_username")
	 private MobileElement username;

	 @FindBy(id="com.ebay.mobile:id/edit_text_password")
	 private MobileElement password;

	 @FindBy(id="com.ebay.mobile:id/button_sign_in")
	 private MobileElement SignInButton;

	  
	  
	  public void  ClickOnSignIn() {
		  action.clickOnElement(SignInButton);
	  }
	  	  
	  public void LogintoEbayapp( String Username, String Password) throws BiffException, IOException, InterruptedException {
		  Username = DataRead.getData("Userdata", "Email", 1);
		  Password = DataRead.getData("Userdata", "Password", 1); 
		  action.inputText(username, Username );
		  action.inputText(password, Password);	  
		  
		  
	  }
	
	
}
