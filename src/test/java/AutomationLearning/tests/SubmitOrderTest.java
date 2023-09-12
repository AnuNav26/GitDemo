package AutomationLearning.tests;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationLearning.TestComponents.BaseTest;
import automationlearning.pageobjects.CartPage;
import automationlearning.pageobjects.CheckoutPage;
import automationlearning.pageobjects.ConfirmationPage;
import automationlearning.pageobjects.OrdersPage;
import automationlearning.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName="zara coat 3";
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(String email, String password, String productName) throws IOException
	{
		
		 
		String countryName="India";
		ProductCatalogue productcatalogue= landingPage.loginApplication(email, password);
		List<WebElement> productlist = productcatalogue.getProductList();
		// find the product
		productcatalogue.getProductByName(productName);
		//Click Add to cart
		productcatalogue.addProductToCart(productName);
		//Click Cart icon
		CartPage cartPage=productcatalogue.goToCartPage();
		//Cart page - List the cart products
		Boolean match = cartPage.verifyProductInCart(productName);
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

	@Test(dependsOnMethods= {"submitOrder"},dataProvider="getData",groups={"Purchase"})
	public void orderHistoryTest(String email,String password, String productName) 
	{
		ProductCatalogue productcatalogue= landingPage.loginApplication(email, password);
		OrdersPage ordersPage = productcatalogue.goToMyOrdersPage();
		Assert.assertTrue(ordersPage.verifyProductInOrderHistory(productName));
	}
	
	@DataProvider
	public Object[][] getData() 
	{
	return new Object[][] {{"SanviNavin2612@gmail.com","Sanu2612","zara coat 3"},{"sanvinavin0201@gmail.com","Sanu0201","ADIDAS ORIGINAL"},{"sanvinavin1308@gmail.com","Sanu1308","IPHONE 13 PRO"}};	
	}
}
