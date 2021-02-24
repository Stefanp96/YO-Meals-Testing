package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	public WebElement getEmail() {
		return driver.findElement(By.xpath("//*[@placeholder=\"Email Address\"]"));
	}

	public WebElement getPassword() {
		return driver.findElement(By.xpath("//*[@placeholder=\"Password\"]"));
	}

	public void clearEmailAndPassword() {
		this.getEmail().clear();
		this.getPassword().clear();
	}

	public void login(String email, String password) {
		this.getEmail().sendKeys(email);
		this.getPassword().sendKeys(password);
		driver.findElement(By.xpath("//*[@title=\"Login\"]")).click();

	}

}
