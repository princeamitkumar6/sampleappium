package com.qa.test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSLongPress extends IOSBaseTest {
	
	// Line 1: Changes from kumar


	@Test
	public void IOSLongPressTest() {
		iosDriver.findElement(AppiumBy.accessibilityId("Steppers")).click();
		WebElement ele = iosDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'Increment'`][3]"));

		Map<String, Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("duration", 5);
		iosDriver.executeScript("mobile:touchAndHold", params);
	}
	private void Syso() {
		// TODO Auto-generated method stub

	}
	public void longpressIOS(By by, int timeOut) {
		WebDriverWait wait = new WebDriverWait(iosDriver, Duration.ofSeconds(0));
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		Map<String, Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("duration", 5);
		iosDriver.executeScript("mobile:touchAndHold", params);
	}

}
