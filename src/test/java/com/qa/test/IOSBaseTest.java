package com.qa.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSBaseTest {

    public IOSDriver iosDriver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void configAppium() {
    	
    	// Start IOS server
        try {
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .build();
            service.start();
            
            // Option to set
            XCUITestOptions options = new XCUITestOptions();
            options.setDeviceName("iPhone 16");
            options.setApp("/Users/ditsdev/eclipse-workspace/Appium/src/test/resource/TestApp 3.app");
            options.setPlatformVersion("18.1");
            options.setWdaLaunchTimeout(Duration.ofSeconds(60));

            // Start android driver
            iosDriver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
            iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to configure Appium", e);
        }
    }

    // Close the server 
    @AfterClass
    public void tearDown() {
        if (iosDriver != null) {
            iosDriver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}
