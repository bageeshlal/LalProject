package com.abc.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class JSONBase {

	protected static WebDriver driver;
	JavascriptExecutor jse;	
	String jsonUrl, jsonSubUrl,program;
	
	
	public JSONBase() {
		super();
	}

	@BeforeMethod
	public void setup(){
		jsonUrl = "http://program.abcradio.net.au/";
		jsonSubUrl = "api/v1/programs/";
		program = "ppJj0E8g2R.json";
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		jse = (JavascriptExecutor)driver;
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
