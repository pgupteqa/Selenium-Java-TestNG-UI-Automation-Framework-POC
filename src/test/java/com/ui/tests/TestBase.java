package com.ui.tests;

import static com.constants.Browser.CHROME;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.constants.Env;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {
	
	protected HomePage homepage;
	Logger logger=LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;
	
	@Parameters({"browsername","isLambdaTest","isHeadLess", "enviornmentname"})
	@BeforeMethod(description = "Load the homepage of the website")
	public void setup(@Optional("chrome") String browsername, @Optional("false") boolean isLambdaTest, @Optional("true") boolean isHeadLess, 
			@Optional("QA") String enviornmentname, ITestResult result) {
	
		this.isLambdaTest = isLambdaTest; // instance variable
		WebDriver lambdadriver;
		
		if (isLambdaTest) {
			
			lambdadriver = LambdaTestUtility.initializeLambdaTest(browsername, result.getMethod().getMethodName());
			homepage = new HomePage(lambdadriver);

		} else {
			//Runs on the local machine
			logger.info("Loads the HomePage of the website");
			homepage = new HomePage(Browser.valueOf(browsername.toUpperCase()),Env.valueOf(enviornmentname.toUpperCase()),isHeadLess);
		}
	}

	public BrowserUtility getInstance() {
		return homepage;
	}
	
	@AfterMethod(description = "Tear down the browser")
	public void tearDown()
	{
		if(isLambdaTest) {
			LambdaTestUtility.quitSession(); // quit the lambdatest browser session
		}
		else
		{
			homepage.quitBrowserSession(); //quit the local browser session
		}
	}
}
