package com.ui.tests;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.constants.Env;
import com.constants.Size;
import com.ui.pages.DeliveryAddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pages.PaymentPage;
import com.ui.pages.ProductDetailPage;
import com.ui.pages.SearchPage;
import com.ui.pages.ShippingAddressPage;
import com.ui.pages.ShoppingCartDetailPage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;
import com.utility.PropertiesUtil;

public class EndToEndProductCheckoutTest extends TestBase {
	
	private static final String PRODUCT_SEARCH_TERM = PropertiesUtil.readProperty(Env.DEV,"PRODUCTSEARCHTERM");
	private MyAccountPage myAccountPage;
	private SearchPage searchPage;
	private ProductDetailPage productDetailPage;
	private ShoppingCartDetailPage shopCartPage;
	private DeliveryAddressPage deliveryAddressPage;
	private ShippingAddressPage shippingAddressPage;
	private PaymentPage paymentPage;
	private static final String PAYMENT_SUCCESS_MESSAGE_TEXT = "Your order on My Shop is complete.";
	private Logger logger=LoggerUtility.getLogger(this.getClass());
	

	//retryAnalyzer = com.ui.listeners.RetryAnalyzer.class
	@Test(description="User selects the product and proceed to checkout", groups= {"smoke", "sanity", "regression"}, dataProviderClass=com.ui.dataproviders.MultiDataProvider.class, 
			dataProvider="ValidLoginDataProvider")
	public void endToEndProductcheckoutTest(User user)
	{
		myAccountPage = loginAsValidUser(user);
	    
		searchPage = myAccountPage.searchForProduct(PRODUCT_SEARCH_TERM);
		Assert.assertTrue(searchPage.isSearchProductPresentInProductList(PRODUCT_SEARCH_TERM),"Product '" + PRODUCT_SEARCH_TERM + "' is not present in the search results");
	    
	    productDetailPage = searchPage.clickOnTheProductTitle(0);
	    
	    shopCartPage = productDetailPage.changeSize(Size.M).addProductToTheCart().proceedToCheckout();
	    
	    deliveryAddressPage = shopCartPage.goToDeliveryAddressPage();
	    shippingAddressPage = deliveryAddressPage.goToShippingAddressPage();
	    paymentPage = shippingAddressPage.goToPaymentPage();
	    
	    String payment_success_msg = paymentPage.makePaymentByWire();
	    Assert.assertEquals(payment_success_msg,PAYMENT_SUCCESS_MESSAGE_TEXT );
	    
	    
	}

}
