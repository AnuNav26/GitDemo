package automationlearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationlearning.abstractcomponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;
	public CheckoutPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub

	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By searchresults = By.cssSelector(".ta-results");
	
	@FindBy(xpath="//button[contains(@class, 'ta-item')][2]")
	WebElement selectcountry;
	
	
	public void selectCountry(String countryName) 
	{
		Actions a=new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		//Wait until search results displayed
		waitForElementToAppear(searchresults);
		//Select India 
		selectcountry.click();
	}
	
	public ConfirmationPage submitOrder() 
	{
		//place order
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);
		return new ConfirmationPage(driver);
			
	}
}
