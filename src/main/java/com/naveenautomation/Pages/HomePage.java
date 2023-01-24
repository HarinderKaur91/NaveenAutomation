package com.naveenautomation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.Browsers.ProxyDriver;

public class HomePage extends Page {
	private static final String PAGE_URL = "/home";

	public HomePage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	public static final By myAccountDropDown = By.xpath("//span[text()='My Account']");
	public static final By registerLink = By.xpath("//a[text()='Register']");
	public static final By loginLink = By.cssSelector("ul.dropdown-menu.dropdown-menu-right li:last-of-type a");

	public void openDropDown() {
		((ProxyDriver) wd).click(myAccountDropDown);
	}

	public RegisterAccountPage clickRegisterLink() {
		openDropDown();
		((ProxyDriver) wd).click(registerLink);
		return new RegisterAccountPage(wd, true);
	}

	public AccountLoginPage clickLoginLink() {
		openDropDown();
		((ProxyDriver) wd).click(loginLink);
		return new AccountLoginPage(wd, true);
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
	public HomePage get() {
		return (HomePage) super.get();
	}

}
