package com.abc.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class ABCRadioBase {

	protected static WebDriver driver;
	JavascriptExecutor jse;	
	String url, subUrl, radioUrl,audioUrl,audioUrl2;
	
	
	public ABCRadioBase() {
		super();
	}


	@BeforeMethod
	public void setup(){
		url = "http://www.abc.net.au/";
		subUrl = "news/";
		radioUrl = "radionational/";
		audioUrl = "programs/bigideas/a-fortunate-universe/8076406/";
		audioUrl2 ="https://radio.abc.net.au/programitem/pg1aGbWlx6?play=true";
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		jse = (JavascriptExecutor)driver;
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
