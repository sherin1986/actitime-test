package com.actitime.buisnesslibrary;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.actitime.genericlibraries.Driver;
import com.actitime.genericlibraries.ExcelLibrary;
import com.actitime.genericlibraries.WebdriverCommonUtil;
import com.actitime.pagefactorylibrary.ProjectsNTasks;

public class OpenTasksLibrary {

	WebdriverCommonUtil dLibrary;
	ExcelLibrary eLibrary;
	ProjectsNTasks projectTasks;
	com.actitime.pagefactorylibrary.OpenTasks taskBtn;

	public OpenTasksLibrary() throws InvalidFormatException, IOException {
		dLibrary = new WebdriverCommonUtil();
		eLibrary = new ExcelLibrary();
		projectTasks = PageFactory.initElements(Driver.driver,
				ProjectsNTasks.class);
		taskBtn = PageFactory.initElements(Driver.driver,
				com.actitime.pagefactorylibrary.OpenTasks.class);
	}

	public void navigateToOpenTasks() {
		projectTasks.getOpenTasksLink().click();
		dLibrary.waitForPageToLoad();
	}

	public void createTasks(String sheetName) throws InvalidFormatException,
			IOException {
		// Adding tasks and deleting them after creation
		int taskIndex = 0;
		for (int j = 0; j < eLibrary.getRowCount(sheetName); j++) {

			if ("5".equals(eLibrary.getCellData(sheetName, j, 0))) {
				String taskName = eLibrary.getExcelData(j, 1, sheetName);
				String deadLine = eLibrary.getExcelData(j, 2, sheetName);
				String billabilityStatus = eLibrary.getExcelData(j, 3,
						sheetName);
				String task = "task[" + taskIndex + "]";
				Driver.driver.findElement(By.name(task + ".name")).sendKeys(
						taskName);
				Driver.driver.findElement(By.name(task + ".deadline"))
						.sendKeys(deadLine);

				Select billabilitySelect = new Select(
						Driver.driver.findElement(By
								.name(task + ".billingType")));

				List<WebElement> billabilityList = billabilitySelect
						.getOptions();
				for (int i = 0; i < billabilityList.size(); i++) {
					if (billabilityList.get(i).getText()
							.equals(billabilityStatus)) {
						billabilitySelect.selectByIndex(i);
					}
				}
				taskIndex++;
			}
		}
		taskBtn.getCreateTasksBtn().click();
		dLibrary.waitForPageToLoad();

		for (int j = 0; j < eLibrary.getRowCount(sheetName); j++) {

			if ("5".equals(eLibrary.getCellData("Tasks", j, 0))) {
				String taskName = eLibrary.getExcelData(j, 1, sheetName);
				Driver.driver.findElement(
						By.xpath("//td[a[text()='" + taskName
								+ "']]/following-sibling::td[4]/a")).click();
				Alert deleteTaskAlert = Driver.driver.switchTo().alert();
				deleteTaskAlert.accept();
				dLibrary.waitForPageToLoad();
			}
		}
	}
}
