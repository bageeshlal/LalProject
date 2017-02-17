package com.abc.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class JsonTest extends JSONBase{

		
	@Test
	@Parameters({"jsonUrl","fileName"})
	public void verifyJsonOutput(String jsonUrl, String fileName) throws IOException{
		driver.get(jsonUrl);
		
		String data = FileUtils.readFileToString(new File(fileName));
		Map<String, String> map = new HashMap<String, String>();
		String actual = data.substring(1,data.length()-1);
		String[] actualArr = actual.split("\",");
		for(String entry : actualArr){
			map.put(entry.split(":")[0], entry.split(":")[1]);
		}
		
		Object response = jse.executeScript(
				"return document.getElementsByTagName('pre')[0].innerHTML;", "");
		String jsondata = response.toString();
		String expected = jsondata.substring(1, jsondata.length()-1);
		String[] expectedArr = expected.split("\",");
		Map<String,String> expectedMap = new HashMap<String, String>();
		for(String entry : expectedArr){
			expectedMap.put(entry.split(":")[0], entry.split(":")[1]);
		}
		
		//Verification
		Assert.assertEquals(map, expectedMap,"Expected and actual json differs");
	}

}
