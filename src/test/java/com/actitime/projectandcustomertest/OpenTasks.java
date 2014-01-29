package com.actitime.projectandcustomertest;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.actitime.buisnesslibrary.ArchivesLibrary;
import com.actitime.buisnesslibrary.CommonLibrary;
import com.actitime.buisnesslibrary.OpenTasksLibrary;
import com.actitime.buisnesslibrary.ProjectsNCustomersLibrary;
import com.actitime.genericlibraries.Driver;
import com.actitime.genericlibraries.ExcelLibrary;
import com.actitime.genericlibraries.WebdriverCommonUtil;

public class OpenTasks {
	ExcelLibrary eLibrary;
	OpenTasksLibrary oLibrary;
	CommonLibrary cLibrary;
	ProjectsNCustomersLibrary bLibrary;
	com.actitime.pagefactorylibrary.OpenTasks taskBtn;
	WebdriverCommonUtil dLibrary;
	ArchivesLibrary aLibrary;
	Driver driver;


	@BeforeClass
	public void bClass() throws InvalidFormatException, IOException {
		driver = new Driver();
		bLibrary = new ProjectsNCustomersLibrary(driver.getWebDriver());
		aLibrary = new ArchivesLibrary(driver.getWebDriver());
		eLibrary = new ExcelLibrary();
		oLibrary = new OpenTasksLibrary(driver.getWebDriver());
		cLibrary = new CommonLibrary(driver.getWebDriver());
		taskBtn = PageFactory.initElements(driver.getWebDriver(),
				com.actitime.pagefactorylibrary.OpenTasks.class);
		dLibrary = new WebdriverCommonUtil(driver.getWebDriver());
	}

	@BeforeMethod
	public void login() throws InvalidFormatException, IOException {
		cLibrary.getUrl();
		Assert.assertTrue("Login failed", cLibrary.loginPage(eLibrary.getExcelData(6, 1, "Testcases"),
				eLibrary.getExcelData(6, 2, "Testcases")));
	}

	@Test
	public void addNewTasks() throws InvalidFormatException, IOException {

		// Create new tasks and customer and deleting them
		taskBtn.getAddNewTasksBtn().click();
		dLibrary.waitForPageToLoad();
		String customerName = eLibrary.getExcelData(6, 3, "Testcases");
		String projectName = eLibrary.getExcelData(6, 4, "Testcases");
		Select customerSelect = new Select(driver.getWebDriver().findElement(By
				.name("customerId")));
		Select projectSelect = new Select(driver.getWebDriver().findElement(By
				.name("projectId")));
		List<WebElement> customerList = customerSelect.getOptions();
		int flag = 0;
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getText().equals(customerName)) {
				customerSelect.selectByVisibleText(customerName);
				projectSelect.selectByVisibleText(projectName);
				flag = 1;
				break;
			}
		}
		if (flag == 0) {
			customerSelect.selectByVisibleText("-- new customer --");
			driver.getWebDriver().findElement(By.name("customerName")).sendKeys(
					customerName);
			driver.getWebDriver().findElement(By.name("projectName")).sendKeys(
					projectName);
		}

		Assert.assertTrue("Task creation failed", oLibrary.createTasks("Tasks"));
		if(flag==0){
			bLibrary.navigateToCustomersAndProjects();
			Assert.assertTrue("Project deletion failed", aLibrary.deleteProject(projectName));
			Assert.assertTrue("Customer deletion failed",aLibrary.deleteCustomer(customerName));
		}

	}

	@AfterMethod
	public void aMethod() {
		cLibrary.logout();
	}

	@AfterClass
	public void aClass(){
		driver.quit();
	}

}
