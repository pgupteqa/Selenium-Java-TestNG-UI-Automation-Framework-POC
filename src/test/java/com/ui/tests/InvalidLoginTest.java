package com.ui.tests;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojo.InvalidUsers;
import com.ui.pojo.User;
import com.utility.LoggerUtility;

@Listeners({ com.ui.listeners.TestListener.class })
public class InvalidLoginTest extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	@Test(description = "Verify the Login using valid user", groups = { "login", "sanity","smoke" }, 
			dataProviderClass = com.ui.dataproviders.MultiDataProvider.class, dataProvider = "invalidLoginDataProvider")
	public void loginTest(InvalidUsers user) {

		assertEquals(homepage.goToLoginPage().doLoginWithInvalidCredentials(user.getEmailAddress(), user.getPassword())
				.getErrorMessage(), "Authentication failed.");
	}

}
