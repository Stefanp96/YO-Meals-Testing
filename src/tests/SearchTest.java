package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest {

	@Test(priority = 0)
	public void searchResultsTest() throws IOException {
		SoftAssert sa = new SoftAssert();

		this.driver.get(this.baseURL + "/meals");
		this.locationPopupPage.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");

		for (int i = 1; i <= 7; i++) {
			XSSFRow row = sheet.getRow(i);

			String location = row.getCell(0).getStringCellValue();
			String URL = row.getCell(1).getStringCellValue();
			int numOfResults = (int) row.getCell(2).getNumericCellValue();

			this.driver.navigate().to(URL);
			this.locationPopupPage.openLocation();
			this.locationPopupPage.setLocation(location);

			sa.assertEquals(this.searchResultPage.NumberOfSeacrhResults(), numOfResults,
					"[ERROR] results fail to match");

			for (int j = 3; j < row.getLastCellNum(); j++) {

				String proudcts = row.getCell(j).getStringCellValue();
				sa.assertTrue(this.searchResultPage.namesOfAllMeals().contains(proudcts), "[ERROR]");

			}

		}
		sa.assertAll();
		wb.close();
		fis.close();
	}

}
