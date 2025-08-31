package utils;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LocatorReader {

    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/locators.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static By get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Locator not found in properties file: " + key);
        }

        // Decide whether to use By.id or By.xpath or By.cssSelector based on value
        if (value.startsWith("//") || value.startsWith("(")) {
            return By.xpath(value);
        } else if (value.startsWith("css=")) {
            return By.cssSelector(value.replace("css=", ""));
        } else {
            return By.id(value);
        }
    }
}
