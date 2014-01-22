package com.actitime.pagefactorylibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ActiveProjectsAndCustomers {
	@FindBy(xpath = "//input[@value='Add New Customer']")
	private WebElement addNewCustomerBtn;

	@FindBy(xpath = "//input[@value='Add New Project']")
	private WebElement addNewProjectBtn;

	@FindBy(xpath = "//input[@value='Archive Selected Customers & Projects']")
	private WebElement archiveSelectedCustomerAndProjectBtn;

	public WebElement getAddNewCustomerBtn() {
		return addNewCustomerBtn;
	}

	public WebElement getAddNewProjectBtn() {
		return addNewProjectBtn;
	}

	public WebElement getArchiveSelectedCustomerAndProjectBtn() {
		return archiveSelectedCustomerAndProjectBtn;
	}
}
