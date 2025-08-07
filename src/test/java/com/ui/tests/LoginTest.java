package com.ui.tests;

import static com.constants.Browser.*;

import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.Registration;
import com.ui.pojo.User;
import com.utility.LoggerUtility;

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends TestBase {

	
	Logger logger=LoggerUtility.getLogger(this.getClass());
	
	@Test(description="Verify the Login using valid user", groups= {"login","sanity","smoke"}, dataProviderClass=com.ui.dataproviders.MultiDataProvider.class, 
			dataProvider="LoginDataProvider", retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
	public void loginTest(User user) 
	{	
		//loginAsValidUser("Prateek Guptey", user);
		assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUsername(),
		"Prateek Guptey");
		
	}
	
	/*@Test(description="Verify the Login using valid user using CSV", groups= {"login","sanity","smoke"}, dataProviderClass=com.ui.dataproviders.MultiDataProvider.class, 
			dataProvider="LoginTestCSVDataProvider")
	public void loginCSVTest(User user) 
	{	
		
		assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUsername(),
		"Prateek Guptey");
	}
	
	@Test(description="Verify the Login using valid user using CSV", groups= {"login","sanity","smoke"}, dataProviderClass=com.ui.dataproviders.MultiDataProvider.class, 
			dataProvider="LoginTestExcelDataProvider", retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
	public void loginExcelTest(User user) 
	{	
		assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUsername(),
		"Prateek Guptey");
	}
	
	/*@Test(description="Verify the Login using valid user", groups= {"login","sanity","smoke"}, dataProviderClass=com.ui.dataproviders.MultiDataProvider.class, 
			dataProvider="registrationDataProvider")
	public void loginTest2(Registration reg)
	{	
		
		assertEquals(homepage.goToLoginPage().doLoginWith(reg.getRegisteredEmailAddress(),reg.getRegisteredPassword()).getUsername(),
		"Prateek Guptey1");
	}*/

}
