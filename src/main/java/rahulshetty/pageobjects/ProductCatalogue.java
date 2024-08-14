package rahulshetty.pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	public ProductCatalogue(WebDriver driver){
		super(driver);
		//initialization
		this.driver=driver;	
		PageFactory.initElements(driver, this); }
	
	//List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	//PageFactory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy=By.cssSelector(".mb-3");
	By addtoCart=By.xpath("//*[@id=\"products\"]/div[1]/div[2]/div[1]/div/div/button[2]");
	By toastMessage=By.id("container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod= getProductList().stream().filter(product->product.findElement(By.xpath("//b[text()='ZARA COAT 3']")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addtoCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisppear(spinner); 
	}
	
	
	

}
