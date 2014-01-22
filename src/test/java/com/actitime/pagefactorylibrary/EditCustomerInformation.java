package com.actitime.pagefactorylibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditCustomerInformation {
	@FindBy(name="name")
	private WebElement customerNameEdit;
	
	@FindBy(name="saveChanges")
	private WebElement saveChangesBtn;

	public WebElement getCustomerNameEdit() {
		return customerNameEdit;
	}

	public WebElement getSaveChangesBtn() {
		return saveChangesBtn;
	}
	
}
