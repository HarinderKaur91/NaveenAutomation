package com.naveenautomation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.Browsers.ProxyDriver;

public class MyAccountInformation extends Page {
	private static final String PAGE_URL = "/edit";


	public MyAccountInformation(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}
	
	public static final By firstName=By.cssSelector("#input-firstname");
	public static final By lastName=By.cssSelector("#input-lastname");
	public static final By email=By.cssSelector("#input-email");
	public static final By telephone=By.cssSelector("#input-telephone");
	public static final By continueBtn=By.cssSelector("input[value='Continue']");

	public MyAccountPage updateTelephone(String mobile) {
		((ProxyDriver)wd).clear(telephone);
		((ProxyDriver)wd).sendKeys(telephone, mobile);
		((ProxyDriver)wd).submit(continueBtn);
		return new MyAccountPage(wd,true);
	}

//	public String getTextFromFirstName() {
//		return firstName.getAttribute("value");
//	}
//
//	public String getTextFromLastName() {
//		return lastName.getAttribute("value");
//	}
//
//	public String getTextFromEmail() {
//		return email.getAttribute("value");
//	}
//
//	public String getTextFromTelephone() {
//		return telephone.getAttribute("value");
//	}

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
	public MyAccountInformation get() {
		return (MyAccountInformation)super.get();
	}
}
