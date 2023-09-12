package automationlearning.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationlearning.abstractcomponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	WebDriver driver;
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	public ConfirmationPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub

	}

	public String verifyConfirmationMessage() 
	{
		return confirmMessage.getText();
	}
}
