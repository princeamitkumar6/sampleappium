package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSBasic extends IOSBaseTest {

	@Test
	public void IOSBasicTest() {

		// xpath, classname, IOS, iosClassChain, IOSPredicateString, accesibility id ,id
		iosDriver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
		iosDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == 'Text Entry'`]")).click();
		iosDriver.findElement(AppiumBy.iOSNsPredicateString("type == \"XCUIElementTypeTextField\"")).sendKeys("Hello World");
		iosDriver.findElement(AppiumBy.accessibilityId("OK")).click();
		
		iosDriver.findElement(AppiumBy.iOSNsPredicateString("name == \"Confirm / Cancel\"")).click();
		String text = iosDriver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND name BEGINSWITH[c] 'A message'")).getText();
		System.out.println(text);
		iosDriver.findElement(AppiumBy.iOSNsPredicateString("name == \"Confirm\"")).click();
		
		Assert.assertEquals(text, "A message should be a short, complete sentence.");
		
		//longpress , scroll, swipe, slides, dropdowns
	}

}
