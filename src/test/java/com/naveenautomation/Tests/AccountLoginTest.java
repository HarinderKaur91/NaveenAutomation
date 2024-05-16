package com.naveenautomation.Tests;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.AccountLoginPage;
import com.naveenautomation.Pages.HomePage;
import com.naveenautomation.Pages.MyAccountPage;
import com.naveenautomation.Utils.ExcelUtils;

public class AccountLoginTest extends TestBase {
	HomePage homePage;
	MyAccountPage myAccountPage;
	SoftAssert softAssert;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		homePage = new HomePage(driver,true).get();
		softAssert = new SoftAssert();

	}

	@Test(dataProvider = "LoginDataProvider")
	public void verifyLoginCredentials(String userName, String password) {
		AccountLoginPage accountLoginPage = homePage.clickLoginLink();
		accountLoginPage.login(userName, password);
		softAssert.assertEquals(driver.getTitle(), "My Account", "Page not navigated to my account page");
		softAssert.assertAll();
	}

//	@Test(dataProvider = "MyAccountDataProvider")
//	public void verifyEditAccountFieldsArePreFilled(String firstName, String lastName, String email, String telephone,
//			String password, String confirmPassword) {
//		
//		AccountLoginPage accountLoginPage = homePage.clickLoginLink();
//		MyAccountPage myAccountPage= accountLoginPage.login(email, password);
//
//		// Validating title of the page
//		softAssert.assertEquals(driver.getTitle(), "My Account", "Title doesn't match");
//		
//		MyAccountInformation myAccountInformation = myAccountPage.clickEditAccount();
//
//		// Validating whether data is pre-filled in the edit account page
//		softAssert.assertEquals(myAccountInformation.getTextFromFirstName(), firstName, "First name not filled");
//		softAssert.assertEquals(myAccountInformation.getTextFromLastName(), lastName, "Last name not filled");
//		softAssert.assertEquals(myAccountInformation.getTextFromEmail(), email, "Email not filled");
//		softAssert.assertEquals(myAccountInformation.getTextFromTelephone(), telephone, "Telephone not filled");
//		softAssert.assertAll();
//	}

	// fetching data from the Excel Sheet
	@DataProvider(name = "MyAccountDataProvider")
	public String[][] provideMyAccountData() throws IOException {

		String filePath = "./Test Data\\RegisterDetails.xlsx";
		int rowCount = ExcelUtils.getRowCount(filePath, "Sheet1");
		int colCount = ExcelUtils.getColumnCount(filePath, "Sheet1", rowCount);
		String[][] registerData = new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				registerData[i - 1][j] = ExcelUtils.getCellValue(filePath, "Sheet1", i, j);
			}
		}
		return registerData;
	}

	@DataProvider(name = "LoginDataProvider")
	public String[][] provideLoginData() throws IOException {

		String filePath = "C:\\Users\\kaurh\\OneDrive\\Desktop\\LoginDetails.xlsx";
		int rowCount = ExcelUtils.getRowCount(filePath, "Sheet1");
		int colCount = ExcelUtils.getColumnCount(filePath, "Sheet1", rowCount);
		String[][] loginData = new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				loginData[i - 1][j] = ExcelUtils.getCellValue(filePath, "Sheet1", i, j);
			}
		}
		return loginData;
	}

	@AfterMethod
	public void teardown() {
		quitBrowser();
	}
}
