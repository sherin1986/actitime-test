package com.actitime.pagefactorylibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewCustomer {
	
	@FindBy(name="name")
	private WebElement CustomerName;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement createCustomerButton;

	public WebElement getCustomerName() {
		return CustomerName;
	}

	public WebElement getCreateCustomerButton() {
		return createCustomerButton;
	}
	

}
