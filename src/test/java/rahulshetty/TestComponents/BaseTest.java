package rahulshetty.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshetty.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public WebDriver initializeDriver() throws IOException {
		//properties class
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshetty\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) 
		{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			
			//Fire-fox code
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20 ));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	public LandingPage launchApplication() throws IOException {
		
			driver= initializeDriver();
			LandingPage landingPage= new LandingPage(driver); 
			landingPage.goTo();
			return landingPage;
		}
		
	}

