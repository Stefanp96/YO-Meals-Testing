package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	public WebElement getMyAccount() {
		return driver.findElement(By.xpath("//*[@class=\"my-account-dropdown\"]/ul/li"));
	}
	public WebElement getLogout() {
		return driver.findElement(By.xpath("//*[@class=\"my-account-dropdown\"]/ul/li[2]"));
	}
	
	public void logOut () {
		this.driver.findElement(By.xpath("//*[@class=\"filled \"]/a")).click();
		this.getLogout().click();
	}

}
