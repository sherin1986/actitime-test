package com.actitime.projectandcustomertest;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.actitime.buisnesslibrary.ArchivesLibrary;
import com.actitime.buisnesslibrary.CommonLibrary;
import com.actitime.buisnesslibrary.ProjectsNCustomersLibrary;
import com.actitime.genericlibraries.Driver;
import com.actitime.genericlibraries.ExcelLibrary;

public class CreateCustomerNProject {
	ProjectsNCustomersLibrary bLibrary;
	ExcelLibrary eLibrary;
	CommonLibrary cLibrary;
	ArchivesLibrary aLibrary;
	Driver driver;

	@BeforeClass
	public void bClass() throws InvalidFormatException, IOException {
		driver = new Driver();
		eLibrary = new ExcelLibrary();
		bLibrary = new ProjectsNCustomersLibrary(driver.getWebDriver());
		cLibrary = new CommonLibrary(driver.getWebDriver());
		aLibrary = new ArchivesLibrary(driver.getWebDriver());
	}

	@BeforeMethod
	public void bMethod() throws InvalidFormatException, IOException {
		cLibrary.getUrl();
		String username = eLibrary.getExcelData(2, 1, "Testcases");
		String password = eLibrary.getExcelData(2, 2, "Testcases");
		Assert.assertTrue(cLibrary.loginPage(username, password));
		bLibrary.navigateToCustomersAndProjects();
	}

	@Test
	public void createCustomer() throws InvalidFormatException, IOException {
		String actualName = eLibrary.getExcelData(2, 3, "Testcases");
		bLibrary.createCustomer(actualName);
		Assert.assertTrue(bLibrary.verifyCustomer(actualName),
				"Customer verification failed");
		bLibrary.navigateToCustomersAndProjects();
		aLibrary.deleteCustomer(actualName);
		//eLibrary.setExcelValue(2, 7, "Testcases", "PASS");

	}

	@Test(dependsOnMethods = { "createCustomer" })
	public void modifyCustomer() throws InvalidFormatException, IOException {
		String customerName = eLibrary.getExcelData(3, 3, "Testcases");
		String newName = eLibrary.getExcelData(3, 5, "Testcases");
		bLibrary.createCustomer(customerName);
		Assert.assertTrue(bLibrary.modifyCustomer(customerName, newName),
				"Customer doesnt exist or new customer to modify already exists");
		Assert.assertTrue(bLibrary.verifyCustomer(newName),
				"Customer verification failed");
		bLibrary.navigateToCustomersAndProjects();
		aLibrary.deleteCustomer(newName);
		//eLibrary.setExcelValue(3, 7, "Testcases", "PASS");

	}

	@Test(dependsOnMethods = { "modifyCustomer" })
	public void projectForCustomer() throws InvalidFormatException, IOException {
		String customerName = eLibrary.getExcelData(4, 3, "Testcases");
		String projectName = eLibrary.getExcelData(4, 4, "Testcases");
		bLibrary.createCustomer(customerName);
		Assert.assertTrue(bLibrary.createProject(customerName, projectName),
				"Project creation failed");
		Assert.assertTrue(bLibrary.verifyProject(projectName),
				"Project verification failed");
		bLibrary.navigateToCustomersAndProjects();
		aLibrary.deleteProject(projectName);
		aLibrary.deleteCustomer(customerName);
		//eLibrary.setExcelValue(4, 7, "Testcases", "PASS");
	}

	@AfterMethod
	public void aMethod() {
		cLibrary.logout();
	}

	@AfterClass
	public void aClass() {
		driver.quit();
	}
}
