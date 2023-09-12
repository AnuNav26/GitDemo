package automationlearning.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationlearning.pageobjects.CartPage;
import automationlearning.pageobjects.OrdersPage;

public class AbstractComponents {

	WebDriver driver;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findEle)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findEle));
	
	}
	
	public void waitForWebElementToAppear(WebElement findEle)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findEle));
	
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCartPage() 
	{
		waitForWebElementToAppear(cartHeader);
		cartHeader.click();
		return new CartPage(driver);
	}
	
	public OrdersPage goToMyOrdersPage() 
	{
		ordersHeader.click();
		return new OrdersPage(driver);
	}
}
