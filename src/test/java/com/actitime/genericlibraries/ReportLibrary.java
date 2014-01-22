package com.actitime.genericlibraries;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ReportLibrary {
	
	public void getScreenShot() throws IOException
	{
		EventFiringWebDriver ef = new EventFiringWebDriver(Driver.driver);
		File srcImg = ef.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(srcImg, new File("G:\\WSpaces\\Qspiders\\actiTime\\Screenshot"));
	}
	
	public Logger getLogs(Class className)
	{
		Logger log = Logger.getLogger(className);
		BasicConfigurator.configure();
		return log;
	}

}
