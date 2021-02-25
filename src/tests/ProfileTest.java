package tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test(priority = 0)
	public void editProfile() throws InterruptedException {

		this.driver.get(this.baseURL + "/guest-user/login-form");
		this.locationPopupPage.closePopUp();
		this.loginPage.clearEmailAndPassword();
		this.loginPage.login(this.email, this.password);

		Assert.assertTrue(this.notificationSistemPage.getMessageText().contains("Login Successfull"),
				"[ERROR]: login fail");

		this.driver.get(this.baseURL + "/member/profile");

		this.profilePage.clearProfileInfo();
		this.profilePage.changeProfile("Stefan", "Pavlovic", "Nis", "234", "324", "231", "3920", "42673");
		this.profilePage.getSaveButton().click();

		driver.findElement(By.xpath("//*[@class=\"content\"]")).getText();

		Assert.assertTrue(this.notificationSistemPage.getMessageText().contains("Setup Successful"),
				"[ERROR]: update of profile inforamtion fail");

		this.authPage.logOut();

		Assert.assertTrue(this.notificationSistemPage.getMessageText().contains("Logout Successfull!"),
				"[ERROR]: logout fail");

	}

	@Test(priority = 5)
	public void profileImageTest() throws IOException {
		this.driver.get(this.baseURL + "/guest-user/login-form");
		this.locationPopupPage.closePopUp();
		this.loginPage.clearEmailAndPassword();
		this.loginPage.login(this.email, this.password);

		driver.get(this.baseURL + "/member/profile");

		String imgPath = new File("img/ProfilePicture.jpg").getCanonicalPath();

		this.profilePage.uploadProfilePicutre(imgPath);

		Assert.assertEquals(this.notificationSistemPage.getMessageText(), "Profile Image Uploaded Successfully",
				"[ERROR]: image Upload fail ");
		this.profilePage.deleteProfilePicture();

		Assert.assertEquals(this.notificationSistemPage.getMessageText(), "Profile Image Deleted Successfully",
				"[ERROR]: image Remove Fail");

		this.notificationSistemPage.waitForNotToDissaper();

		this.authPage.logOut();

		Assert.assertTrue(this.notificationSistemPage.getMessageText().contains("Logout Successfull!"),
				"[ERROR]: logout fail");

	}

}
