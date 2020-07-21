#Author: Meenakankat@gmail.com
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Loginto the Ebay Mobile app and search for an Item by verifying the Testdata , screen orientations etc
   
@ebay_search
Scenario: Verify the Login functionality of ebay mobile app
   Given user connects to ebay app using appium server and launch the app
   Then  user searchforanitem which s/he wants to buy in search box
   Then  verify the orientation of app
   When  the list of searched items are shown on the mobile screen
   Then  user selects one of the item 
   And   tap on Add to cart
   Then  verify information of name/price/description on searchscreen and checkout screen are same
   

@ebay_Login
Scenario: Verify the Login functionality of ebay mobile app
   Given user connects to ebay app using appium server and launch the app
   Then  user taps on Sign in button
   And   verify welcome screen opensup 
   Then  user enter "username" and "password" 
   And   taps on Sign in button which is enabled after entering credentials 