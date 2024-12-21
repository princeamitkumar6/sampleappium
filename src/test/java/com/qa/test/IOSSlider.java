package com.qa.test;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSSlider extends IOSBaseTest{
	
	@Test
	public void IOSSliderTest() throws InterruptedException {
		
		// IOS Slider Test
		WebElement slider = iosDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSlider[`name == 'AppElem'`]"));
		slider.sendKeys("0%");
		System.out.println(slider.getDomAttribute("value"));
		Thread.sleep(2000);
		slider.sendKeys("1%");

	}

}
