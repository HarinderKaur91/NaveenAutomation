package com.naveenautomation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.Browsers.ProxyDriver;

public class AccountCreated extends Page {

	private static final String PAGE_URL = "/success";

	public AccountCreated(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static final By accountCreationSuccessTxt = By.cssSelector("#content h1");
	private static final By continueBtn = By.cssSelector("div.buttons a");

	public String getSuccessAccountCreatedText() {
		return ((ProxyDriver) wd).getText(accountCreationSuccessTxt);
	}

	public MyAccountPage clickContinueButton() {
		((ProxyDriver) wd).click(continueBtn);
		return new MyAccountPage(wd, true);
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
	public AccountCreated get() {
		return (AccountCreated)super.get();
	}
}
