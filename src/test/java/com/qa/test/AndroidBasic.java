package com.qa.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBasic {

	@Test
	public void AppiumTest() throws MalformedURLException {

		// Start to Appium server 
		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
		// AndroidDriver , IOSDriver
		// Appium code -> appium server -> Mobile
		
		System.out.println("Server start sucessfully");

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 8"); // emulator
		options.setApp("/Users/ditsdev/eclipse-workspace/Appium/src/test/resource/apk-file/ApiDemos-debug.apk");
  

		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		

		driver.quit();
		service.stop();
	}

}
