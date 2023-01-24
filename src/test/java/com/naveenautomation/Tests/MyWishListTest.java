package com.naveenautomation.Tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.naveenautomation.Base.TestBase;
import com.naveenautomation.Pages.AccountLoginPage;
import com.naveenautomation.Pages.HomePage;
import com.naveenautomation.Pages.LaptopsAndNotebooksPage;
import com.naveenautomation.Pages.MyWishListPage;
import com.naveenautomation.Pages.MyWishListPage.WishList;

public class MyWishListTest extends TestBase {
	LaptopsAndNotebooksPage laptopsAndNotebooksPage;
	SoftAssert softAssert;
	HomePage homePage;
	AccountLoginPage accountLoginPage;
	MyWishListPage myWishListPage;

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		softAssert = new SoftAssert();
		homePage = new HomePage(driver,true).get();
		laptopsAndNotebooksPage = new LaptopsAndNotebooksPage(driver,true).get();
		accountLoginPage = new AccountLoginPage(driver,true).get();
		homePage.clickLoginLink();
		accountLoginPage.login("harinder21@gmail.com", "Password1");
		myWishListPage = laptopsAndNotebooksPage.clickWishlistLink();
	}

	@Test
	public void validateTitle() {
		// validating title of my wishlist page
		softAssert.assertEquals(driver.getTitle(), "My Wish List", "Title doesn't match");
		softAssert.assertAll();
	}

	@Test(priority = 1)
	public void verifyProductName() {
		// validating names of products added to wishlist
		softAssert.assertEquals(myWishListPage.getElementFromTheTable("MacBook Air", WishList.PRODUCTNAME).getText(),
				"MacBook Air", "Product1 name doesn't match");
		softAssert.assertEquals(myWishListPage.getElementFromTheTable("MacBook Pro", WishList.PRODUCTNAME).getText(),
				"MacBook Pro", "Product2 name doesn't match");
		softAssert.assertEquals(myWishListPage.getElementFromTheTable("Sony VAIO", WishList.PRODUCTNAME).getText(),
				"Sony VAIO", "Product3 name doesn't match");
		softAssert.assertAll();
	}

	@Test(priority = 2)
	public void verifyProductPrice() {
		// validating price of products added to wishlist
		softAssert.assertEquals(myWishListPage.getElementFromTheTable("MacBook Air", WishList.UNITPRICE).getText(),
				"$1,202.00", "Price for product1 doesn't match");
		softAssert.assertEquals(myWishListPage.getElementFromTheTable("MacBook Pro", WishList.UNITPRICE).getText(),
				"$2,000.00", "Price for product2 doesn't match");
		softAssert.assertEquals(myWishListPage.getElementFromTheTable("Sony VAIO", WishList.UNITPRICE).getText(),
				"$1,202.00", "Price for product3 doesn't match");
		softAssert.assertAll();
	}

	@Test(priority = 3)
	public void verifyModifyBannerAfterDelete() {
		// Deleting last product and validating delete banner success
		myWishListPage.deleteProductInWishList("MacBook Pro",WishList.ACTION,By.cssSelector("a"));
		softAssert.assertEquals(myWishListPage.getTextFromSuccessBannerAfterDelete(),
				"Success: You have modified your wish list!\n×", "Element not deleted");
		softAssert.assertAll();
	}

	@AfterMethod
	public void teardown() {
		quitBrowser();
	}

}
