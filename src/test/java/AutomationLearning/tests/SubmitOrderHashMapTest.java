package AutomationLearning.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationLearning.TestComponents.BaseTest;
import automationlearning.pageobjects.CartPage;
import automationlearning.pageobjects.CheckoutPage;
import automationlearning.pageobjects.ConfirmationPage;
import automationlearning.pageobjects.ProductCatalogue;

public class SubmitOrderHashMapTest extends BaseTest {

	//String productName="zara coat 3";
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException
	{
		
		 
		String countryName="India";
		ProductCatalogue productcatalogue= landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> productlist = productcatalogue.getProductList();
		// find the product
		productcatalogue.getProductByName(input.get("productName"));
		//Click Add to cart
		productcatalogue.addProductToCart(input.get("productName"));
		//Click Cart icon
		CartPage cartPage=productcatalogue.goToCartPage();
		//Cart page - List the cart products
		Boolean match = cartPage.verifyProductInCart(input.get("productName"));
		Assert.assertTrue(match);
		//Click on Checkout
		CheckoutPage checkoutpage=cartPage.goToCheckout();
		
		// Payment page - select country
		checkoutpage.selectCountry(countryName);
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		
		
		// confirmation page
		String confirmMessage = confirmationpage.verifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase( "Thankyou for the order."));
		System.out.println(confirmMessage);
	}

	/*@Test(dependsOnMethods= {"submitOrder"},dataProvider="getData",groups={"Purchase"})
	public void orderHistoryTest(String email,String password, String productName) 
	{
		ProductCatalogue productcatalogue= landingPage.loginApplication(email, password);
		OrdersPage ordersPage = productcatalogue.goToMyOrdersPage();
		Assert.assertTrue(ordersPage.verifyProductInOrderHistory(productName));
	}*/
	
	@DataProvider
	public Object[][] getData() 
	{
		HashMap<Object,Object> map= new HashMap<Object,Object>();
		map.put("email", "SanviNavin2612@gmail.com");
		map.put("password", "Sanu2612");
		map.put("productName", "zara coat 3");
		
		HashMap<Object,Object> map1= new HashMap<Object,Object>();
		map1.put("email", "sanvinavin0201@gmail.com");
		map1.put("password", "Sanu0201");
		map1.put("productName", "ADIDAS ORIGINAL");
		
		HashMap<Object,Object> map2= new HashMap<Object,Object>();
		map2.put("email", "sanvinavin1308@gmail.com");
		map2.put("password", "Sanu1308");
		map2.put("productName", "IPHONE 13 PRO");
		
	return new Object[][] {{map},{map1},{map2}};	
	}
}
