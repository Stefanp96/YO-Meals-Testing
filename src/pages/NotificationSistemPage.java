package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSistemPage extends BasicPage{

	public NotificationSistemPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	public WebElement getMessageElement() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'alert--success') or contains(@class, "
				+ "'alert--danger')][contains(@style,'display: block')]"));
	}

	public String getMessageText() {
		return this.getMessageElement().getText();
	}

	public void waitForNotToDissaper() {

		waiter.until(ExpectedConditions.attributeContains(By.xpath("//*[contains(@class, 'system_message')]"), "style",
				"display: none;"));
	}

}
