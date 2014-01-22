package com.actitime.pagefactorylibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectsNTasks {

	@FindBy(linkText = "Open Tasks")
	private WebElement openTasksLink;

	@FindBy(linkText = "Completed Tasks")
	private WebElement completedTasksLink;

	@FindBy(linkText = "Projects & Customers")
	private WebElement projectsNCustomerLink;

	@FindBy(linkText = "Archives")
	private WebElement archivesLink;

	public WebElement getOpenTasksLink() {
		return openTasksLink;
	}

	public WebElement getCompletedTasksLink() {
		return completedTasksLink;
	}

	public WebElement getArchivesLink() {
		return archivesLink;
	}

	public WebElement getProjectsNCustomerLink() {
		return projectsNCustomerLink;
	}

}
