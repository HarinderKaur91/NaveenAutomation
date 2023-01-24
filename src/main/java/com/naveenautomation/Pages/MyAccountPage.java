package com.naveenautomation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.naveenautomation.Browsers.ProxyDriver;

public class MyAccountPage extends Page {
	
	private static final String PAGE_URL="/account";
	public MyAccountPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	

	public static final By myAccountText = By.cssSelector("#content h2:first-of-type");
	public static final By logOutLink = By.cssSelector("div.list-group a:last-of-type");
	public static final By editAccountLink = By.cssSelector("div.list-group a:nth-of-type(2)");
	public static final By editAccountInformation = By.cssSelector("#content ul:first-of-type li:first-of-type a");
	
	@FindBy(css = "ul.nav.navbar-nav li.dropdown:nth-of-type(2) a.dropdown-toggle")
	WebElement laptopAndNotebooksHoverButton;
//	public static final By laptopAndNotebooksHoverButton = By
//			.cssSelector("ul.nav.navbar-nav li.dropdown:nth-of-type(2) a.dropdown-toggle");
	public static final By showAllLaptops = By.cssSelector("ul li.dropdown:nth-of-type(2) a.see-all");
	public static final By addressBookLink = By.cssSelector("div.list-group a:nth-of-type(4)");
	public static final By telephoneChangeSuccessBanner = By.cssSelector("div.alert-success");
	public static final By subscribeUnsubscribeLink = By.cssSelector("#content ul:last-of-type a");

	public String getMyAccountText() {
		return ((ProxyDriver)wd).getText(myAccountText);
	}

	public AccounLogoutPage clickLogOutLink() {
		((ProxyDriver) wd).click(logOutLink);
		return new AccounLogoutPage(wd, true);
	}

	@FindBy(css = "div.alert-success")
	WebElement subscribeAlertSuccessBanner;

	public MyAccountInformation clickEditAccount() {
		((ProxyDriver) wd).click(editAccountInformation);
		return new MyAccountInformation(wd, true);
	}

	public NewsletterSubscriptionPage clicksubscribeUnsubscribeLink() {
		((ProxyDriver) wd).click(subscribeUnsubscribeLink);
		return new NewsletterSubscriptionPage(wd, true);
	}

	public String getsubscribeAlertSuccessMessage() {
		return subscribeAlertSuccessBanner.getText();
	}

	public void hoverOnLaptopAndNotebooks() {
		//action.moveToElement(laptopAndNotebooksHoverButton).perform();
		((ProxyDriver)wd).mouseHover(laptopAndNotebooksHoverButton);
	}

	public LaptopsAndNotebooksPage clickShowAllLaptops() {
		((ProxyDriver) wd).click(showAllLaptops);
		return new LaptopsAndNotebooksPage(wd, true);
	}

	public String getTelephoneChangeSuccessMessage() {
		return ((ProxyDriver)wd).getText(telephoneChangeSuccessBanner);
	}

	public AddressBookPage clickAddressBookLink() {
		((ProxyDriver) wd).click(addressBookLink);
		return new AddressBookPage(wd, true);
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
	public MyAccountPage get() {
		return (MyAccountPage)super.get();
	}

}
