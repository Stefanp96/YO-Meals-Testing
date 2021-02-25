package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));

	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhoneNo() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public WebElement getUplodButton() {
		return this.driver.findElement(By.xpath("//*[@class=\"hover-elemnts\"]/a"));

	}

	public WebElement getUploadFile() {
		return this.driver.findElement(By.xpath("//input[@type=\"file\"]"));
	}

	public WebElement getRemoveButton() {
		return this.driver.findElement(By.xpath("//*[@class=\"hover-elemnts\"]/a[2]"));
	}

	public void uploadProfilePicutre(String imgPath) {
		this.js.executeScript("arguments[0].click()", this.getUplodButton());
		this.getUploadFile().sendKeys(imgPath);

	}

	public void deleteProfilePicture() {
		this.js.executeScript("arguments[0].click()", this.getRemoveButton());

	}

	public WebElement getSaveButton() {
		return this.driver.findElement(By.xpath("//*[@type=\"submit\"]"));

	}

	public Select getCounty() {
		WebElement Selectbutton = this.driver.findElement(By.id("user_country_id"));
		Select country = new Select(Selectbutton);
		return country;
	}

	public Select getState() {
		WebElement Selectbutton = this.driver.findElement(By.id("user_state_id"));
		Select state = new Select(Selectbutton);
		return state;

	}

	public Select getCity() {
		WebElement Selectbutton = driver.findElement(By.id("user_city"));
		Select city = new Select(Selectbutton);
		return city;
	}

	public void clearProfileInfo() {
		this.getFirstName().clear();
		this.getLastName().clear();
		this.getPhoneNo().clear();
		this.getZipCode().clear();

	}

	public void changeProfile(String firstName, String lastName, String address, String phoneNo, String zipCode,
			String countyValue, String stateValue, String cityValue) throws InterruptedException {
		this.getFirstName().sendKeys(firstName);
		this.getLastName().sendKeys(lastName);
		this.getAddress().sendKeys(address);
		this.getPhoneNo().sendKeys(phoneNo);
		this.getZipCode().sendKeys(zipCode);
		this.getCounty().selectByValue(countyValue);
		Thread.sleep(2000);
		this.getState().selectByValue(stateValue);
		Thread.sleep(2000);
		this.getCity().selectByValue(cityValue);

	}

}
