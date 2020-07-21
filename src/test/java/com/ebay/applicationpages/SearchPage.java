package com.ebay.applicationpages;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automation.utilities.DataRead;
import com.automation.utilities.MobileActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import jxl.read.biff.BiffException;

public class SearchPage {

	
	@SuppressWarnings({ "rawtypes", "unused" })
	private AppiumDriver d;
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;
	private MobileActions action;
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(SearchPage.class);
	
	public SearchPage() {
		// this.d = MobileDriverManager.getApmDriverInstance();
	}
	
	@SuppressWarnings("rawtypes")
	public SearchPage(AppiumDriver d) {
		this.d = d;
		action = new MobileActions(d);
		PageFactory.initElements(new AppiumFieldDecorator(d), this);
	}

	
	 @FindBy(xpath="//android.widget.TextView[@content-desc=\"Search keyword Search for anything\"]")
	 private MobileElement searchbox;
	 @FindBy(id="com.ebay.mobile:id/search_src_text")
	 private MobileElement searchtext;
	 @FindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.TextView")
	 private MobileElement searchitemn;
	 @FindBy(id="com.ebay.mobile:id/text_slot_1")
	 private MobileElement savebutton;
	 @FindBy(xpath="//android.widget.Button[@text='Add to basket']")
	 private MobileElement AddToCartButton;
	 @FindBy(id="com.ebay.mobile:id/textview_item_price")
	 private MobileElement selecteditemprice;
	 @FindBy(id="com.ebay.mobile:id/call_to_action_button")
	 private MobileElement GotoBasket;
	 

	 
	 public void clickonSearch() throws InterruptedException {
		 System.out.println("click on search box");
	     Thread.sleep(3000);
	      action.clickOnElement(searchbox);
	      System.out.println("clicked on search box");
	        
	  }
	
	  public void SearchItemFromSearchBox( ) throws BiffException, IOException {
		    System.out.println("Enter Text");
			String item = DataRead.getData("Userdata", "Item", 1);
		    action.inputText(searchtext, item);
	        action.clickOnElement(searchitemn);   
	  }
	  
	public void scrolldownthepage() throws BiffException, IOException {
		  action.clickOnElement(savebutton);
		  String Text =  DataRead.getData("Userdata", "Particularitem", 1);
		  MobileActions.scrollIntoView(Text);
	  }
	
	public void screenorientation() throws InterruptedException {
		action.screenorientationLandscape();
		Thread.sleep(2000);
		System.out.println();
		action.screenorientationPORTRAIT();
		
	}

	public void clickonAddtoCart() {
		String actualtext = action.getElementText(selecteditemprice);
		System.out.println("selected item price is : " +actualtext);
		action.clickOnElement(AddToCartButton);
		action.clickOnElement(GotoBasket);
		String textonCheckoutscreen = action.getElementText(selecteditemprice);
		System.out.println("selected item price is : " +textonCheckoutscreen);
		Assert.assertSame(actualtext, textonCheckoutscreen);
		
	//	FrameWorkUtility.verifyingTheActualAndExpected(expectedValue, actualValue);
		
	}
	
	
	 
	
	
}

