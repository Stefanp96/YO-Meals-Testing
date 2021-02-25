package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

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
		this.loginPage.login(this.email, this.password);

		this.driver.get(this.baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		this.mealPage.addProductToFavorite();

		Assert.assertTrue(
				this.notificationSistemPage.getMessageText().contains("Product has been added to your favorites"),
				"[ERROR]:Add Product of Favorite fail");

	}

}