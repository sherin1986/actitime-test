package com.actitime.pagefactorylibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {

	@FindBy(name = "username")
	private WebElement userName;

	@FindBy(name = "pwd")
	private WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginButton;

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

}
