package com.actitime.genericlibraries;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverCommonUtil {
	
	WebDriver driver;
	
	public WebdriverCommonUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForPageToLoad() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void waitForLinkPresent(String linkText) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By
				.linkText(linkText)));
	}

	public void waitForElementPresent(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(xpath)));
	}

	public void waitForNamePresent(String name) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
	}

	public void waitForIdPresent(String id) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
	}

	public boolean verifyText(String expectedText) {
		String entirePageSource = driver.getPageSource();
		boolean status = false;
		if (entirePageSource.contains(expectedText)) {
			status = true;
		}
		return status;
	}

	public boolean verifyTextPresent(String xpath, String expectedText) {
		String actualText = driver.findElement(By.xpath(xpath))
				.getText();
		boolean status = false;
		if (actualText.contains(expectedText)) {
			status = true;
		}
		return status;
	}

	public boolean verifyElementPresent(String xpath)
			throws InterruptedException {
		boolean status = false;
		int count = 0;
		while (count < 10) {
			try {
				driver.findElement(By.xpath(xpath));
				status = true;
				break;
			} catch (Exception e) {
				Thread.sleep(2000);
				count++;
			}
		}
		return status;
	}

	public boolean verifyIdPresent(String id) throws InterruptedException {
		boolean status = false;
		int count = 0;
		while (count < 10) {
			try {
				driver.findElement(By.id(id));
				status = true;
				break;
			} catch (Exception e) {
				Thread.sleep(2000);
				count++;
			}
		}
		return status;
	}

	public boolean verifyNamePresent(String name) throws InterruptedException {
		boolean status = false;
		int count = 0;
		while (count < 10) {
			try {
				driver.findElement(By.name(name));
				status = true;
				break;
			} catch (Exception e) {
				Thread.sleep(2000);
				count++;
			}
		}
		return status;
	}

	public String[] getRowData(String rowXpath) {
		String entireRowData = driver.findElement(By.xpath(rowXpath))
				.getText();
		String[] rowDataArray = entireRowData.split(" ");
		return rowDataArray;
	}

	public String[] getColumnData(String columnXpath) {

		List<WebElement> entireColumnData = driver.findElements(By
				.xpath(columnXpath));
		String[] columnDataArray = new String[entireColumnData.size()];
		for (int i = 0; i < entireColumnData.size(); i++) {
			columnDataArray[i] = entireColumnData.get(i).getText();
		}
		return columnDataArray;
	}

	public String[] clickAndGetRowData(String rowXpath,
			String clickableNextButtonXpath) {

		String[] rowDataArray = null;

		while (true) {

			try {
				String entireRowData = driver.findElement(
						By.xpath(rowXpath)).getText();
				rowDataArray = entireRowData.split(" ");
				break;
			} catch (NoSuchElementException e) {
				driver.findElement(By.xpath(clickableNextButtonXpath))
						.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
		}

		return rowDataArray;
	}
	
	public void acceptAlert(){
		
	}
	
	public void getWindowIds(){
		
	}
}
