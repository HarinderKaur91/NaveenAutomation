package com.naveenautomation.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.AccountCreated;
import com.naveenautomation.Pages.HomePage;
import com.naveenautomation.Pages.RegisterAccountPage;

public class AccountCreatedTest extends TestBase {

	HomePage homePage;
	SoftAssert softAssert;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		homePage = new HomePage(driver,true);
		softAssert = new SoftAssert();
	}

	@Test
	public void validateUserRegistration() {
		RegisterAccountPage registerAccountPage = homePage.clickRegisterLink();
		String randomEmail=registerAccountPage.generateRandomEmail();
		AccountCreated accountCreated = registerAccountPage.signUp("Harinder", "Kaur", randomEmail,
				"543212345", "Password1", "Password1");
		softAssert.assertEquals(accountCreated.getSuccessAccountCreatedText(), "Your Account Has Been Created!",
				"Account not created");
		softAssert.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}

}
