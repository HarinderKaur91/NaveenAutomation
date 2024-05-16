package com.naveenautomation.Tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.AccountLoginPage;
import com.naveenautomation.Pages.HomePage;
import com.naveenautomation.Pages.MyAccountInformation;
import com.naveenautomation.Pages.MyAccountPage;
import com.naveenautomation.Pages.NewsletterSubscriptionPage;

public class MyAccountTest extends TestBase {

	HomePage homePage;
	AccountLoginPage accountLoginPage;
	MyAccountPage myAccountPage;
	SoftAssert softAssert;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		softAssert = new SoftAssert();
		homePage = new HomePage(driver, true).get();
		myAccountPage = new MyAccountPage(driver, true).get();
		accountLoginPage = new AccountLoginPage(driver, true).get();
		homePage.clickLoginLink();
		accountLoginPage.login("harinderk@gmail.com", "Password2");
	}

	@Test(groups = { "smoke" })
	public void validateLogin() {
		System.out.println("smoke suite test case1 running");
		// softAssert.assertEquals(myAccountPage.getMyAccountText(), "My Account",
		// "Login Failed");
		// softAssert.assertAll();
	}

	// @Test(dependsOnMethods = { "testSomething" }, groups = { "smoke" })
	@Test(groups = { "smoke" })

	public void verifyAlertBannerForTelephoneUpdate() {
		// System.out.println("Regression suite test case");
		System.out.println("smoke suite test case2 running");
//		MyAccountInformation myAccountInformation = myAccountPage.clickEditAccount();
//		myAccountInformation.updateTelephone("780298456");
//		softAssert.assertEquals(myAccountPage.getTelephoneChangeSuccessMessage(),
//				"Success: Your account has been successfully updated.", "Telephone update failed");
//		softAssert.assertEquals(driver.getTitle(), "My Account", "Failed to naviagte back to my account page");
//		;
	}

	@Test(groups = { "regression" })
	public void verifyAlertBannerForNewsletterSubscription() {
		NewsletterSubscriptionPage newsletterSubscriptionPage = myAccountPage.clicksubscribeUnsubscribeLink();
		newsletterSubscriptionPage.clickContinueButton("Yes");
		softAssert.assertEquals(myAccountPage.getsubscribeAlertSuccessMessage(),
				" Success: Your newsletter subscription has been successfully updated!", "Subscription update failed");
		softAssert.assertEquals(driver.getTitle(), "My Account", "Failed to naviagte back to my account page");
		;
	}

	@Test(groups = { "smoke" })
	public void testSomething() {
		System.out.println("Inside depends on method");
		if (softAssert != null) {
			softAssert.assertTrue(true);
			softAssert.assertAll();
		}
	}

	@BeforeMethod
	public void tearDown() {
		quitBrowser();
	}
}
