package AutomationLearning.tests;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import automationlearning.pageobjects.CartPage;
import automationlearning.pageobjects.CheckoutPage;
import automationlearning.pageobjects.ConfirmationPage;
import automationlearning.pageobjects.LandingPage;
import automationlearning.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName="zara coat 3"; 
		String countryName="India";
		//Open chrome browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Maximize
		driver.manage().window().maximize();
		//Launch URL - Login page
		
		//Enter credentials
		LandingPage landingPage= new LandingPage(driver);
		landingPage.goToLandingPage();
		landingPage.loginApplication("SanviNavin2612@gmail.com", "Sanu2612");
		
		// Products list page and wait until products are listed
		ProductCatalogue productcatalogue=new ProductCatalogue(driver);
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
		driver.close();
	}

}
