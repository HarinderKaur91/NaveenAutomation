package com.naveenautomation.Tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.AccountLoginPage;
import com.naveenautomation.Pages.HomePage;
import com.naveenautomation.Pages.LaptopsAndNotebooksPage;
import com.naveenautomation.Pages.MyAccountPage;

public class LaptopsAndNotebooksTest extends TestBase {

	HomePage homePage;
	AccountLoginPage accountLoginPage;
	MyAccountPage myAccountPage;
	SoftAssert softAssert;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		homePage = new HomePage(driver,true);
		softAssert = new SoftAssert();
		accountLoginPage = new AccountLoginPage(driver,true);
		myAccountPage = new MyAccountPage(driver,true);
		homePage.clickLoginLink();
		accountLoginPage.login("harinder21@gmail.com", "Password1");

	}

	@Test
	public void validateLaptopsAndNotebooksPage() {

		myAccountPage.hoverOnLaptopAndNotebooks();
		LaptopsAndNotebooksPage laptopsAndNotebooksPage = myAccountPage.clickShowAllLaptops();
		
		//Selecting sorting technique
		laptopsAndNotebooksPage.selectSortingMethodFromDropDown();
		
		//Adding products to the wishlist
		laptopsAndNotebooksPage.addToWishlistFirstLaptop();
		laptopsAndNotebooksPage.addToWishlistSecondLaptop();
		laptopsAndNotebooksPage.addToWishlistThirdLaptop();
		
		//Validating success banner
		softAssert.assertEquals(laptopsAndNotebooksPage.getTextFromSuccesBanner(),
				"Success: You have added "+laptopsAndNotebooksPage.getProductNameInSuccessBanner()+ " to your "+laptopsAndNotebooksPage.getWishListInSuccessBanner()+"!\n×", "Text on success banner doesn't match");
		
		//Validating heading text of laptops page
		softAssert.assertEquals(laptopsAndNotebooksPage.getLaptopsAndNotebooksHeadingText(), "Laptops & Notebooks",
				"Text doesn't match");
		
		//Validating title of laptops page
		softAssert.assertEquals(driver.getTitle(), "Laptops & Notebooks", "Title doesn't match");
		laptopsAndNotebooksPage.clickWishlistLink();
		softAssert.assertAll();
	
	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}

}
