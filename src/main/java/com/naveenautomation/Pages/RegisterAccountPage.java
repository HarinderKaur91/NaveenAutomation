package com.naveenautomation.Pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.Browsers.ProxyDriver;

public class RegisterAccountPage extends Page {

	private static final String PAGE_URL = "/register";

	public RegisterAccountPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}


	public static final By fName=By.cssSelector("input-firstname");
	public static final By lName=By.cssSelector("input-lastname");
	public static final By email=By.cssSelector("input-email");
	public static final By telephone=By.cssSelector("input-telephone");
	public static final By password=By.cssSelector("input-password");
	public static final By confirmPassword=By.cssSelector("input-confirm");
	public static final By subscribeRadioBtn=By.cssSelector("label.radio-inline input[value='1']");
	public static final By privacyPolicyCheckBox=By.cssSelector("input[name='agree']");
	public static final By continueBtn=By.cssSelector("input[value='Continue']");

	private void enterFirstName(String fname) {
		((ProxyDriver)wd).sendKeys(fName, fname);
	}

	private void enterLastName(String lname) {
		((ProxyDriver)wd).sendKeys(lName, lname);
	}

	private void enterEmail(String emailInput) {
		((ProxyDriver)wd).sendKeys(email, emailInput);
	}

	private void enterPhoneNumber(String phone) {
		((ProxyDriver)wd).sendKeys(telephone, phone);
	}

	private void enterPassword(String pwd) {
		((ProxyDriver)wd).sendKeys(password, pwd);
	}

	private void enterConfirmPwd(String cPwd) {
		((ProxyDriver)wd).sendKeys(confirmPassword, cPwd);
	}

	private void clickPrivacyPolicy() {
		((ProxyDriver)wd).click(privacyPolicyCheckBox);
	}

	public AccountCreated signUp(String fname, String lname, String emailInput, String phone, String pwd, String cPwd) {
		enterFirstName(fname);
		enterLastName(lname);
		enterEmail(emailInput);
		enterPhoneNumber(phone);
		enterPassword(pwd);
		enterConfirmPwd(cPwd);
		((ProxyDriver)wd).click(subscribeRadioBtn);
		clickPrivacyPolicy();
		((ProxyDriver)wd).submit(continueBtn);
		return new AccountCreated(wd,true);
	}

	public String generateRandomEmail() {
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		String randomEmail= "harinder" + randomInt + "@gmail.com";
		return randomEmail;
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
	public RegisterAccountPage get() {
		return (RegisterAccountPage)super.get();
	}

}
