package com.ebay.stepDefinition;

import com.automation.drivers.MobileDriverManager;
import com.automation.utilities.DataRead;
import com.ebay.applicationpages.LoginPage;
import com.ebay.applicationpages.SearchPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;

public class ebayLoginStepDef {
	
	
	
	
	@SuppressWarnings("rawtypes")
	AppiumDriver d;
	MobileDriverManager manager;
	LoginPage loginPage;
	SearchPage searchPage;
	DataRead dr;
	
	
	public ebayLoginStepDef() {
		d = MobileDriverManager.getDriver();
		loginPage = new LoginPage(d);
		searchPage = new SearchPage(d);
	
	}	
	
	@Given("^user connects to ebay app using appium server and launch the app$")
	public void user_connects_to_ebay_app_using_appium_server_and_launch_the_app() throws Throwable {
		System.out.println("driver instance");
		d = MobileDriverManager.getDriver();
		System.out.println("Launching the app");
	}

	@Then("^user taps on Sign in button$")
	public void user_taps_on_Sign_in_button() throws Throwable {
	    loginPage.ClickOnSignIn();
	}

	@Then("^verify welcome screen opensup$")
	public void verify_welcome_screen_opensup() throws Throwable {
	   
	}

	@Then("^user enter \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enter_and(String Username, String Password) throws Throwable {
		System.out.println("entering username");
	    loginPage.LogintoEbayapp(Username, Password);
	}
	
	@Then("^taps on Sign in button which is enabled after entering credentials$")
	public void taps_on_Sign_in_button_which_is_enabled_after_entering_credentials() throws Throwable {
	   
	}
	
	
	/*
	 *  below steps are used to search for an item search box
	 */

	@Then("^user searchforanitem which s/he wants to buy in search box$")
	public void user_searchforanitem_which_s_he_wants_to_buy_in_search_box() throws Throwable {
		Thread.sleep(3000);
		searchPage.clickonSearch();
		searchPage.SearchItemFromSearchBox();
	}
	
	@Then("^verify the orientation of app$")
	public void verify_the_orientation_of_app() throws Throwable {
	    searchPage.screenorientation();
	}
	
	@When("^the list of searched items are shown on the mobile screen$")
	public void the_list_of_searched_items_are_shown_on_the_mobile_screen() throws Throwable {
		searchPage.scrolldownthepage();
	}

	@Then("^user selects one of the item$")
	public void user_selects_one_of_the_item() throws Throwable {
		
	}

	@Then("^tap on Add to cart$")
	public void tap_on_Add_to_cart() throws Throwable {
		searchPage.clickonAddtoCart();
	}

	@Then("^verify information of name/price/description on searchscreen and checkout screen are same$")
	public void verify_information_of_name_price_description_on_searchscreen_and_checkout_screen_are_same() throws Throwable {
	    
	}

	
	
	
	
	
	
	
	
	
	
	
	

}
