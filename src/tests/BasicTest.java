package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;

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
		this.notificationSistemPage= new NotificationSistemPage(driver, waiter, js);
	}
}
