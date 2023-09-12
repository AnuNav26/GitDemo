package automationlearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationlearning.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail= driver.findElement(By.id("userEmail"));
	
	//PageFactory
	//driver.findElement(By.id("userEmail"))
	@FindBy(id="userEmail")
	WebElement userEmails;
	//driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement passwordEle;
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement submit;
	//<div class="ng-tns-c4-5 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error" 
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	//Action Methods
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmails.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}
	
	public void goToLandingPage()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
