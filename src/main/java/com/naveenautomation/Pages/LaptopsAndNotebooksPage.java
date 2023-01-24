package com.naveenautomation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.Browsers.ProxyDriver;

public class LaptopsAndNotebooksPage extends Page {
	private static final String PAGE_URL = "/category&path=18";

	public LaptopsAndNotebooksPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	public static final By laptopsAndNotebboksText = By.cssSelector("#content h2");

	public static final By sortByDropDown = By.cssSelector("div.col-md-4select.form-control");
	public static final By firstProductAddToWishlistButton = By
			.cssSelector("#content>div:nth-of-type(4)>div:first-of-type button:nth-of-type(2)");
	public static final By secondProductAddToWishlistButton = By
			.cssSelector("#content>div:nth-of-type(4)>div:nth-of-type(2) button:nth-of-type(2)");
	public static final By thirdproductAddToWishlistButton = By
			.cssSelector("#content>div:nth-of-type(4)>div:nth-of-type(3) button:nth-of-type(2)");
	public static final By successBanner = By.cssSelector("div.alert-success");
	public static final By produtNameInSuccessBanner = By.cssSelector("div.alert-success a:first-of-type");
	public static final By wishListInSuccessBanner = By.cssSelector("div.alert-success a:last-of-type");
	public static final By wishListLink = By.cssSelector("ul.list-inline>li:nth-of-type(3) span");

	public String getLaptopsAndNotebooksHeadingText() {
		return ((ProxyDriver) wd).getText(laptopsAndNotebboksText);
	}

	public void selectSortingMethodFromDropDown() {
		((ProxyDriver) wd).selectFromDropDown(sortByDropDown, "Rating (Highest)");

	}

	public void addToWishlistFirstLaptop() {
		((ProxyDriver) wd).click(firstProductAddToWishlistButton);
	}

	public void addToWishlistSecondLaptop() {
		((ProxyDriver) wd).click(secondProductAddToWishlistButton);
	}

	public void addToWishlistThirdLaptop() {
		((ProxyDriver) wd).click(thirdproductAddToWishlistButton);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getTextFromSuccesBanner() {
		return ((ProxyDriver) wd).getText(successBanner);
	}

	public String getProductNameInSuccessBanner() {
		return ((ProxyDriver) wd).getText(produtNameInSuccessBanner);
	}

	public String getWishListInSuccessBanner() {
		return ((ProxyDriver) wd).getText(wishListInSuccessBanner);
	}

	public MyWishListPage clickWishlistLink() {
		((ProxyDriver) wd).click(wishListLink);
		return new MyWishListPage(wd, true);
	}

	@Override
	protected String getPageUrl() {
		return getDomain() + PAGE_URL;
	}

	@Override
	protected void isLoaded() {
		if (!urlContains(wd.getCurrentUrl())) {
			throw new Error();
		}
	}

	@Override
	public LaptopsAndNotebooksPage get() {
		return (LaptopsAndNotebooksPage) super.get();
	}
}
