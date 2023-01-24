package com.naveenautomation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.Browsers.ProxyDriver;

public class NewsletterSubscriptionPage extends Page {
	private static final String PAGE_URL = "/newsletter";

	public NewsletterSubscriptionPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	public static final By subscribeYesRadioBtn=By.cssSelector("input[value='1']");
	public static final By subscribeNoRadioBtn=By.cssSelector("input[value='0']");
	public static final By continueBtn=By.cssSelector("input[value='Continue']");

	public void clicksubscribeYesRadioButton() {
		if (!((ProxyDriver)wd).isSelected(subscribeYesRadioBtn)) {
			((ProxyDriver)wd).click(subscribeYesRadioBtn);
		}
	}

	public void clicksubscribeNoRadioButton() {
		if (!((ProxyDriver)wd).isSelected(subscribeNoRadioBtn)) {
			((ProxyDriver)wd).click(subscribeNoRadioBtn);
		}
	}

	public MyAccountPage clickContinueButton(String choiceForSubscription) {
		if (choiceForSubscription.equalsIgnoreCase("Yes")) {
			clicksubscribeYesRadioButton();
		}else if (choiceForSubscription.equalsIgnoreCase("No")) {
			clicksubscribeNoRadioButton();
		}
		((ProxyDriver)wd).submit(continueBtn);
		return new MyAccountPage(wd,true);
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
	public NewsletterSubscriptionPage get() {
		return (NewsletterSubscriptionPage)super.get();
	}
}
