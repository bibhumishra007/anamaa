package com.medsys.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;


public class Login {
	public String baseUrl;
	WebDriver driver;
	/*
	@Test
	public void lunchBrowser() {
		System.out.println("Lunch Chrome Browser.");
		WebDriverManager.chromedriver().setup();
		DesiredCapabilities caps = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
	}

	@Test
	public void login() {
		System.out.println("Login to App");
	}

	@Test
	public void url() {
		System.out.println("Provide URL");
		baseUrl = "https://perf2cluster1.mepstaging.medsyshealth.com/emrmegax/#/login";
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
	}

	@Test
	public void credential() throws InterruptedException {
		System.out.println("Provide username and Password");
		driver.findElement(By.id("mat-input-0")).sendKeys("Rocky");  //Username
		driver.findElement(By.id("mat-input-1")).sendKeys("123", Keys.ENTER);  //Password and Sign in button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		String ExpTitle = "MedSys";
		String ActTitle = driver.getTitle();
		assertEquals(ExpTitle, ActTitle);
		Thread.sleep(6000);
	}

	@Test
	public void rpm() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.findElement(By.cssSelector("img[class='mat-tooltip-trigger img-fluid floating imagedashboard']")).click(); //Select RPM module
		Thread.sleep(6000);
	}

	@Test
	public void closeBrowser(){
		driver.quit();
	}
*/
	
	@Test
	public void loginMedSys() throws InterruptedException {
		try {
			WebDriverManager.chromedriver().setup();
			DesiredCapabilities caps = new DesiredCapabilities();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);

			baseUrl = "https://perf2cluster1.mepstaging.medsyshealth.com/emrmegax/#/login";
			driver.get(baseUrl);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));

			driver.findElement(By.id("mat-input-0")).sendKeys("Rocky");  //Username
			driver.findElement(By.id("mat-input-1")).sendKeys("1234", Keys.ENTER);  //Password and Sign in button
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

			//driver.sendKeys(Keys.ENTER);
			//driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();  //Sign in button
			//driver.findElement(By.xpath("//button[@class='btn btn-primary']")).sendKeys(Keys.ENTER);  //Sign in button
			Thread.sleep(3000);

			//driver.findElement(By.cssSelector("img[class='mat-tooltip-trigger img-fluid floating imagedashboard']")).click(); //Select RPM module
			//Thread.sleep(6000);

			SoftAssert ast = new SoftAssert();

			//Title Test
			String ExpTitle = "MedSysHealth";
			String ActTitle = driver.getTitle();
			ast.assertEquals(ExpTitle, ActTitle, "Title mismatch...");

			//URL Test
			String ExpUrl = "https://perf2cluster1.mepstaging.medsyshealth.com/emrmegax/#/dashboard/dashboard";
			String ActUrl = driver.getCurrentUrl();
			ast.assertEquals(ExpUrl, ActUrl, "URL mismatch...");

			//Error Message Test
			String ExpErr = driver.findElement(By.xpath("//div[@class='alert alert-danger ng-star-inserted']")).getText();
			String ActErr = "Invalid User Name or Passwordd";
			ast.assertEquals(ExpErr, ActErr, "Error message mismatch...");

			driver.quit();
			ast.assertAll();
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}
