package automationlearning.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationlearning.abstractcomponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {

	WebDriver driver;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedproducts;
	
	public OrdersPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifyProductInOrderHistory(String productName)
	 {
		 Boolean match = orderedproducts.stream().anyMatch(orderedproduct-> orderedproduct.getText().equalsIgnoreCase(productName));
		return match;
	 }

}
