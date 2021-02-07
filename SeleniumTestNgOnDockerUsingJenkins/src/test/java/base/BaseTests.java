package base;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pages.GooglePage;

public class BaseTests {

	public WebDriver driver;

	private static ExtentHtmlReporter htmlReport;
	private static ExtentReports extent;
	protected static GooglePage googlePage;

	@BeforeMethod
	public void setUp(Method method) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName(BrowserType.CHROME);
		cap.setCapability("name", "Google_" + method.getName());

		cap.setCapability("name", "tester");
		// WebDriverManager.chromedriver().setup();
		driver = new RemoteWebDriver(new URL("http://192.168.1.11:4444/wd/hub"), cap);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to("https://www.google.com/");
		googlePage = new GooglePage(driver);
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
