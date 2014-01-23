package com.actitime.buisnesslibrary;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.actitime.genericlibraries.Driver;
import com.actitime.genericlibraries.WebdriverCommonUtil;
import com.actitime.pagefactorylibrary.ActiveProjectsAndCustomers;
import com.actitime.pagefactorylibrary.Archives;
import com.actitime.pagefactorylibrary.ProjectsNTasks;

public class ArchivesLibrary {
	WebdriverCommonUtil dLibrary;
	ActiveProjectsAndCustomers activeBtn;
	Archives archiveBtn;
	ProjectsNTasks projectTasks;
	
	private WebDriver driver;

	public ArchivesLibrary(WebDriver driver) {
		this.driver = driver;
		
		dLibrary = new WebdriverCommonUtil(driver);
		activeBtn = PageFactory.initElements(driver,
				ActiveProjectsAndCustomers.class);
		archiveBtn = PageFactory.initElements(driver, Archives.class);
		projectTasks = PageFactory.initElements(driver,
				ProjectsNTasks.class);
	}

	public void navigateToArchives() {
		projectTasks.getArchivesLink().click();
		dLibrary.waitForPageToLoad();
	}

	public void archiveCustomer(String customerName) {
		driver.findElement(
				By.xpath("//td[a[text()='" + customerName
						+ "']]/following-sibling::td[5]/input")).click();
		activeBtn.getArchiveSelectedCustomerAndProjectBtn().click();
		Alert archiveAlert = driver.switchTo().alert();
		archiveAlert.accept();
		dLibrary.waitForPageToLoad();

	}

	public void restoreCustomer(String customerName) {

		driver.findElement(
				By.xpath("//td[a[text()='" + customerName
						+ "']]/following-sibling::td[3]/input")).click();
		archiveBtn.getBtnRestoreSelectedCustomer().click();
		Alert restoreAlert = driver.switchTo().alert();
		restoreAlert.accept();
		dLibrary.waitForPageToLoad();

	}

	public void deleteCustomer(String customerName) {
		driver.findElement(
				By.xpath("//td/a[text()='" + customerName + "']")).click();
		dLibrary.waitForPageToLoad();
		driver.findElement(
				By.xpath("//input[@value='Delete This Customer']")).click();
		Alert deleteAlert = driver.switchTo().alert();
		deleteAlert.accept();
		dLibrary.waitForPageToLoad();

	}
	public void deleteProject(String projectName){
		driver.findElement(
				By.xpath("//td/div/a[text()='" + projectName + "']")).click();
		dLibrary.waitForPageToLoad();
		driver.findElement(
				By.xpath("//input[@value='Delete This Project']")).click();
		Alert deleteAlert = driver.switchTo().alert();
		deleteAlert.accept();
		dLibrary.waitForPageToLoad();
	}

}
