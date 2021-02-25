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
		
		Assert.assertTrue(this.notificationSistemPage.getMessageText().contains("Meal Added To Cart"),"[ERROR]:Meal Add to Cart fail");
		
		

	}

}