package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	@Test(priority = 0)
	public void addMealtoCart() {
		this.driver.get(this.baseURL + "/meals");
		this.locationPopupPage.closePopUp();

		this.mealPage.addProductToCart("LOBSTER SHRIMP...");

		this.mealPage.addtoCart("3");

		Assert.assertTrue(this.notificationSistemPage.getMessageText().contains("The Following Errors Occurred:"),
				"[ERROR] message fail to occur");
		Assert.assertTrue(this.notificationSistemPage.getMessageText().contains("Please Select Location"),
				"[ERROR]: message fail to occur");

		this.notificationSistemPage.waitForNotToDissaper();

		this.locationPopupPage.openLocation();
		this.locationPopupPage.setLocation("City Center - Albany");

		this.mealPage.addtoCart("3");

		Assert.assertTrue(this.notificationSistemPage.getMessageText().contains("Meal Added To Cart"),
				"[ERROR]:Meal Add to Cart fail");

	}

	@Test(priority = 5)
	public void addMealtoFavorite() {
		this.driver.get(this.baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		this.locationPopupPage.closePopUp();
		this.mealPage.addProductToFavorite();

		Assert.assertTrue(this.notificationSistemPage.getMessageText().contains("Please login first!"),
				"[ERROR]: Login notification fail to occur ");

		this.driver.navigate().to(this.baseURL + "/guest-user/login-form");
		this.loginPage.clearEmailAndPassword();
		this.loginPage.login(this.email, this.password);

		this.driver.get(this.baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		this.mealPage.addProductToFavorite();

		Assert.assertTrue(
				this.notificationSistemPage.getMessageText().contains("Product has been added to your favorites"),
				"[ERROR]:Add Product of Favorite fail");

	}

	@Test(priority = 10)
	public void clearCartTesta() throws IOException, InterruptedException {

		SoftAssert sa = new SoftAssert();

		driver.get(this.baseURL + "/meals");
		Thread.sleep(2000);
		this.locationPopupPage.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			String mealsUrl = row.getCell(0).getStringCellValue();
			int mealsQuantity = (int) row.getCell(1).getNumericCellValue();

			this.driver.navigate().to(mealsUrl);
			this.mealPage.addtoCart(String.valueOf(mealsQuantity));

			sa.assertTrue(this.notificationSistemPage.getMessageText().contains("Meal Added To Cart"),
					"[ERROR]: Meals Add To Cart fail");

		}
		
		sa.assertAll();
		
		this.mealPage.clearAll();
		Assert.assertTrue(this.notificationSistemPage.getMessageText().contains("All meals removed from Cart Successfully"),
				"[ERROR]: Meals Remove From Cart fail");
		
		wb.close();
		fis.close();

	}

}