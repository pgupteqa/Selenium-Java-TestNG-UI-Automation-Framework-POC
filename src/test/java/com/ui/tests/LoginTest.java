package com.ui.tests;

import static org.testng.Assert.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.pojo.User;


@Listeners({ com.ui.listeners.TestListener.class })
public class LoginTest extends TestBase {

	
	@Test(description = "Verify the Login using valid user", groups = { "login", "sanity", "smoke",
			"regression" }, dataProviderClass = com.ui.dataproviders.MultiDataProvider.class, dataProvider = "LoginDataProvider", retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
	public void loginTest(User user) {

		assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUsername(),
				"Prateek Guptey");

	}

	/*
	 * @Test(description="Verify the Login using valid user using CSV", groups=
	 * {"login","sanity","smoke"},
	 * dataProviderClass=com.ui.dataproviders.MultiDataProvider.class,
	 * dataProvider="LoginTestCSVDataProvider") public void loginCSVTest(User user)
	 * {
	 * 
	 * assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(),
	 * user.getPassword()).getUsername(), "Prateek Guptey"); }
	 * 
	 * @Test(description="Verify the Login using valid user using CSV", groups=
	 * {"login","sanity","smoke"},
	 * dataProviderClass=com.ui.dataproviders.MultiDataProvider.class,
	 * dataProvider="LoginTestExcelDataProvider", retryAnalyzer =
	 * com.ui.listeners.RetryAnalyzer.class) public void loginExcelTest(User user) {
	 * assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(),
	 * user.getPassword()).getUsername(), "Prateek Guptey"); }
	 */

}
