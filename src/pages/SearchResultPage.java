package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public List<WebElement> getAllSearchResults() {
		List<WebElement> results = this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
		return results;
	}

	public ArrayList<String> namesOfAllMeals() {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < this.getAllSearchResults().size(); i++) {
			String names = this.getAllSearchResults().get(i).getText();
			list.add(names);
		}
		return list;
	}
	
	public int NumberOfSeacrhResults () {
		int num=this.getAllSearchResults().size();
		return num;
	}

}
