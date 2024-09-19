package com.medsys.login;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class LoginAnnotation {

    public String baseUrl;
    public String rpmUrl;
    WebDriver driver;

    @BeforeTest
    public void lunchBrowser() throws Exception {
        System.out.println("Lunch Chrome Browser.");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        DesiredCapabilities caps = new DesiredCapabilities();
        options.addArguments("--remote-allow-origins=*");
        // options.addArguments("--disable-notifications");

        Map<String, Object> prefs = new HashMap<String, Object>(); // For allowing browser notification
        prefs.put("profile.default_content_setting_values.notifications", 1); // 1 to allow notifications
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        System.out.println("Allow the browser Notifications.");
    }
    @AfterTest
    public void tearDown() {
        System.out.println("Close the Browser.");
        driver.quit();
    }
      @Test
    public void url() throws InterruptedException {
        System.out.println("Provide URL");
        baseUrl = "https://perf2cluster1.mepstaging.medsyshealth.com/emrmegax/#/login";
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));


          String ExpUrl = baseUrl;
          String ActUrl = driver.getCurrentUrl();
          assertEquals(ExpUrl, ActUrl);
          Thread.sleep(2000);
    }

    @Test
    public void login() throws InterruptedException {
        System.out.println("Provide Username and Password");
        driver.findElement(By.id("mat-input-0")).sendKeys("Rocky");  //Username
        driver.findElement(By.id("mat-input-1")).sendKeys("123", Keys.ENTER);  //Password and Sign in button
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));

        /*driver.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();  //Sign in button
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).sendKeys(Keys.ENTER);  //Sign in button*/

        String ExpTitle = "MedSys";
        String ActTitle = driver.getTitle();
        assertEquals(ExpTitle, ActTitle);
        Thread.sleep(2000);
    }

    @Test
    public void rpm() throws InterruptedException {
        System.out.println("Goto RPM module");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
        driver.findElement(By.cssSelector("img[class='mat-tooltip-trigger img-fluid floating imagedashboard']")).click(); //Select RPM module

        rpmUrl= "https://perf2cluster1.mepstaging.medsyshealth.com/emrmegax/#/rpm/rpmpatientdetails";
        String ExpRpmUrl = rpmUrl;
        String ActRpmUrl = driver.getCurrentUrl();
        assertEquals(ExpRpmUrl, ActRpmUrl);
        Thread.sleep(2000);
    }
    @Test
    public void logout() throws InterruptedException {
        System.out.println("Logout from the application");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
        driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-menu-trigger mat-icon-button mat-button-base ng-tns-c241-0 ng-star-inserted']")).click(); //Click on the icon for logout
        driver.findElement(By.xpath("//*[@id=\"mat-menu-panel-0\"]/div/button[1]/span")).click(); //Click on Logout
        Thread.sleep(2000);
    }
}