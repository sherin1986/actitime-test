package com.actitime.buisnesslibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.actitime.genericlibraries.Driver;
import com.actitime.genericlibraries.WebdriverCommonUtil;
import com.actitime.pagefactorylibrary.CommonPage;
import com.actitime.pagefactorylibrary.Login;

public class CommonLibrary {
	WebdriverCommonUtil dLibrary;
	Login login;
	CommonPage commonPage;
	
	WebDriver driver;

	public CommonLibrary(WebDriver driver) {
		this.driver = driver;
		
		dLibrary = new WebdriverCommonUtil(driver);
		login = PageFactory.initElements(driver, Login.class);
		commonPage = PageFactory.initElements(driver, CommonPage.class);
	}

	public void getUrl() {
		driver.get("http://meenu-hp:8090/login.do");
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
