package automationlearning.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationlearning.abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents
	{
	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartproducts;
	
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public CartPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	 public Boolean verifyProductInCart(String productName)
	 {
		 Boolean match = cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	 }
	 
	 public CheckoutPage goToCheckout() {
		 checkoutEle.click();
		 return new CheckoutPage(driver);
	 }
	
	}
