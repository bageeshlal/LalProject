package com.abc.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.swing.text.Document;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.fetch.Response;
import com.google.common.io.Files;

public class NewsTests extends ABCNewsBase{

		
	public NewsTests() {
		super();
	}

	/**
	 * Scenario 1:  Verify the page loads successful
	 *  http://www.abc.net.au/news/
	 */
	@Test
	public void checkHomePageLoadCompleted(){		
		driver.get(url+subUrl);
		Object response = jse.executeScript(
				"if(document.readyState === 'complete'){return true;}"
				+ "else {return false;}", "");
		Assert.assertTrue((Boolean)response, "Home Page not loaded successully");
	}
	
	/**
	 * Scenario 2:  Verify that news banner loads
	 *  http://www.abc.net.au/news/
	 */
	@Test
	public void checkNewsBannerLoaded(){
		driver.get(url+subUrl);
		Object response = jse.executeScript(
				"var bannerEle = document.getElementById('container_header');"
				+ "if(typeof(bannerEle) != 'undefined' && bannerEle != null){ return true; }"
				+ "else {return false}	", "");
		Assert.assertTrue((Boolean)response, "	News Banner not loaded successully");
	}
	
	/**
	 * Scenario 3: Verify can navigate to the 'Just In' page via the link on the primary navigation
	 */
	@Test
	public void verifyLinkNavigation(){
		driver.get(url+subUrl);
		Object response = jse.executeScript(
				"var ele = document.getElementsByTagName('a');"
				+ "var s = arguments[arguments.length - 1];"
				+ "for(var i = 0; i< ele.length; i++){if(ele[i].href===arguments[arguments.length - 1]){ ele[i].click();"
				+ "if(document.readyState === 'complete') {return true;}}}"
				+ "return false;",url+subUrl+"justin/");
				
		Assert.assertTrue((Boolean)response, "	Justin page not loaded successully");
	}
	
	/**
	 * Scenario 4:  Verify that on the Just In page that the content per article loads correctly, ie it must contain
	 * Title
	 * Author
	 * Timestamp
	 * Text
	 */
	@Test
	public void verifyContentPerArticle(){
		verifyLinkNavigation();
		@SuppressWarnings("unchecked")
		List<Object> response = (List<Object>) jse.executeScript(
				"var articles = document.querySelectorAll('ul.article-index li');"
				+ "var heading;"
				+ "var failedHeadingArray = []; "
				+ "for(var i = 0; i< articles.length; i++){"
				+ "heading = articles[i].children[0].children[0].innerText;"	
				+ "if(articles[i].children[0].tagName !== 'H3' || articles[i].children[0].children[0].tagName !=='A' || articles[i].children[0].children[0].href === ''){ failedHeadingArray.push('Title_'+heading); continue;}"
				+ "if(articles[i].children[1].tagName !== 'DIV' || articles[i].children[1].className !== 'byline'){ failedHeadingArray.push('Author_'+heading); continue;}"
				+ "if(articles[i].children[2].tagName !== 'P' || articles[i].children[2].className !== 'published' || articles[i].children[2].children[0].className !== 'timestamp'){failedHeadingArray.push('Timestamp_'+heading); continue;}"
				+ "if(articles[i].children[3].tagName !== 'P' || articles[i].children[3].innerText === ''){failedHeadingArray.push('Text_'+heading); continue;}"
				+ "}"
				+ "return failedHeadingArray;", "");
		if(response.size() > 0){
			Reporter.log("Follwoing articles have missing information!");
			for(int i =0 ; i< response.size(); i++){
				String[] info = response.get(i).toString().split("_");
				Reporter.log(info[0]+" missing for article \""+ info[1]+"\"");
			}
			Assert.fail("Missing information - Title/Author/TimeStamp/Text for article(s)");
		}
		
		
	}
	
	/**
	 * Scenario 5:  Verify that a video loads and appears successfully on the following page
	 * http://www.abc.net.au/news/2017-02-09/weatherill-promises-to-intervene-dramatically/8254908
	 */
	@Test
	public void verifyVedioLoadedSuccessfully(){
		driver.get(url+subUrl+videoUrl);
		Object response = jse.executeScript(
				 "if(document.readyState === 'complete'){"
				+ "var vid = document.getElementById('jwplayer-video-0_display');"
				+ "if(vid == null) {return false;} else {return true;}}", "");
		Assert.assertTrue(((Boolean)response).booleanValue(), "Video not loaded as expected");
	}
	
	/**
	 * Scenario 6: Verify that the Image Gallery successfully loads and images appear correctly
	 */
	@Test
	public void verifyImageLoad(){
		driver.get(url+subUrl+imgUrl);
		Object response = jse.executeScript(
				"if(document.readyState === 'complete'){"
				+ "var img = document.getElementsByClassName('imageGallery lightSlider lsGrab lSSlide');"
				+ "if(img == null) {return false;} else {return true;}}", "");
		Assert.assertTrue(((Boolean)response).booleanValue(), "Image not loaded as expected");
	}
	
}
