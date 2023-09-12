package AutomationLearning.tests;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationlearning.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String prodName="zara coat 3"; 
		//Open chrome browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Maximize
		driver.manage().window().maximize();
		//Launch URL - Login page
		LandingPage landingPage= new LandingPage(driver);
		driver.get("https://rahulshettyacademy.com/client");
		//Enter credentials
		driver.findElement(By.id("userEmail")).sendKeys("SanviNavin2612@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sanu2612");
		driver.findElement(By.id("login")).click();
		// Products list page and wait until products are listed
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		// find the product
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(prodName)).findFirst().orElse(null);
		//Click Add to cart
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//Wait until loading symbol disappears and the toast message is received
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//Click on cart icon in header
		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
		
		//Cart page - List the cart products
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		//Verify the product added to cart
		Boolean match = cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(prodName));
		Assert.assertTrue(match);
		//Click on Checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		// Payment page - select country
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		//Wait until search results displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		//Select India 
		//.ta-item:nth-of-type(2)
		driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();
		//place order
		
		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);
		
		
		// confirmation page
		String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase( "Thankyou for the order."));
		System.out.println(confirmMessage);
		driver.close();
	}

}
