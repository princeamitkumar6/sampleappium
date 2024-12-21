package com.qa.test;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class IOSSwipeTest extends IOSBaseTest {

	@Test
	public void IOSSwipeTestDemo() throws InterruptedException {
		
		//Bundle ID
		Map<String, Object> params = new HashMap<>();
		params.put("bundleId", "com.apple.mobileslideshow");
		
		iosDriver.executeScript("mobile:launchApp", params);
		
//		WebElement selectButton = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name='Select']"));
//		if (selectButton.isEnabled() && selectButton.isDisplayed()) {
//		    selectButton.click();
//		} else {
//		    System.out.println("Button is not clickable");
//		}
		
//		WebElement element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Select\"]"));
//		int x = element.getRect().getX() + (element.getRect().getWidth() / 2);
//		int y = element.getRect().getY() + (element.getRect().getHeight() / 2);
//		
//		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//
//		Sequence tap = new Sequence(finger, 0)
//		        .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
//		        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
//		        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//
//		iosDriver.perform(Arrays.asList(tap));
//
//		WebElement element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name='Select']"));
//		System.out.println("Is displayed: " + element.isDisplayed());
//		System.out.println("Is enabled: " + element.isEnabled());
//
//		((JavascriptExecutor) iosDriver).executeScript("mobile: scroll", ImmutableMap.of("direction", "down"));
//		WebElement element1 = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name='Select']"));
//		element1.click();
//		
//		WebElement element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name='Select']"));
//		System.out.println("Element attributes: ");
//		System.out.println("Is displayed: " + element.isDisplayed());
//		System.out.println("Is enabled: " + element.isEnabled());
//		System.out.println("Rect: " + element.getRect());
//
//		
//		Map<String, Object> args = new HashMap<>();
//		args.put("x", 263);
//		args.put("y", 61);
//		iosDriver.executeScript("mobile: tap", args);
//		
//		WebElement element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name='Select']"));
//		int centerX = element.getRect().getX() + (element.getRect().getWidth() / 2);
//		int centerY = element.getRect().getY() + (element.getRect().getHeight() / 2);
//		Map<String, Object> args = new HashMap<>();
//		args.put("x", centerX);
//		args.put("y", centerY);
//		iosDriver.executeScript("mobile: tap", args);
//		
//		WebDriverWait wait = new WebDriverWait(iosDriver, Duration.ofSeconds(10));
//		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeButton[@name='Select']")));
//		element.click();  // Try clicking after confirming the element is clickable
		
		//((JavascriptExecutor) iosDriver).executeScript("mobile: scroll", ImmutableMap.of("direction", ""));
		WebElement element1 = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"person.fill\"]"));
		((JavascriptExecutor) iosDriver).executeScript("arguments[0].click();", element1);





//		WebDriverWait wait = new WebDriverWait(iosDriver, Duration.ofSeconds(10));
//		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeButton[@name='Select']")));
//		element.click();
		
		
		List<WebElement> allphotos =iosDriver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeImage"));
		System.out.println(allphotos.size());
		
		Map<String, Object> params1 = new HashMap<>();
		params1.put("direction", "left");
		iosDriver.executeScript("mobile:swipe", params1);
		
	}
	
	public void swipeLeftIOS(By by, int timeOut) {
		WebDriverWait wait = new WebDriverWait(iosDriver, Duration.ofSeconds(0));
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "left");
		iosDriver.executeScript("mobile:swipe", params);
	}

}
