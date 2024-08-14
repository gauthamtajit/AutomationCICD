package rahulshetty.SeleniumFrameworkTest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshetty.TestComponents.BaseTest;
import rahulshetty.pageobjects.CartPage;
import rahulshetty.pageobjects.CheckoutPage;
import rahulshetty.pageobjects.ConfirmationPage;
import rahulshetty.pageobjects.LandingPage;
import rahulshetty.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	@Test
	public void submitOrder() throws IOException {
		String productName="ZARA COAT 3";
		LandingPage landingPage=launchApplication();
		
		ProductCatalogue productCatalogue=landingPage.loginApplication("gauthamtajit@gmail.com", "Unit589vs!@#");
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.gotoCart();
		
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage=checkoutPage.SubmitOrder();
		String confirmMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
				 
	 }
}
 
	
