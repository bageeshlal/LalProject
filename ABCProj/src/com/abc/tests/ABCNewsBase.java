package com.abc.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class ABCNewsBase {

	protected static WebDriver driver;
	JavascriptExecutor jse;	
	String url, subUrl, videoUrl,imgUrl;
	
	
	public ABCNewsBase() {
		super();
	}


	@BeforeMethod
	public void setup(){
		url = "http://www.abc.net.au/";
		subUrl = "news/";
		videoUrl = "2017-02-09/weatherill-promises-to-intervene-dramatically/8254908";
		imgUrl = " 2017-02-10/abc-open-pic-of-the-week/8256256";
		//System.setProperty("webdriver.gecho.driver","C://Bageesh//Work//Selenium_Drivers//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		jse = (JavascriptExecutor)driver;
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
