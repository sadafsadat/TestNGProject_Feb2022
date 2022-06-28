package variousConcepts;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class learnTestNG {

	WebDriver driver;
	String browser;

	// Element list
	By USER_NAME_FIELD = By.xpath("//*[@id=\"username\"]");
	By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]");
	By LOGIN_BUTTON_FIELD = By.xpath("/html/body/div/div/div/form/div[3]/button");
	By DASHBOARD_HEADER_FIELD = By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2");

	// Test Data
	String userName = "demo@techfios.com";
	String password = "abc123";
	String dashboardHeader = "Dashboard";
	private String url;

	public void readConfig() {

		// InputStream //BufferedRead //Scanner //FileReader - Four classes Java offers
		// us to read any file, we can utilizer any of these

		try {

			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void init() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();

		// if you had two browsers and wanted to run it in either, or:
		// if(browser.equalsIgnoreCase("Chrome") {
		// system.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		// driver = new ChromeDriver();
		// }else if (browser.equalsIgnoreCase("Firefox")) {
		// System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		// driver = new FirefoxDriver();
		// }

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.techfios.com/billing/?ng=admin/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void loginTest() {

		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(LOGIN_BUTTON_FIELD).click();
		Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(), dashboardHeader,
				"Dashboard is not available.");
	}

	@AfterMethod
	public void tearDown() {
		driver.close();

	}
}
