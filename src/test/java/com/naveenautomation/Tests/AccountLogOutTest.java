package com.naveenautomation.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.AccounLogoutPage;
import com.naveenautomation.Pages.AccountLoginPage;
import com.naveenautomation.Pages.HomePage;
import com.naveenautomation.Pages.MyAccountPage;

public class AccountLogOutTest extends TestBase {
	HomePage homePage;
	MyAccountPage myAccountPage;
	AccountLoginPage accountLoginPage;
	SoftAssert softAssert;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		softAssert = new SoftAssert();
		homePage = new HomePage(driver,true);
		accountLoginPage = new AccountLoginPage(driver,true);
		myAccountPage=new MyAccountPage(driver,true);
		homePage.clickLoginLink();
		accountLoginPage.login("harinder21@gmail.com", "Password1");
	}

	@Test
	public void validateLogOut() {
		AccounLogoutPage accounLogoutPage = myAccountPage.clickLogOutLink();
		softAssert.assertEquals(accounLogoutPage.getSuccessLogOutText(), "Account Logout", "Logout failed");
		softAssert.assertEquals(driver.getTitle(), "Account Logout", "Account logout title doesn't match");
		accounLogoutPage.clickcontinueBtn();
		softAssert.assertEquals(driver.getTitle(), "Your Store", "Failed to naviagte back to home page");
		softAssert.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}

}
