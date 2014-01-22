package com.actitime.pagefactorylibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Archives {

	@FindBy(xpath = "//input[@value='Restore Selected Customers & Projects']")
	private WebElement btnRestoreSelectedCustomer;

	public WebElement getBtnRestoreSelectedCustomer() {
		return btnRestoreSelectedCustomer;
	}

}
