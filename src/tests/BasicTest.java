package tests;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;
import pages.SearchResultPage;

public abstract class BasicTest {

	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	protected String baseURL;
	protected String email;
	protected String password;
	protected LocationPopupPage locationPopupPage;
	protected ProfilePage profilePage;
	protected LoginPage loginPage;
	protected AuthPage authPage;
	protected NotificationSistemPage notificationSistemPage;
	protected MealPage mealPage;
	protected SearchResultPage searchResultPage;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\ec\\Test\\driver\\chromedriver.exe");

		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.waiter = new WebDriverWait(driver, 10);
		this.js = (JavascriptExecutor) driver;

		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		this.baseURL = "http://demo.yo-meals.com";
		this.email = "customer@dummyid.com";
		this.password = "12345678a";

		this.locationPopupPage = new LocationPopupPage(driver, waiter, js);
		this.profilePage = new ProfilePage(driver, waiter, js);
		this.loginPage = new LoginPage(driver, waiter, js);
		this.authPage = new AuthPage(driver, waiter, js);
		this.notificationSistemPage = new NotificationSistemPage(driver, waiter, js);
		this.mealPage = new MealPage(driver, waiter, js);
		this.searchResultPage = new SearchResultPage(driver, waiter, js);
	}

	@AfterMethod
	public void takeScreenShot(ITestResult result) throws Exception {
		String testTime = new SimpleDateFormat("yyyyMMddHHmmss'.png'").format(new Date());
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File file = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File("screenshot/" + testTime));
		}

		this.driver.manage().deleteAllCookies();
		this.driver.navigate().refresh();
	}

	@AfterClass
	public void clear() {
		this.driver.close();
	}

}
