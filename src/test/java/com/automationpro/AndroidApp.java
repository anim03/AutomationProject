package com.automationpro;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import  io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement; 
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidApp {
    private AndroidDriver driver;

    @BeforeClass
    public void setup() {
        try {
            
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName("emulator-5554"); 
            options.setApp("C:/Apps/sauce_app.apk"); 
            options.setAutomationName("UiAutomator2"); 
            options.setAutoGrantPermissions(true);
            options.setNoReset(true); 
            options.setNewCommandTimeout(Duration.ofSeconds(60)); 
           
            
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            System.out.println("Appium driver initialized successfully.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Malformed URL for Appium server.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to initialize Appium driver. Check capabilities and server connection.");
        }
    }

    @Test
    public void loginTest() {
        try {
            
            WebElement usernameField = driver.findElement(AppiumBy.accessibilityId("test-Username"));
            usernameField.sendKeys("standard_user");
            System.out.println("Entered username.");

            
            WebElement passwordField = driver.findElement(AppiumBy.accessibilityId("test-Password"));
            passwordField.sendKeys("secret_sauce");
            System.out.println("Entered password.");

          
            WebElement loginButton = driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
            loginButton.click();
            System.out.println("Clicked login button.");

          
            WebElement productPage = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='PRODUCTS']"));
            Assert.assertTrue(productPage.isDisplayed(), "Login failed, Products page is not displayed.");
            System.out.println("Login successful, Products page displayed.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to complete login test. Check element locators and app state.");
        }
    }

    @Test(dependsOnMethods = "loginTest")
    public void searchProductTest() {
        try {
           
            WebElement productItem = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Sauce Labs Backpack']"));
            Assert.assertTrue(productItem.isDisplayed(), "Product search failed, item not found.");
            System.out.println("Product found: Sauce Labs Backpack.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to search for product. Check element locators.");
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Appium driver closed successfully.");
        }
    }
}
