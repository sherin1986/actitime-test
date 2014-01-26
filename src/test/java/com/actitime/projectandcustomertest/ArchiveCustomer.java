package com.actitime.projectandcustomertest;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
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
import com.actitime.genericlibraries.WebdriverCommonUtil;
import com.actitime.pagefactorylibrary.ActiveProjectsAndCustomers;
import com.actitime.pagefactorylibrary.Archives;

public class ArchiveCustomer {
	CommonLibrary cLibrary;
	ProjectsNCustomersLibrary bLibrary;
	ExcelLibrary eLibrary;
	ArchivesLibrary aLibrary;
	ActiveProjectsAndCustomers activeBtn;
	Archives archiveBtn;
	WebdriverCommonUtil dLibrary;
	
	Driver driver;

	@BeforeClass
	public void bClass() throws InvalidFormatException, IOException {
		
		driver = new Driver();
		
		cLibrary = new CommonLibrary(driver.getWebDriver());
		bLibrary = new ProjectsNCustomersLibrary(driver.getWebDriver());
		eLibrary = new ExcelLibrary();
		dLibrary = new WebdriverCommonUtil(driver.getWebDriver());
		aLibrary = new ArchivesLibrary(driver.getWebDriver());
		activeBtn = PageFactory.initElements(driver.getWebDriver(),
				ActiveProjectsAndCustomers.class);
		archiveBtn = PageFactory.initElements(driver.getWebDriver(), Archives.class);
	}

	@BeforeMethod
	public void login() throws InvalidFormatException, IOException {
		cLibrary.getUrl();
		cLibrary.loginPage(eLibrary.getExcelData(5, 1, "Testcases"),
				eLibrary.getExcelData(5, 2, "Testcases"));
		bLibrary.navigateToCustomersAndProjects();
	}

	@Test
	public void archiveCustomer() throws InvalidFormatException, IOException {

		String customerName = eLibrary.getExcelData(5, 3, "Testcases");

		// Create customer and archive the same
		bLibrary.createCustomer(customerName);
		aLibrary.archiveCustomer(customerName);

		// Restore archived project
		aLibrary.navigateToArchives();
		dLibrary.waitForPageToLoad();
		aLibrary.restoreCustomer(customerName);

		// Deleting the restored project
		bLibrary.navigateToCustomersAndProjects();
		aLibrary.deleteCustomer(customerName);
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
