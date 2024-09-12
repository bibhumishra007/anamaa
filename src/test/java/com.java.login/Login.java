package com.java.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.time.Duration;


public class Login {

	public String baseUrl;
	WebDriver driver;

	@BeforeSuite
	public void lunchBrowser() {
		System.out.println("Lunch Chrome Browser.");

	}

	@BeforeClass
	public void login() {
		System.out.println("Login to App");
		baseUrl = "https://perf2cluster1.mepstaging.medsyshealth.com/emrmegax/#/login";
	}

	@BeforeTest
	public void url() {
		System.out.println("Provide URL");
	}

	@BeforeMethod
	public void credential() {
		System.out.println("Provide username and Password");
	}

	
	@Test
	public void loginn() throws InterruptedException {

		try {
			WebDriverManager.chromedriver().setup();
			DesiredCapabilities caps = new DesiredCapabilities();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			driver.get(baseUrl);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.findElement(By.id("mat-input-0")).sendKeys("Rocky");  //Username
			driver.findElement(By.id("mat-input-1")).sendKeys("123");  //Password
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();  //Sign in button
			Thread.sleep(6000);

			driver.findElement(By.cssSelector("img[class='mat-tooltip-trigger img-fluid floating imagedashboard']")).click(); //Select RPM module
			Thread.sleep(6000);

			driver.quit();
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}
