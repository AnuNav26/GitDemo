package AutomationLearning.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import AutomationLearning.TestComponents.BaseTest;
import automationlearning.pageobjects.CartPage;
import automationlearning.pageobjects.CheckoutPage;
import automationlearning.pageobjects.ConfirmationPage;
import automationlearning.pageobjects.LandingPage;
import automationlearning.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productcatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutpage;
	public ConfirmationPage confirmationpage;
	
	@Given("I landed on ecommerce page")
	public void land_on_ecommerce_page() throws IOException
	{
		landingPage= launchApplication();
	}
	
	@Given("^logged in with username (.+) and password (.+)$")
	public void loggedin_username_password(String username, String password) 
	{
	productcatalogue= landingPage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName)
	{
		List<WebElement> productlist = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		cartPage=productcatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductInCart(productName);
		Assert.assertTrue(match);
		checkoutpage=cartPage.goToCheckout();
		checkoutpage.selectCountry("India");
		confirmationpage=checkoutpage.submitOrder();
	}
	
	@Then("{string} message is displayed in confirmation page")
	public void message_displayed_confirmationPage(String string) 
	{
		String confirmMessage = confirmationpage.verifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	 @Then("{string} error message is displayed")
	 public void error_message_displayed(String errorMessage) 
		{
		 Assert.assertEquals(errorMessage, landingPage.getErrorMessage());
		 driver.close();
		}
}
