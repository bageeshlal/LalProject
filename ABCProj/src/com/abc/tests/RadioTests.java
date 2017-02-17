package com.abc.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

public class RadioTests extends ABCRadioBase{

	/**
	 * Scenario 1: Verify can navigate to a 'Program' from the Programs Submenu
	 */
	@Test
	@Parameters("program")
	public void verifyRadioProgramSubMenuNavigation(String program){
		boolean flag;
		driver.get(url+radioUrl);
		jse.executeScript(
				"if(document.readyState === 'complete'){"
				+ "var prgSubMenu = document.querySelectorAll('#rn-programindex')[0].children;"
				+ "for(var i = 0; i<prgSubMenu.length; i++){"
				+ "var subMenu = prgSubMenu[i].children[0];"
				+ "var linkText = subMenu.innerText;"
				+ "if(subMenu.tagName === 'A' && linkText ===  arguments[arguments.length-1]){ subMenu.click();}"
				+ "}}",program);
		Object response = jse.executeScript("return document.title;", "");		
		if(program.equals("All Programs")){
			flag = response.toString().contains("Programs")?true:false;
		}
		else{
			flag = response.toString().contains(program)?true:false;
		}
		Assert.assertTrue(flag, "Page not navigated to "+program+"  page as expected");	
	}
	
	/**
	 * Scenario 2: Navigate to last item in the 'Program guide' banner (underneath the primary navigation) and select the last program
	 */
	@Test
	public void selectLastProgramFromProgramGuide(){
		driver.get(url+radioUrl);
		Object response = jse.executeScript(
				"if(document.readyState === 'complete'){"
				+ "var prgs = document.querySelectorAll('.at-a-glance')[0].children;"
				+ "var lastEle = prgs[prgs.length-2].children[0];"
				+ "lastEle.click(); return lastEle.children[0].children[1].innerText;}", "");
		Object title = jse.executeScript(
				"if(document.readyState === 'complete'){"
				+ "return document.title;}", "");
		Assert.assertTrue(title.toString().contains(response.toString()), "Page not navigated to program page as expected.");
	}
	
	/**
	 * Scenario 3: Verify can search for the content in the search bar and that content is returned
	 */
	@Test
	@Parameters("searchContent")
	public void searchContentInSearcgBar(String searchContent){
		driver.get(url+radioUrl);
		jse.executeScript(
				"if(document.readyState === 'complete'){"
				+ "document.getElementById('search-simple-input-query').value=arguments[arguments.length-1];"
				+ "document.getElementById('search-simple-input-submit').click();}",searchContent);
		Object response = jse.executeScript(
				 "if(document.readyState === 'complete'){"
				+ "var searchHeader = document.querySelector('.ct-search-header');"
				+ "function waitForEle(){ if(searchHeader == null) waitForEle();}"
				+ "waitForEle();"
				+ "var txt1 = searchHeader.children[0].innerText;"
				+ "var txt2 = searchHeader.children[1].innerText;"
				+ "return ((txt1.concat('_')).concat(txt2));"
				+ "}", "");
		String[] resp = response.toString().split("_");
		Assert.assertTrue(resp[0].contains(searchContent) && resp[1].toString().contains(searchContent), "Search is not successful as expected");
	}
	
	/**
	 * Scenario 4: Verify you can click on Social Media 'Share' icon and the correct pop-up appears.
	 */
	//Not completed
	@Test
	public void verifySocialMediaButtons(){
		driver.get(url+radioUrl+audioUrl);
		jse.executeScript(
				"if(document.readyState==='complete'){"
				+ "document.getElementsByTagName('iframe')[0].onload = function(){"
				//+ "document.querySelector('div.fb-share-button.fb_iframe_widget').click();"
				+ "document.getElementsByTagName('iframe')[0].click();"
				+ "document.querySelector('button[title=Share]').click();}}"
				, "");
		
		System.out.println();
	}
	
	/**
	 * Scenario 5: Verify that you can click on 'Download Audio' you are directed to the mp3 file
	 */
	@Test
	public void verifyDownloadClickPayAudio(){
		driver.get(url+radioUrl+audioUrl);
		jse.executeScript(
				"if(document.readyState === 'complete'){"
				+ "document.querySelector('.cs-has-media').children[1].children[0].click();}", "");
		//System.out.println();
		Object response = jse.executeScript(
				"if(document.readyState === 'complete'){"
				+ "var mediaType = document.querySelector('video');"
				+ "if(mediaType != null) return true;"
				+"}", "");
		Assert.assertTrue(((Boolean)response).booleanValue(), "Audio not playing as expected");
	}
	
	/**
	 * Scenario 6: Verify that you can click on 'Listen now' and you are redirected to the following url
	 */
	@Test
	public void verifyUrlOnClickingListenNow(){
		driver.get(url+radioUrl+audioUrl);
		Object response = jse.executeScript(
				"if(document.readyState === 'complete'){"
				+ "document.querySelector('.cs-has-media').children[0].children[0].click();"
				+ "if(documet.readyState === 'complete'){"
				+ "var currUrl = window.location.href;"
				+ "return currUrl;}}", "");
		Assert.assertTrue(response.toString().equalsIgnoreCase("https://radio.abc.net.au/programitem/pg1aGbWlx6?play=true"), "Url not redirected as expected.");
	}
	
	/**
	 * Scenario 7 : Verify that the audio player loads successfully when you load url: https://radio.abc.net.au/programitem/pg1aGbWlx6?play=true 
	 */
	@Test
	public void verifyAudioLoadedSuccessfully(){
		driver.get(audioUrl2);
		Object response = jse.executeScript(
				"if(document.readyState === 'complete'){"
				+ "if(document.getElementsByClassName('rp__playingItem__overlay')[0] != null && document.getElementById('player') != null){"
				+ "return true;}}", "");
		Assert.assertTrue(((Boolean)response).booleanValue(), "Audio not loaded as expected");
	}
}
