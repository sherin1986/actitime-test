package com.actitime.pagefactorylibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewProject {
	@FindBy(name="name")
	private WebElement addNewProjectEdit;
	
	@FindBy(name="createProjectSubmit")
	private WebElement createProjectBtn;

	public WebElement getAddNewProjectEdit() {
		return addNewProjectEdit;
	}

	public WebElement getCreateProjectBtn() {
		return createProjectBtn;
	}
}
