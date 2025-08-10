package com.ui.tests;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.pojo.InvalidUsers;


@Listeners({ com.ui.listeners.TestListener.class })
public class InvalidLoginTest extends TestBase {

	
	@Test(description = "Verify the Login using valid user", groups = {"login","sanity","smoke","regression"}, 
			dataProviderClass = com.ui.dataproviders.MultiDataProvider.class, dataProvider = "invalidLoginDataProvider")
	public void inValidLoginTest(InvalidUsers invaliduser) {

		assertEquals(homepage.goToLoginPage().doLoginWithInvalidCredentials(invaliduser.getEmailAddress(), invaliduser.getPassword())
				.getErrorMessage(), "Authentication failed.");
		
	}

}
