package com.actitime.buisnesslibrary;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

	public ArchivesLibrary() {
		dLibrary = new WebdriverCommonUtil();
		activeBtn = PageFactory.initElements(Driver.driver,
				ActiveProjectsAndCustomers.class);
		archiveBtn = PageFactory.initElements(Driver.driver, Archives.class);
		projectTasks = PageFactory.initElements(Driver.driver,
				ProjectsNTasks.class);
	}

	public void navigateToArchives() {
		projectTasks.getArchivesLink().click();
		dLibrary.waitForPageToLoad();
	}

	public void archiveCustomer(String customerName) {
		Driver.driver.findElement(
				By.xpath("//td[a[text()='" + customerName
						+ "']]/following-sibling::td[5]/input")).click();
		activeBtn.getArchiveSelectedCustomerAndProjectBtn().click();
		Alert archiveAlert = Driver.driver.switchTo().alert();
		archiveAlert.accept();
		dLibrary.waitForPageToLoad();

	}

	public void restoreCustomer(String customerName) {

		Driver.driver.findElement(
				By.xpath("//td[a[text()='" + customerName
						+ "']]/following-sibling::td[3]/input")).click();
		archiveBtn.getBtnRestoreSelectedCustomer().click();
		Alert restoreAlert = Driver.driver.switchTo().alert();
		restoreAlert.accept();
		dLibrary.waitForPageToLoad();

	}

	public void deleteCustomer(String customerName) {
		Driver.driver.findElement(
				By.xpath("//td/a[text()='" + customerName + "']")).click();
		dLibrary.waitForPageToLoad();
		Driver.driver.findElement(
				By.xpath("//input[@value='Delete This Customer']")).click();
		Alert deleteAlert = Driver.driver.switchTo().alert();
		deleteAlert.accept();
		dLibrary.waitForPageToLoad();

	}

}
