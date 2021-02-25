package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getAddtoCartButton() {
		return this.driver.findElement(By.xpath("//*[@class=\"price-feature--wrapper\"]/div[2]/a"));

	}

	public WebElement getAddtoFavouriteButton() {
		return this.driver.findElement(By.xpath("//*[@class=\"product-detail-image\"]/a"));

	}

	public WebElement getQuantity() {
		return this.driver.findElement(By.name("product_qty"));
	}

	public void addProductToCart(String name) {
		driver.findElement(By.linkText(name)).click();
		;
	}

	public void addtoCart(String quantity) {
		this.getQuantity().sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		this.getQuantity().sendKeys(quantity);
		this.getAddtoCartButton().click();
	}

}
