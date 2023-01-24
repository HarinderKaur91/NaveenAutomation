package com.naveenautomation.Tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.AccountLoginPage;
import com.naveenautomation.Pages.AddressBookPage;
import com.naveenautomation.Pages.HomePage;
import com.naveenautomation.Pages.LaptopsAndNotebooksPage;
import com.naveenautomation.Pages.MyAccountPage;
import com.naveenautomation.Pages.MyWishListPage;

public class AddressBookTest extends TestBase {
	LaptopsAndNotebooksPage laptopsAndNotebooksPage;
	SoftAssert softAssert;
	HomePage homePage;
	AccountLoginPage accountLoginPage;
	MyWishListPage myWishListPage;
	MyAccountPage myAccountPage;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		softAssert = new SoftAssert();
		homePage = new HomePage(driver,true);
		laptopsAndNotebooksPage = new LaptopsAndNotebooksPage(driver,true);
		accountLoginPage = new AccountLoginPage(driver,true);
		myAccountPage = new MyAccountPage(driver,true);
		homePage.clickLoginLink();
		accountLoginPage.login("harinder21@gmail.com", "Password1");
	}

	@Test
	public void verifyAddressBookEditedSuccessfully() {
		AddressBookPage addressBookPage = myAccountPage.clickAddressBookLink();
		String locatorForEdit = "table.table.table-bordered.table-hover td a:first-of-type";
		//updating first address
		addressBookPage.clickEditButtonInWebTable("143521",By.cssSelector(locatorForEdit));
		addressBookPage.updateAddressBook("Parul","Verma","XYZ","2089","15st","Milton","L8G0T1","Canada","Ontario");
		
		softAssert.assertEquals(addressBookPage.getTextFromSuccessAlert(), "Your address has been successfully updated",
				"Address not updated!");
		//updating second address
		addressBookPage.clickEditButtonInWebTable("L6Y0Z1",By.cssSelector(locatorForEdit));
		addressBookPage.updateAddressBook("Harinder","Kaur","ABC","2232","21St","Edmonton","T6T0Z1","Canada","Alberta");
		
		//validating success banner
		softAssert.assertEquals(addressBookPage.getTextFromSuccessAlert(), "Your address has been successfully updated",
				"Address not updated!");
		softAssert.assertAll();
	}

	@AfterMethod
	public void teardown() {
		quitBrowser();
	}
}
