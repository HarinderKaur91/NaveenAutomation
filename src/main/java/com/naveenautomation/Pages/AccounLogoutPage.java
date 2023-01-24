package com.naveenautomation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.Browsers.ProxyDriver;

public class AccounLogoutPage extends Page {
	
	private static final String PAGE_URL = "/logout";

	public AccounLogoutPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);

	}

	private static final By accountLogOutSuccessTxt = By.cssSelector("#content h1");
	private static final By continueBtn = By.cssSelector("div.buttons a");

	public String getSuccessLogOutText() {
		return ((ProxyDriver)wd).getText(accountLogOutSuccessTxt);
	}

	public HomePage clickcontinueBtn() {
		((ProxyDriver) wd).click(continueBtn);
		return new HomePage(wd, true);

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
	public AccounLogoutPage get() {
		return (AccounLogoutPage)super.get();
	}
}
