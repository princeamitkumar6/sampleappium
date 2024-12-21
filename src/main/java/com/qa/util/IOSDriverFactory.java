package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class IOSDriverFactory {

    private AndroidDriver androidDriver;
    private Properties prop;

    /**
     * Initializes and returns the AndroidDriver based on the provided properties.
     * @param prop Properties containing configuration values.
     * @return AndroidDriver instance.
     */
    public AndroidDriver init_android_driver(Properties prop) {
        String deviceType = prop.getProperty("devicetype", "").trim();

        if (deviceType.equalsIgnoreCase("android")) {
            return setupAndroidDriver(prop);
        } else if (deviceType.isEmpty()) {
            System.err.println("Device type not specified in the configuration.");
        } else {
            System.err.println("Unsupported device type: " + deviceType);
        }
        return null;
    }

    /**
     * Sets up and returns the AndroidDriver.
     * @param prop Properties containing configuration values.
     * @return AndroidDriver instance.
     */
    private AndroidDriver setupAndroidDriver(Properties prop) {
        try {
            // Set mobile options
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(prop.getProperty("devicename").trim());
            options.setApp(prop.getProperty("apkpath").trim());

            System.out.println("Using APK path: " + options.getApp());

            // Connect to the Android device
            androidDriver = new AndroidDriver(new URL(prop.getProperty("serverurl").trim()), options);
            System.out.println("AndroidDriver initialized successfully.");
        } catch (MalformedURLException e) {
            System.err.println("Failed to initialize AndroidDriver: Invalid server URL.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Failed to initialize AndroidDriver.");
            e.printStackTrace();
        }

        return androidDriver;
    }

    /**
     * Loads properties from the configuration file.
     * @return Properties loaded from the config file.
     */
    public Properties init_properties() {
        prop = new Properties();
        String configFilePath = "./src/test/resources/config/config.properties";

        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            prop.load(fis);
            System.out.println("Properties loaded successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("Configuration file not found at path: " + configFilePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error occurred while loading properties.");
            e.printStackTrace();
        }

        validateProperties(prop);
        return prop;
    }

    /**
     * Validates that required properties are present.
     * @param prop Properties to validate.
     */
    private void validateProperties(Properties prop) {
        if (prop.getProperty("devicetype") == null || prop.getProperty("devicetype").isEmpty()) {
            System.err.println("Error: 'devicetype' property is missing or empty in the config file.");
        }

        if (prop.getProperty("serverurl") == null || prop.getProperty("serverurl").isEmpty()) {
            System.err.println("Error: 'serverurl' property is missing or empty in the config file.");
        }

        if (prop.getProperty("apkpath") == null || prop.getProperty("apkpath").isEmpty()) {
            System.err.println("Error: 'apkpath' property is missing or empty in the config file.");
        }
    }
}


