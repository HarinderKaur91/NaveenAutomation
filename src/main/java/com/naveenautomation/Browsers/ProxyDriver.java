package com.naveenautomation.Browsers;

import java.net.URL;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProxyDriver implements WebDriver, JavascriptExecutor, TakesScreenshot {

	private WebDriver driver;

	public ProxyDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void click(By locator) {
		WebElement element = this.waitForElementToBeClickable(locator, 10);
		element.click();
	}

	public void submit(By locator) {
		this.waitForElementToBeClickable(locator, 10).submit();
	}

	public void clear(By locator) {
		this.waitForElementToBeVisible(locator, 5).clear();
	}

	public String getText(By locator) {
		return this.waitForElementToBeVisible(locator, 5).getText();
	}

	public void sendKeys(By locator, String text) {
		this.waitForElementToBeVisible(locator, 5).sendKeys(text);
	}

//	public void getAttribute(By by) {
//		return By.getAttribute("value");
//	}
	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return ((TakesScreenshot) driver).getScreenshotAs(target);
	}

	@Override
	public Object executeScript(String script, Object... args) {
		return ((JavascriptExecutor) driver).executeScript(script, args);
	}

	@Override
	public Object executeAsyncScript(String script, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void get(String url) {
		ProxyDriver.this.driver.get(url);
	}

	@Override
	public String getCurrentUrl() {
		return ProxyDriver.this.driver.getCurrentUrl();
	}

	@Override
	public String getTitle() {
		return ProxyDriver.this.driver.getTitle();
	}

	@Override
	public List<WebElement> findElements(By by) {
		return ProxyDriver.this.driver.findElements(by);
	}

	@Override
	public WebElement findElement(By by) {
		return ProxyDriver.this.driver.findElement(by);
	}

	@Override
	public String getPageSource() {
		return ProxyDriver.this.driver.getPageSource();
	}

	@Override
	public void close() {
		driver.close();

	}

	@Override
	public void quit() {
		driver.quit();
	}

	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	@Override
	public TargetLocator switchTo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Navigation navigate() {
		return new Navigation() {

			@Override
			public void back() {
				ProxyDriver.this.driver.navigate().back();
			}

			@Override
			public void forward() {
				ProxyDriver.this.driver.navigate().forward();

			}

			@Override
			public void to(String url) {
				ProxyDriver.this.driver.navigate().to(url);
			}

			@Override
			public void to(URL url) {
				ProxyDriver.this.driver.navigate().to(url);
			}

			@Override
			public void refresh() {
				ProxyDriver.this.driver.navigate().refresh();

			}
		};
	}

	public String getText(By locator, int waitforElementToBeVisibleInSeconds) {
		try {

			WebElement element = this.waitForElementToBeVisible(locator, waitforElementToBeVisibleInSeconds);
			return element.getText().trim();
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Options manage() {
		return driver.manage();
	}

	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by, 10);
			return this.findElement(by).isDisplayed();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean isEnabled(By by) {
		try {
			waitForElementToBeVisible(by, 10);
			return this.findElement(by).isEnabled();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean isSelected(By by) {
		try {
			waitForElementToBeVisible(by, 10);
			return this.findElement(by).isSelected();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public void selectFromDropDown(By by, String value) {
		WebElement element =this.waitForElementToBeVisible(by, 5);
		waitForElementToBeSelectable(element);
		Select sc = new Select(element);
		try {
			sc.selectByValue(value);
		} catch (Exception e) {
			sc.selectByVisibleText(value);
		}
	}

	public void switchToNewTab(By element) {
		String parentHandle = this.getWindowHandle();
		waitForElementToBeClickable(element, 5);

		Set<String> allWindowHandles = this.getWindowHandles();
		for (String windowHandle : allWindowHandles) {
			if (!windowHandle.equals(parentHandle)) {
				this.switchTo().window(windowHandle);
			}
		}
	}
	
	public void mouseHover(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();

	}

	public void acceptAlert() {
		new WebDriverWait(this, 10).until(ExpectedConditions.alertIsPresent()).accept();
	}

	public void dismissAlert() {
		new WebDriverWait(this, 10).until(ExpectedConditions.alertIsPresent()).dismiss();
	}

	public WebElement waitForElementToBeClickable(By locator, int timeOutInSeconds) {
		return new WebDriverWait(this, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement waitForElementToBeVisible(By Locator, int timeOutInSeconds) {
		return new WebDriverWait(this, timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(Locator));
	}

	public void waitForElementToBeSelectable(WebElement element) {
		new WebDriverWait(this, 10).until(ExpectedConditions.elementSelectionStateToBe(element, true));
	}
}
