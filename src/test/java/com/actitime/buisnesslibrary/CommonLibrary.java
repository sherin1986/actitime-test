package com.actitime.buisnesslibrary;

import org.openqa.selenium.support.PageFactory;

import com.actitime.genericlibraries.Driver;
import com.actitime.genericlibraries.WebdriverCommonUtil;
import com.actitime.pagefactorylibrary.CommonPage;
import com.actitime.pagefactorylibrary.Login;

public class CommonLibrary {
	WebdriverCommonUtil dLibrary;
	Login login;
	CommonPage commonPage;

	public CommonLibrary() {
		dLibrary = new WebdriverCommonUtil();
		login = PageFactory.initElements(Driver.driver, Login.class);
		commonPage = PageFactory.initElements(Driver.driver, CommonPage.class);
	}

	public boolean loginPage(String username, String password) {
		boolean status = true;
		try {
			login.getUserName().sendKeys(username);
			login.getPassword().sendKeys(password);
			login.getLoginButton().click();
			dLibrary.waitForPageToLoad();
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public void logout() {
		commonPage.getLogoutBtn().click();

	}
}
