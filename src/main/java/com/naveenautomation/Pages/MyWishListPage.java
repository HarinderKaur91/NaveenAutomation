package com.naveenautomation.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.naveenautomation.Browsers.ProxyDriver;

public class MyWishListPage extends Page {
	
	private static final String PAGE_URL = "/wishlist";

	public MyWishListPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}


	public static final By deleteButton=By.cssSelector("tbody tr:last-of-type td:last-of-type a");
	public static final By successBannerAfterDelete=By.cssSelector("div.alert-success");

	public WebElement getElementFromTheTable(String productName, WishList column) {

		int columnIndex = getIndexForColumn(column);

		List<WebElement> rowsInTable = wd
				.findElements(By.cssSelector("table.table.table-bordered.table-hover tbody tr"));

		for (int i = 0; i < rowsInTable.size(); i++) {
			List<WebElement> cells = rowsInTable.get(i).findElements(By.cssSelector("td"));

			String productNameText = cells.get(1).getText();

			if (productNameText.equals(productName)) {
				return cells.get(columnIndex);
			}
		}

		System.out.println("Column name was not found!!!");
		return null;
	}

	public int getIndexForColumn(WishList column) {
		List<WebElement> headers = wd
				.findElements(By.cssSelector("table.table.table-bordered.table-hover thead tr td"));

		for (WebElement webElement : headers) {
			String headerText = webElement.getText();
			if (headerText.equals(column.getName())) {
				return headers.indexOf(webElement);
			}
		}
		System.out.println("Column does not exist.....");
		return -1;
	}

	public String getTextFromSuccessBannerAfterDelete() {
		return ((ProxyDriver)wd).getText(successBannerAfterDelete);
	}

	public enum WishList {

		IMAGE("Image"), PRODUCTNAME("Product Name"), MODEL("Model"), STOCK("Stock"), UNITPRICE("Unit Price"),
		ACTION("Action");

		String name;

		private WishList(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public void deleteProductInWishList(String productName, WishList column, By locator) {
		getElementFromTheTable(productName, column).findElement(locator).click();
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
	public MyWishListPage get() {
		return (MyWishListPage)super.get();
	}
}
