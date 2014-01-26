package com.actitime.buisnesslibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.actitime.genericlibraries.Driver;
import com.actitime.genericlibraries.WebdriverCommonUtil;
import com.actitime.pagefactorylibrary.ActiveProjectsAndCustomers;
import com.actitime.pagefactorylibrary.AddNewCustomer;
import com.actitime.pagefactorylibrary.AddNewProject;
import com.actitime.pagefactorylibrary.EditCustomerInformation;
import com.actitime.pagefactorylibrary.ProjectsNTasks;

public class ProjectsNCustomersLibrary {
	WebdriverCommonUtil dLibrary;
	ProjectsNTasks projectTasks;
	AddNewCustomer addCustomer;
	ActiveProjectsAndCustomers activeProjectnCustomer;
	AddNewProject addNewProject;
	EditCustomerInformation editCustomer;
	WebDriver driver;

	public ProjectsNCustomersLibrary(WebDriver driver) {
		this.driver = driver;

		dLibrary = new WebdriverCommonUtil(driver);
		projectTasks = PageFactory.initElements(driver, ProjectsNTasks.class);
		addCustomer = PageFactory.initElements(driver, AddNewCustomer.class);
		activeProjectnCustomer = PageFactory.initElements(driver,
				ActiveProjectsAndCustomers.class);
		addNewProject = PageFactory.initElements(driver, AddNewProject.class);
		editCustomer = PageFactory.initElements(driver,
				EditCustomerInformation.class);
	}

	public void navigateToCustomersAndProjects() {
		projectTasks.getProjectsNCustomerLink().click();
		dLibrary.waitForPageToLoad();
	}

	public void createCustomer(String actualName) {
		activeProjectnCustomer.getAddNewCustomerBtn().click();
		dLibrary.waitForPageToLoad();
		addCustomer.getCustomerName().sendKeys(actualName);
		addCustomer.getCreateCustomerButton().click();
		dLibrary.waitForPageToLoad();
	}

	public boolean verifyCustomer(String actualName) {
		boolean status = true;
		try {
			if (driver.findElement(By.linkText(actualName)).isDisplayed()) {
				driver.findElement(By.linkText(actualName)).click();
				dLibrary.waitForPageToLoad();
				String expectedName = driver
						.findElement(
								By.xpath("//td[contains(text(),'You have selected customer:')]/following-sibling::td/span"))
						.getText();
				Assert.assertEquals(actualName, expectedName);
				System.out.println(expectedName + " is verified successfully");
				// Checking whether customer is active
				System.out
						.println(expectedName
								+ " is "
								+ driver.findElement(
										By.xpath("//td[contains(text(),'Customer status:')]/following-sibling::td/span"))
										.getText());
			}
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	public boolean modifyCustomer(String nameToModify, String newName) {
		boolean status = true;
		try {
			if (driver.findElement(By.linkText(nameToModify)).isDisplayed()) {
				driver.findElement(By.linkText(nameToModify)).click();
				dLibrary.waitForPageToLoad();
				editCustomer.getCustomerNameEdit().clear();
				editCustomer.getCustomerNameEdit().sendKeys(newName);
				editCustomer.getSaveChangesBtn().click();
				dLibrary.waitForPageToLoad();
			}
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	public boolean createProject(String actualName, String projectName) {
		boolean status = true;
		try {
			if (driver.findElement(By.linkText(actualName)).isDisplayed()) {
				driver.findElement(
						By.xpath("//td[a[text()='" + actualName
								+ "']]/following-sibling::td[1]/a")).click();
				dLibrary.waitForPageToLoad();
				addNewProject.getAddNewProjectEdit().sendKeys(projectName);
				addNewProject.getCreateProjectBtn().click();
				dLibrary.waitForPageToLoad();

			}
		} catch (Exception e) {
			status = false;
		}
		return status;

	}

	public boolean verifyProject(String projectName) {
		boolean status = true;
		try {
			if (driver.findElement(By.linkText(projectName)).isDisplayed()) {
				driver.findElement(By.linkText(projectName)).click();
				dLibrary.waitForPageToLoad();
				String expectedName = driver
						.findElement(
								By.xpath("//td[contains(text(),'You have selected project:')]/following-sibling::td/span"))
						.getText();
				Assert.assertEquals(projectName, expectedName);
				System.out.println(expectedName + " is verified successfully");
				// Checking whether customer is active
				System.out
						.println(expectedName
								+ " is "
								+ driver.findElement(
										By.xpath("//td[contains(text(),'Project status:')]/following-sibling::td/span"))
										.getText());
			}
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

}
