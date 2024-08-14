package rahulshetty.SeleniumFrameworkTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshetty.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingpage= new LandingPage(driver); 
		driver.findElement(By.id("userEmail")).sendKeys("gauthamtajit@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Unit589vs!@#");
		driver.findElement(By.xpath("//input[@name='login']")).click();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		// Find all product elements (assuming they have a common class name)
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		
		// Iterate through the list of products using a simple for loop
		for(int i=0;i<products.size();i++) {
			WebElement product=products.get(i);
			
			// Find the product name element (assuming it is inside the product element)
			WebElement productNameElement=product.findElement(By.xpath("//b[text()='ZARA COAT 3']"));
			String productName=productNameElement.getText();
			
			// Check if the product name matches the desired product names
			 if ("ZARA COAT 3".equals(productName)) {
				 WebElement addToCartButton=product.findElement(By.xpath("//*[@id=\"products\"]/div[1]/div[2]/div[1]/div/div/button[2]"));
				 addToCartButton.click();
				 System.out.println("Added to cart: " + productName);
				 }
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
			 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
			 
			 driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
			 
			 List<WebElement> cartProducts=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
			 
			 Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
			 Assert.assertTrue(match);
			 
			 driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
			 
			 driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("ta-results list-group ng-star-inserted")));
			// driver.findElement(By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted'])[2]")).click();
			 
				 
			 }
		}
 
	}
