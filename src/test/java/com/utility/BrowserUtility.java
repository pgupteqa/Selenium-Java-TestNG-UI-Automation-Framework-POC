package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

// Its a Parent class that is used in the pages. And here we have used to abstraction, creating the reusable methods and used them inside the pages.

public abstract class BrowserUtility {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public WebDriver getDriver() {
		return driver.get(); // threadlocal is used so we have to get and set the driver. to assign a value
								// we have to use set method.
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver); // Initialize driver instance
	}

	// Constructor for browser management
	public BrowserUtility(Browser browserName, boolean isHeadless) {
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
				logger.info("Chrome Browser is selected in headless");
			} else {
				driver.set(new ChromeDriver());
				logger.info("Chrome Browser is selected");
			}
		}

		else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1920,1080");
				driver.set(new FirefoxDriver(options));
				logger.info("Firefox Browser is selected");
			} else {
				driver.set(new FirefoxDriver());
				logger.info("Firefox Browser is selected");
			}

		}

		else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());
				logger.info("Microsoft Edge Browser is selected");
			}

		}

		else {
			logger.error("Invalid browsername..... Please select a valid browser");
		}
	}

	public void goToWebsite(String url) {
		logger.info("Navigated to the Website " + url);
		driver.get().get(url);
	}

	public void maxWindow() {
		logger.info("Maximized the Browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding the Element with locator" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Found the Element with locator" + locator);
		logger.info("perform click action and clicked on the element" + locator);
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		WebElement element = driver.get().findElement(locator);
		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		WebElement element = driver.get().findElement(locator);
		return element.getText();
	}

	public void quitBrowserSession() {
		logger.info("Browser is Closed");
		driver.get().quit();
	}

	public String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);

		String path = "./screenshot/" + name + " - " + timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return path;
	}

}
