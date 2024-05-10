package com.app;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class MobileTest {
    static AndroidDriver driver;
    @Test
    public void test1() throws MalformedURLException {
        AutomatorOptions options = new AutomatorOptions();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options.getApiDemosOptions());
        driver.findElement(AppiumBy.accessibilityId("Accessibility")).click();
        driver.findElement(AppiumBy.accessibilityId("Accessibility Node Provider")).click();
        String actualMessage = driver.findElements(AppiumBy.className("android.widget.TextView")).get(1).getText().trim();
        String expectedMessage = "Enable TalkBack and Explore-by-touch from accessibility settings. Then touch the colored squares.";
        Assert.assertEquals(actualMessage, expectedMessage);
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();

    }

    @Test
    public void test2() throws MalformedURLException {
        AutomatorOptions options = new AutomatorOptions();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options.getSauceLabOptions());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
        driver.findElement(AppiumBy.accessibilityId("menu item log in")).click();
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("bob@example.com");
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("10203040");
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
        driver.findElement(AppiumBy.accessibilityId("menu item log out")).click();
        driver.findElement(By.id("android:id/button1")).click();
        String actualMessage = driver.findElement(By.id("android:id/alertTitle")).getText();
        String expectedMessage = "You are successfully logged out.";
        Assert.assertEquals(actualMessage, expectedMessage);
        driver.findElement(By.id("android:id/button1")).click();
        driver.quit();

    }
}
