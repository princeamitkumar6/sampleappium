//package com.qa.util;
//
//import org.testng.annotations.BeforeClass;
//import java.io.File;
//import java.time.Duration;
//import java.util.Properties;
//
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.asserts.SoftAssert;
//
//import com.zumlo.factory.AndroidDriverFactory;
//import com.zumlo.screens.android.ActivitiesScreen;
//import com.zumlo.screens.android.AdditionalSecuritySettingsScreen;
//import com.zumlo.screens.android.ChangePasswordScreen;
//import com.zumlo.screens.android.ChatScreen;
//import com.zumlo.screens.android.CongratulationScreen;
//import com.zumlo.screens.android.CreateNewPasswordScreen;
//import com.zumlo.screens.android.HomeScreen;
//import com.zumlo.screens.android.LandingScreen;
//import com.zumlo.screens.android.LoginScreen;
//import com.zumlo.screens.android.MentalHealthJournalingScreen;
//import com.zumlo.screens.android.EnterOtpScreen;
//import com.zumlo.screens.android.GoalsScreen;
//import com.zumlo.screens.android.PersonalDataScreen;
//import com.zumlo.screens.android.PrivacyPolicyScreen;
//import com.zumlo.screens.android.ProductTourScreen;
//import com.zumlo.screens.android.ProfileScreen;
//import com.zumlo.screens.android.PromptSuggestionsScreen;
//import com.zumlo.screens.android.QuestionScreenOne;
//import com.zumlo.screens.android.QuestionScreenTwo;
//import com.zumlo.screens.android.QuizScreen;
//import com.zumlo.screens.android.RecommendationsScreen;
//import com.zumlo.screens.android.QuestionScreenThree;
//import com.zumlo.screens.android.QuestionScreenFour;
//import com.zumlo.screens.android.QuestionScreenFive;
//import com.zumlo.screens.android.QuestionScreenSix;
//import com.zumlo.screens.android.QuestionScreenSeven;
//import com.zumlo.screens.android.RegisterNowScreen;
//import com.zumlo.screens.android.SettingsScreen;
//import com.zumlo.screens.android.SignupEnterNameScreen;
//import com.zumlo.screens.android.SocialConnectionBoostScreen;
//import com.zumlo.screens.android.StressManagementPlanScreen;
//import com.zumlo.screens.android.WelcomeScreen;
//import com.zumlo.screens.android.WellnessPlanScreen;
//import com.zumlo.screens.android.WellnessResultsConfirmationPage;
//
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.service.local.AppiumDriverLocalService;
//import io.appium.java_client.service.local.AppiumServiceBuilder;
//
//public class BaseTestAndroid {
//
//	protected AppiumDriverLocalService service;
//	protected AndroidDriverFactory driverFactory;
//	protected Properties prop;
//	protected AndroidDriver androidDriver;
//	protected ProductTourScreen productTourScreen;
//	protected LandingScreen landingScreen;
//	protected LoginScreen loginScreen;
//	protected HomeScreen homeScreen;
//	protected ProfileScreen profileScreen;
//	protected PersonalDataScreen personalDataScreen;
//	protected SettingsScreen settingScreen;
//	protected ChangePasswordScreen changePasswordScreen;
//	protected SignupEnterNameScreen signupEnterNameScreen;
//	protected RegisterNowScreen registerNowScreen;
//	protected EnterOtpScreen enterOtpScreen;
//	protected CongratulationScreen congratulationScreen;
//	protected CreateNewPasswordScreen createNewPasswordScreen;
//	protected AdditionalSecuritySettingsScreen additionalSecuritySettingsScreen;
//	protected WelcomeScreen welcomeScreen;
//	protected QuestionScreenOne questionScreen1;
//	protected QuestionScreenTwo questionScreen2;
//	protected QuestionScreenThree questionScreen3;
//	protected QuestionScreenFour questionScreen4;
//	protected QuestionScreenFive questionScreen5;
//	protected QuestionScreenSix questionScreen6;
//	protected QuestionScreenSeven questionScreen7;
//	protected WellnessResultsConfirmationPage wellnessResultsConfirmationPage;
//	protected PromptSuggestionsScreen promptSuggestionsScreen;
//	protected PrivacyPolicyScreen privacyPolicyScreen;
//	protected SoftAssert softAssert;
//	protected RecommendationsScreen recommendationsScreen;
//	protected QuizScreen quizScreen;
//	protected ActivitiesScreen activitiesScreen;
//	protected GoalsScreen goalsScreen;
//	protected WellnessPlanScreen wellnessPlanScreen;
//	protected ChatScreen chatScreen;
//	protected StressManagementPlanScreen stressManagementPlanScreen;
//	protected MentalHealthJournalingScreen mentalHealthJournalingScreen;
//	protected SocialConnectionBoostScreen socialConnectionBoostScreen;
//
//	@BeforeTest
//	public void startServer() {
//		try {
//			driverFactory = new AndroidDriverFactory();
//			prop = driverFactory.init_properties();
//
//			if (prop == null) {
//				throw new IllegalStateException("Failed to load properties. Check your configuration.");
//			}
//
//			if (service != null && service.isRunning()) {
//				service.stop();
//				System.out.println("Existing Appium server stopped.");
//			}
//
//			service = new AppiumServiceBuilder().withAppiumJS(new File(prop.getProperty("mainjspath").trim()))
//					.withIPAddress(prop.getProperty("ipaddress").trim())
//					.usingPort(Integer.parseInt(prop.getProperty("portnumber"))).withTimeout(Duration.ofSeconds(600))
//					.build();
//			service.start();
//
//			int retries = 5;
//			while (retries > 0 && !service.isRunning()) {
//				System.out.println("Waiting for Appium server to start...");
//				Thread.sleep(2000);
//				retries--;
//			}
//
//			if (!service.isRunning()) {
//				throw new IllegalStateException("Appium server failed to start after retries.");
//			} else {
//				System.out.println("Appium server started successfully on: " + prop.getProperty("ipaddress") + ":"
//						+ prop.getProperty("portnumber"));
//			}
//
//		} catch (Exception e) {
//			System.err.println("Error while starting Appium server: " + e.getMessage());
//			throw new IllegalStateException("Appium server start failed.", e);
//		}
//	}
//
//	@BeforeClass
//	public void androidSetup() {
//		try {
//			androidDriver = driverFactory.init_android_driver(prop);
//			if (androidDriver == null) {
//				throw new IllegalStateException("Android Driver failed to initialize.");
//			}
//			System.out.println("Android Driver initialized successfully.");
//
//			productTourScreen = new ProductTourScreen(androidDriver);
//			landingScreen = new LandingScreen(androidDriver);
//			softAssert = new SoftAssert();
//		} catch (Exception e) {
//			System.err.println("Error during Android setup: " + e.getMessage());
//			throw new IllegalStateException("Android setup failed.", e);
//		}
//	}
//
//	@AfterClass
//	public void tearDown() {
//		if (androidDriver != null) {
//			try {
//				androidDriver.quit();
//				System.out.println("Android Driver quit successfully.");
//			} catch (Exception e) {
//				System.err.println("Error during Android driver teardown: " + e.getMessage());
//			}
//		}
//	}
//
//	@AfterTest
//	public void closeServer() {
//		if (service != null) {
//			try {
//				service.stop();
//				System.out.println("Appium server stopped successfully.");
//			} catch (Exception e) {
//				System.err.println("Error while stopping Appium server: " + e.getMessage());
//			}
//		}
//	}
//
//}
