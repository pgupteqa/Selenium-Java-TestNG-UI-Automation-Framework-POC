package com.ui.tests;

import static com.constants.Browser.CHROME;
import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.constants.Browser;
import com.constants.Env;
import com.ui.pages.HomePage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.User;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {

	protected HomePage homepage;
	protected MyAccountPage myAccountPage;
	protected User user; // current user object
	private Logger logger = LoggerUtility.getLogger(this.getClass());
	private ExtentTest test = ExtentReporterUtility.getTest();
	private boolean isUserLoggedIn = false;
	private boolean isLambdaTest;

	@Parameters({ "browsername", "isLambdaTest", "isHeadLess", "enviornmentname" })
	@BeforeMethod(description = "Load the homepage of the website")
	public void setup(@Optional("chrome") String browsername, @Optional("false") boolean isLambdaTest,
			@Optional("false") boolean isHeadLess, @Optional("QA") String enviornmentname, ITestResult result) {

		this.isLambdaTest = isLambdaTest; // instance variable
		WebDriver lambdadriver;

		if (isLambdaTest) {

			lambdadriver = LambdaTestUtility.initializeLambdaTest(browsername, result.getMethod().getMethodName());
			homepage = new HomePage(lambdadriver);

		} else {
			// Runs on the local machine
			logger.info("Loads the HomePage of the website");
			homepage = new HomePage(Browser.valueOf(browsername.toUpperCase()),
					Env.valueOf(enviornmentname.toUpperCase()), isHeadLess);
		}

		isUserLoggedIn = false;
	}

	public BrowserUtility getInstance() {
		return homepage;
	}

	// Common login method that can be reused in multiple tests where login requires eg: Product purchase
	// Handles reusable login logic with assertion
	public MyAccountPage loginAsValidUser(User user) {
		
		this.user = user; //instance variable for user
		
		if (!isUserLoggedIn) 
		{
			logger.info("Login as Valid User " + user.getEmailAddress());
			//test.info("Login as a Valid user with email as " + user.getEmailAddress());
			myAccountPage = homepage.goToLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword());
			isUserLoggedIn = true;
		}
		
		return myAccountPage;
	}

	@AfterMethod(description = "Tear down the browser")
	public void tearDown() {
		if (isLambdaTest) {
			LambdaTestUtility.quitSession(); // quit the lambdatest browser session
		} else {
			homepage.quitBrowserSession(); // quit the local browser session
		}
	}
}
