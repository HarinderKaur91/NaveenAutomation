package com.naveenautomation.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.naveenautomation.Browsers.ProxyDriver;

public class AddressBookPage extends Page {
	private static final String PAGE_URL = "/address";

	public AddressBookPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	public static final By firstName = By.cssSelector("#input-firstname");
	public static final By lastName = By.cssSelector("#input-lastname");
	public static final By company = By.cssSelector("#input-company");
	public static final By address1 = By.cssSelector("#input-address-1");
	public static final By address2 = By.cssSelector("#input-address-2");
	public static final By city = By.cssSelector("#input-city");
	public static final By postCode = By.cssSelector("#input-postcode");
	public static final By country = By.cssSelector("#input-country");
	public static final By state = By.cssSelector("#input-zone");
	public static final By continueButton = By.cssSelector("input[value='Continue']");
	public static final By successAlertBanner = By.cssSelector("div.alert-success");

	private void editFirstName(String fname) {
		((ProxyDriver) wd).clear(firstName);
		((ProxyDriver) wd).sendKeys(firstName, fname);
	}

	private void editLastName(String lname) {
		((ProxyDriver) wd).clear(lastName);
		((ProxyDriver) wd).sendKeys(lastName, lname);
	}

	private void editCompany(String companyName) {

		((ProxyDriver) wd).clear(company);
		((ProxyDriver) wd).sendKeys(company, companyName);
	}

	private void editAddress1(String addressValue1) {

		((ProxyDriver) wd).clear(address1);
		((ProxyDriver) wd).sendKeys(address1, addressValue1);
	}

	private void editAddress2(String addressValue2) {

		((ProxyDriver) wd).clear(address2);
		((ProxyDriver) wd).sendKeys(address2, addressValue2);
	}

	private void editCity(String cityName) {
		((ProxyDriver) wd).clear(city);
		((ProxyDriver) wd).sendKeys(city, cityName);
	}

	private void editPostCode(String postalCode) {
		((ProxyDriver) wd).clear(postCode);
		((ProxyDriver) wd).sendKeys(postCode, postalCode);
	}

	private void editCountry(String countryName) {

		((ProxyDriver) wd).selectFromDropDown(country, countryName);
		((ProxyDriver) wd).selectFromDropDown(country, countryName);
	}

	private void editState(String stateName) {

		((ProxyDriver) wd).selectFromDropDown(state, stateName);
	}

	private void clickContinueButton() {
		((ProxyDriver) wd).submit(continueButton);
	}

	public String getTextFromSuccessAlert() {
		return ((ProxyDriver) wd).getText(successAlertBanner);
	}

	public WebElement getElementFromTheAddressTable(String postalCode) {

		List<WebElement> rowsInTable = wd.findElements(By.cssSelector("table.table.table-bordered.table-hover tr"));
		for (int i = 0; i < rowsInTable.size(); i++) {
			List<WebElement> cells = rowsInTable.get(i).findElements(By.cssSelector("td"));
			String addressText = cells.get(0).getText();

			String[] addressArray = addressText.split("[' '\n]");

			for (int j = 0; j < addressArray.length; j++) {
				// comparing each element of splitted string with postal code
				if (addressArray[j].equals(postalCode)) {
					return cells.get(1);
				}
			}
		}
		System.out.println("Postal code not found!!!");
		return null;
	}

	public enum EditAddressBook {
		FNAME("Fname"), LNAME("Lname"), COMPANY("Company"), ADDRESS1("Address1"), ADDRESS2("Address2"), CITY("City"),
		POSTAL("Postal"), COUNTRY("Country"), STATE("State");

		String inputField;

		private EditAddressBook(String inputFieldToEdit) {
			this.inputField = inputFieldToEdit;
		}
	}

	public void clickEditButtonInWebTable(String postalCode, By locator) {
		getElementFromTheAddressTable(postalCode).findElement(locator).click();
	}

	public void updateAddressBook2(EditAddressBook field, String value) {
		switch (field) {
		case FNAME:
			editFirstName(value);
			break;
		case LNAME:
			editLastName(value);
			break;
		case COMPANY:
			editCompany(value);
			break;
		case ADDRESS1:
			editAddress1(value);
			break;
		case ADDRESS2:
			editAddress2(value);
			break;
		case CITY:
			editCity(value);
			break;
		case POSTAL:
			editPostCode(value);
			break;
		case COUNTRY:
			editCountry(value);
			break;
		case STATE:
			editState(value);
			break;
		default:
			System.out.println("Invalid input!");
			break;
		}

	}

	public void updateAddressBook(String fname, String lname, String company, String address1, String address2,
			String city, String postal, String country, String state) {
		editFirstName(fname);
		editLastName(lname);
		editCompany(company);
		editAddress1(address2);
		editAddress2(address2);
		editCity(city);
		editPostCode(postal);
		editCountry(country);
		sleep();
		editState(state);
		clickContinueButton();
	}

	public void sleep() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public AddressBookPage get() {
		return (AddressBookPage) super.get();
	}

}
