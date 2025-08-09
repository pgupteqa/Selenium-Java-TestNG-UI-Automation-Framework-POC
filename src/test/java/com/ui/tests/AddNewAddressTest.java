package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPOJO;
import com.ui.pojo.User;
import com.utility.FakeAddressUtility;

public class AddNewAddressTest extends TestBase {
	
	private MyAccountPage myAccountPage;
	private AddressPOJO address;
	
	@Test(description="Verify if the logged in user is able to add the new address", groups= {"addNewAddress","smoke","regression"}, dataProviderClass=com.ui.dataproviders.MultiDataProvider.class, 
			dataProvider="ValidLoginDataProvider")
	public void addNewAddressTest(User user)
	{
		myAccountPage = loginAsValidUser(user);
		address=FakeAddressUtility.getFakeAddressData();
		Assert.assertTrue(myAccountPage.validateMyFirstAddressOption(),"The Add New Address Option is not visible.");
		String newAddress=myAccountPage.goToAddressPage().saveAddress(address);
		Assert.assertEquals(newAddress, address.getAddressAlias().toUpperCase());
	}
	
	
	

}
