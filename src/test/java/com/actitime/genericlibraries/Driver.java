package com.actitime.genericlibraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
	private WebDriver driver;

	public Driver() {
		driver = new FirefoxDriver();
	}
	
	public void quit(){
		driver.quit();
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
	// public static WebDriver driver;
	//
	// public WebDriver getBrowser() {
	// System.setProperty("webdriver.ie.driver",
	// "C:\\Users\\Meenu\\Downloads\\IEDriverServer.exe");
	// driver = new InternetExplorerDriver();
	// return driver;
	// }
}
