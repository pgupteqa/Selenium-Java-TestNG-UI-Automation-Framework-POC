package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.constants.Env;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;
import com.utility.PropertiesUtil;

@Listeners({com.ui.listeners.TestListener.class})
public class SearchProductTest extends TestBase{
	
	Logger logger=LoggerUtility.getLogger(this.getClass());
	private MyAccountPage myAccountPage;
	private static final String PRODUCT_SEARCH_TERM = PropertiesUtil.readProperty(Env.DEV,"PRODUCTSEARCHTERM");
	
	
	@Test(description="Verify if the logged in user is able to search for the correct product", groups= {"sanity","regression","smoke"}, dataProviderClass=com.ui.dataproviders.MultiDataProvider.class, 
			dataProvider="ValidLoginDataProvider")
	public void verifyProductSearch(User user)
	{	
		myAccountPage=loginAsValidUser(user);
		boolean status =myAccountPage.searchForProduct(PRODUCT_SEARCH_TERM).isSearchProductPresentInProductList(PRODUCT_SEARCH_TERM);
		Assert.assertEquals(status, true);
		
	}

}
