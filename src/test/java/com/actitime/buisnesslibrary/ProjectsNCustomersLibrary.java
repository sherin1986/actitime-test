package com.actitime.buisnesslibrary;

import org.openqa.selenium.By;
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

	public ProjectsNCustomersLibrary() {
		dLibrary = new WebdriverCommonUtil();
		projectTasks = PageFactory.initElements(Driver.driver,
				ProjectsNTasks.class);
		addCustomer = PageFactory.initElements(Driver.driver,
				AddNewCustomer.class);
		activeProjectnCustomer = PageFactory.initElements(Driver.driver,
				ActiveProjectsAndCustomers.class);
		addNewProject = PageFactory.initElements(Driver.driver,
				AddNewProject.class);
		editCustomer = PageFactory.initElements(Driver.driver,
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
			if (Driver.driver.findElement(By.linkText(actualName))
					.isDisplayed()) {
				Driver.driver.findElement(By.linkText(actualName)).click();
				dLibrary.waitForPageToLoad();
				String expectedName = Driver.driver
						.findElement(
								By.xpath("//td[contains(text(),'You have selected customer:')]/following-sibling::td/span"))
						.getText();
				Assert.assertEquals(actualName, expectedName);
				System.out.println(expectedName + " is verified successfully");
				// Checking whether customer is active
				System.out
						.println(expectedName
								+ " is "
								+ Driver.driver
										.findElement(
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
			if (Driver.driver.findElement(By.linkText(nameToModify))
					.isDisplayed()) {
				Driver.driver.findElement(By.linkText(nameToModify)).click();
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
			if (Driver.driver.findElement(By.linkText(actualName))
					.isDisplayed()) {
				Driver.driver.findElement(
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
			if (Driver.driver.findElement(By.linkText(projectName))
					.isDisplayed()) {
				Driver.driver.findElement(By.linkText(projectName)).click();
				dLibrary.waitForPageToLoad();
				String expectedName = Driver.driver
						.findElement(
								By.xpath("//td[contains(text(),'You have selected project:')]/following-sibling::td/span"))
						.getText();
				Assert.assertEquals(projectName, expectedName);
				System.out.println(expectedName + " is verified successfully");
				// Checking whether customer is active
				System.out
						.println(expectedName
								+ " is "
								+ Driver.driver
										.findElement(
												By.xpath("//td[contains(text(),'Project status:')]/following-sibling::td/span"))
										.getText());
			}
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

}
