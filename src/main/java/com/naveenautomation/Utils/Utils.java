package com.naveenautomation.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.naveenautomation.Base.TestBase;

public class Utils extends TestBase {

	public static int getRandomInteger(int length) {
		Random random = new Random();
		return random.nextInt(length);
	}

	public static String getRandomString(int length) {
		return RandomStringUtils.random(length);
	}

	public static String getRandomEmail() {
		String suffix = RandomStringUtils.random(5);
		return "testemail_" + suffix + "@gmail.com";
	}

	public static String getRandomPassword() {
		return "Password" + new Random().nextInt(1);
	}

	public static Date getCurrentTime() {
		return new Date();
	}

	public void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void takeScreenshot(String testName) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		// wrapping driver with takescreenshot interface and then calling
		// getScreenshotAs method which
		// takes type of output as argument which is file type
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// copying file from random memory to the local memory(we are storing in folder
			// name FailedTestScreenShots
			FileUtils.copyFile(screenshotFile,
					new File("./FailedTestScreenShots\\" + testName + "_" + timeStamp + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
