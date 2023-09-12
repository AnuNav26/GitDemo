package automationlearning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationlearning.abstractcomponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	//PageFactory
	//driver.findElement(By.id("userEmail"))
	
	//driver.findElement(By.cssSelector(".ng-animating"))
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement loading;
	
	
	By productBy = By.cssSelector(".mb-3");
	By addProduct = By.cssSelector(".card-body button:last-of-type");
	By toastMessage= By.cssSelector("#toast-container");

	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addProduct).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(loading);
		
	}
}
