package com.actitime.pagefactorylibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenTasks {
	@FindBy(xpath = "//input[@value='Add New Tasks']")
	private WebElement addNewTasksBtn;
	
	@FindBy(xpath = "//input[@value='Create Tasks']")
	private WebElement createTasksBtn;
	
	@FindBy(xpath = "//input[contains(@value,'Cancel')]")
	private WebElement cancelBtn;

	public WebElement getCreateTasksBtn() {
		return createTasksBtn;
	}

	public WebElement getCancelBtn() {
		return cancelBtn;
	}

	public WebElement getAddNewTasksBtn() {
		return addNewTasksBtn;
	}

}
