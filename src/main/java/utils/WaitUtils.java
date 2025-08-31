package utils;

import base.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    // Generic wait factory
    private static WebDriverWait getWait(long seconds) {
        WebDriver driver = DriverSetup.getDriver();
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    // Wait until element is visible
    public static WebElement visible(By locator, long seconds) {
        return getWait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait until element is clickable
    public static WebElement clickable(By locator, long seconds) {
        return getWait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait until element is present in DOM
    public static WebElement present(By locator, long seconds) {
        return getWait(seconds).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait until URL contains token
    public static boolean urlContains(String token, long seconds) {
        return getWait(seconds).until(ExpectedConditions.urlContains(token));
    }

    // Wait until text is present in element
    public static boolean textPresent(By locator, String text, long seconds) {
        return getWait(seconds).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    // Wait until alert is present
    public static boolean alertPresent(long seconds) {
        return getWait(seconds).until(ExpectedConditions.alertIsPresent()) != null;
    }
}
