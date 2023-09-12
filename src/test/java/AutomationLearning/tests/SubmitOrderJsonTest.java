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

public class SubmitOrderJsonTest extends BaseTest {

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
		System.out.println("The change");
		System.out.println("The change2");
		System.out.println("The change3");
		
		
		//change from y
		
	}

	/*@Test(dependsOnMethods= {"submitOrder"},dataProvider="getData",groups={"Purchase"})
	public void orderHistoryTest(String email,String password, String productName) 
	{
		ProductCatalogue productcatalogue= landingPage.loginApplication(email, password);
		OrdersPage ordersPage = productcatalogue.goToMyOrdersPage();
		Assert.assertTrue(ordersPage.verifyProductInOrderHistory(productName));
	}*/
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//AutomationLearning//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};	
	}
}
