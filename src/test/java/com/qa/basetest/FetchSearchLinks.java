package com.qa.basetest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FetchSearchLinks {

	public WebDriver driver;
	private By sbxChrome = By.name("q");
	private By txtSearchSuggestionList = By.xpath("//div[@class='OBMEnb']/ul/li");

	private static final String url = "https://www.google.com";
	private static final String[] searchKeywords = { "meditation", "yoga", "depression", "anxiety" };

	@Test
	public void testDemo() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.get(url);

		Random random = new Random();
		String seachtext = searchKeywords[random.nextInt(searchKeywords.length)];

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(sbxChrome)).sendKeys(seachtext);

		wait.until(ExpectedConditions.visibilityOfElementLocated((txtSearchSuggestionList)));

		List<WebElement> allSeachTextList = driver.findElements(txtSearchSuggestionList);

		for (WebElement e : allSeachTextList) {
			System.out.println(e.getText());
			if (e.getText().contains(seachtext)) {
				e.click();
				break;
			}
		}
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText(seachtext)));

		List<WebElement> links = driver.findElements(By.partialLinkText(seachtext));

		Set<String> uniqueLinks = new HashSet<>();
		int count = 0;

		for (WebElement e : links) {
			String linkUrl = e.getDomProperty("href");

			if (linkUrl != null && linkUrl.startsWith("https://") && !linkUrl.contains("wikipedia.org")
					&& !linkUrl.contains("facebook.com") && !linkUrl.contains("instagram.com")
					&& !linkUrl.contains("youtube.com") && uniqueLinks.add(linkUrl)) {
				System.out.println(linkUrl);
				count++;
			}

			if (count >= 3) {
				break;
			}
		}

		driver.quit();
	}
}
