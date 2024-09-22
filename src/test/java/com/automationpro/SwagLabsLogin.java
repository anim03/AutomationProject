package com.automationpro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SwagLabsLogin {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
       
        driver = new ChromeDriver();

        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

       
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void loginTest() {
     
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        usernameField.sendKeys("standard_user");

      
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordField.sendKeys("secret_sauce");

       
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
        loginButton.click();

      
        WebElement productsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        Assert.assertTrue(productsTitle.isDisplayed(), "Login failed, Products page is not displayed.");

        
        WebElement menuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-burger-menu-btn")));
        Assert.assertTrue(menuButton.isDisplayed(), "Menu button not found, possible issue with login.");

    }

    @AfterClass
    public void teardown() {
    
        if (driver != null) {
            driver.quit();
        }
    }
}
