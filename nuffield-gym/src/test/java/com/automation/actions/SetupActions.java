package com.automation.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SetupActions {

    private static WebDriver driver;

    public void startBrowser() {
        String path = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=/Users/Jamzy/AppData/Local/Google/Chrome/User Data/Default/");
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void quitBrowser() {
        driver.close();
        driver.quit();
        driver = null;
    }
}
