package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AndroidDriverFactory {

	private AndroidDriver androidDriver;
	private Properties prop;
	// JUST ADDING COMMENT 
	public enum DeviceType {
		ANDROID, IOS;

		public static DeviceType fromString(String deviceType) {
			try {
				return DeviceType.valueOf(deviceType.toUpperCase());
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Unsupported device type: " + deviceType);
			}
		}
	}

	public AndroidDriver init_android_driver(Properties prop) {
		DeviceType deviceType = DeviceType.fromString(prop.getProperty("devicetype", "").trim());

		switch (deviceType) {
		case ANDROID:
			return setupAndroidDriver(prop);
		case IOS:
			throw new UnsupportedOperationException("iOS support is not implemented yet.");
		default:
			throw new IllegalArgumentException("Unsupported device type: " + deviceType);
		}
	}

	private AndroidDriver setupAndroidDriver(Properties prop) {
		try {
			// Validate device-specific properties
			String deviceName = prop.getProperty("devicename", "").trim();
			if (deviceName.isEmpty()) {
				throw new IllegalArgumentException("Device name not specified in the configuration.");
			}

			String apkPath = prop.getProperty("apkpath", "").trim();
			if (apkPath.isEmpty()) {
				throw new IllegalArgumentException("APK path not specified in the configuration.");
			}

			String serverUrl = prop.getProperty("serverurl", "").trim();
			if (serverUrl.isEmpty()) {
				throw new IllegalArgumentException("Server URL not specified in the configuration.");
			}

			// Set mobile options
			UiAutomator2Options options = new UiAutomator2Options().setDeviceName(deviceName).setApp(apkPath);

			System.out.println("Using APK path: " + options.getApp());

			// Connect to the Android device
			AndroidDriver driver = new AndroidDriver(new URL(serverUrl), options);
			System.out.println("AndroidDriver initialized successfully.");
			return driver;

		} catch (MalformedURLException e) {
			throw new IllegalStateException("Invalid server URL: " + prop.getProperty("serverurl"), e);
		} catch (Exception e) {
			throw new IllegalStateException("Failed to initialize AndroidDriver.", e);
		}
	}

	public Properties init_properties() {
		prop = new Properties();
		String configFilePath = System.getProperty("config.file", "./src/test/resources/config/config.properties");

		try (FileInputStream fis = new FileInputStream(configFilePath)) {
			prop.load(fis);
			validateProperties(prop);
			return prop;
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("Configuration file not found at path: " + configFilePath, e);
		} catch (IOException e) {
			throw new IllegalStateException("Error occurred while loading properties.", e);
		}
	}

	private void validateProperties(Properties prop) {
		String[] requiredKeys = { "devicetype", "serverurl", "apkpath", "devicename" };
		for (String key : requiredKeys) {
			if (prop.getProperty(key) == null || prop.getProperty(key).isEmpty()) {
				throw new IllegalArgumentException(
						"Error: '" + key + "' property is missing or empty in the config file.");
			}
		}
	}

}
