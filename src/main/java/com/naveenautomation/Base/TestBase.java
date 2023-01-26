package com.naveenautomation.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.naveenautomation.Browsers.Browsers;
import com.naveenautomation.Browsers.ProxyDriver;
import com.naveenautomation.Listeners.WebdriverEvents;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	// Testing Web hook
	public static WebDriver driver;
	public Browsers DEFAULT_BROWSER = Browsers.EDGE;

	// EventFiringWebDriver is a wrapper around an arbitrary WebDriver instance
	// which supports registering of a WebDriverEventListener, e.g. for logging
	// purposes.
//
//	public static WebdriverEvents events = new WebdriverEvents();
//	public EventFiringWebDriver eventFiringWebDriver;
//launching browser
	public void launchBrowser() {
		// switch case
		switch (DEFAULT_BROWSER) {
		case GOOGLE_CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ProxyDriver(WebDriverManager.chromedriver().create());
			break;

		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new ProxyDriver(WebDriverManager.edgedriver().create());
			break;

		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new ProxyDriver(WebDriverManager.firefoxdriver().create());
			break;

		default:
			System.out.println("Not a valid browser");
			break;
		}
//		eventFiringWebDriver = new EventFiringWebDriver(driver);
//		eventFiringWebDriver.register(events);
//		driver = eventFiringWebDriver;// wrapping driver into eventFiringWebDriver

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		// Manage the page load timeout
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		// Manage the script load timeout
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

	}
//closing browser
	public void quitBrowser() {
		// closing the browser
		// close method closes the current window instance
		// driver.close();
	}

}
