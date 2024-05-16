package com.naveenautomation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.Browsers.ProxyDriver;

public class AccountLoginPage extends Page {

	private static final String PAGE_URL = "/login";

	public AccountLoginPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static final By emailInputField = By.cssSelector(" input[name='emil'],form div.form-group:first-of-type input");
	private static final By passwordInputField = By.cssSelector("form div.form-group:last-of-type input");
	private static final By loginBtn = By.cssSelector("input[type='submit']");
	private static final By alertBanner = By.cssSelector("div.alert-danger");
	private static final By continueBtn = By.xpath("//a[text()='Continue']");

	public void enterEmailInputField(String email) {
		((ProxyDriver) wd).sendKeys(emailInputField, email);
	}

	public void enterPasswordInputField(String password) {
		((ProxyDriver) wd).sendKeys(passwordInputField, password);
	}

	public MyAccountPage login(String email, String password) {
		enterEmailInputField(email);
		enterPasswordInputField(password);
		((ProxyDriver) wd).submit(loginBtn);
		return new MyAccountPage(wd, true);
	}

	public String getTextFromAlertBanner() {
		return ((ProxyDriver) wd).getText(alertBanner);
	}

	public RegisterAccountPage clickContinueButton() {
		((ProxyDriver) wd).click(continueBtn);
		return new RegisterAccountPage(wd, true);
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
	public AccountLoginPage get() {
		return (AccountLoginPage)super.get();
	}
}
