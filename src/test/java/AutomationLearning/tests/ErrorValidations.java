package AutomationLearning.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationLearning.TestComponents.BaseTest;
import AutomationLearning.TestComponents.Retry;
import automationlearning.pageobjects.CartPage;
import automationlearning.pageobjects.ProductCatalogue;



public class ErrorValidations extends BaseTest {

	@Test(groups="ErrorHandling",retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException
	{
		// TODO Auto-generated method stub
		
		landingPage.loginApplication("SanviNavin2612@gmail.com", "Sanu26123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		//Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		//("Incorrect email or password.", landingPage.getErrorMessage());
		System.out.println("The change4");
		
	}
	
	@Test
	public void gitTest()
	{
		
		System.out.println("The changejs");
		
	}
	
	@Test
	public void productErrorValidation() 
	{
		String productName="zara coat 3"; 
		ProductCatalogue productcatalogue= landingPage.loginApplication("sanvinavin0201@gmail.com", "Sanu0201");
		List<WebElement> productlist = productcatalogue.getProductList();
		productcatalogue.getProductByName(productName);
		productcatalogue.addProductToCart(productName);
		CartPage cartPage=productcatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductInCart("zara coat 33");
		Assert.assertFalse(match);
	}
}
