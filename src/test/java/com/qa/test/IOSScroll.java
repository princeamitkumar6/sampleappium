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

public class IOSScroll extends IOSBaseTest {

	@Test
	public void IOSScrollTest() throws InterruptedException {
		
		WebElement ele = iosDriver.findElement(AppiumBy.accessibilityId("Web View"));

        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("element", ((RemoteWebElement) ele).getId());

        iosDriver.executeScript("mobile:scroll", params);

		
		//iosDriver.executeScript("mobile:scroll", params);
		iosDriver.findElement(AppiumBy.iOSNsPredicateString("name == \"Web View\"")).click();
		Thread.sleep(2000);
		iosDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"UIKitCatalog\"]")).click();
		iosDriver.findElement(AppiumBy.accessibilityId("Picker View")).click();
		iosDriver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("100");
		
		iosDriver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("255");

		iosDriver.findElement(AppiumBy.accessibilityId("Blue color component value")).sendKeys("10");

	}
	
	public void scrollIOS(By by, int timeOut) {
		WebDriverWait wait = new WebDriverWait(iosDriver, Duration.ofSeconds(0));
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("element", ((RemoteWebElement) ele).getId());
        iosDriver.executeScript("mobile:scroll", params);
	}

}
