package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;
import com.utility.LoggerUtility;

public final class LoginPage extends BrowserUtility {
	
	Logger logger=LoggerUtility.getLogger(this.getClass());
	
	//these values are not going to change so marked them with final keyword
	public static final By EMAIL_TEXT_LOCATOR = By.xpath("//input[@id='email']");
	public static final By PASSWORD_TEXT_LOCATOR = By.xpath("//input[@id='passwd']");
	public static final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//button[@id='SubmitLogin']");
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public MyAccountPage doLoginWith(String emailaddress, String password)
	{
		logger.info("enter the emailaddress as " +emailaddress+ " and password " +password+ " and click on sign in button");
		enterText(EMAIL_TEXT_LOCATOR, emailaddress);
		enterText(PASSWORD_TEXT_LOCATOR, password );
		clickOn(SIGN_IN_BUTTON_LOCATOR);
		MyAccountPage myAccountPage = new MyAccountPage(getDriver());
		return myAccountPage;
	}
	
	

}
