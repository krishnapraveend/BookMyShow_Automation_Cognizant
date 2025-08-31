package pages;

import base.DriverSetup;
import base.ConfigLoader;
import locators.LocatorRepository;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

import java.util.List;

public class CityPage {
    private final WebDriver driver;
    private String previousCity = null;

    public CityPage() {
        this.driver = DriverSetup.getDriver();  // ThreadLocal driver from DriverSetup
        PageFactory.initElements(driver, this);
        System.out.println("CityPage initialized with WebDriver instance: " + driver.getClass().getSimpleName());
    }

    private By region(String city) {
        return By.xpath("//span[text()='" + city + "']");
    }

    public void selectCity() {
        String city = ConfigLoader.get("city");
        driver.findElement(LocatorRepository.get("cityInput")).sendKeys(city, Keys.ENTER);

        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        // Check for error
        try {
            WebElement err = driver.findElement(LocatorRepository.get("errorMsg"));
            if (err.isDisplayed()) {
                System.out.println("City selection failed: " + city + " | Error: " + err.getText());
                return;
            }
        } catch (NoSuchElementException ignored) {}

        WaitUtils.urlContains(city.toLowerCase(), 10);
        WebElement cityChip = WaitUtils.visible(region(city), 10);
        previousCity = cityChip.getText();
        System.out.println("City selected successfully: " + previousCity);
    }

    public void changeLocation() {
        selectCity();
        String newCity = ConfigLoader.get("newCity");

        WebElement current = WaitUtils.visible(region(previousCity), 10);
        current.click();
        driver.findElement(LocatorRepository.get("cityInput")).sendKeys(newCity, Keys.ENTER);
        WaitUtils.urlContains(newCity.toLowerCase(), 10);
        previousCity = newCity;
        System.out.println("Location changed successfully to: " + newCity);
    }

    public void selectCityByIcon(String city) {
        WaitUtils.clickable(LocatorRepository.get("cityIconInput"), 10);
        List<WebElement> icons = driver.findElements(LocatorRepository.get("availableCities"));
        boolean clicked = false;

        for (WebElement el : icons) {
            if (el.getText().equalsIgnoreCase(city)) {
                el.click();
                clicked = true;
                break;
            }
        }

        if (clicked) {
            previousCity = WaitUtils.visible(region(city), 10).getText();
            System.out.println("City selected via icon successfully: " + previousCity);
        } else {
            System.out.println("City icon not found for: " + city);
        }
    }

    public void viewAllCitiesToggleAndValidate() {
        WebElement allCities = WaitUtils.clickable(LocatorRepository.get("allCities"), 10);
        allCities.click();

        List<WebElement> others = driver.findElements(LocatorRepository.get("otherCities"));
        System.out.println("Other cities count: " + others.size());
        for (int i = 0; i < Math.min(10, others.size()); i++) {
            System.out.println(others.get(i).getText());
        }

        WaitUtils.visible(LocatorRepository.get("hideCities"), 10).click();
        System.out.println("'Hide All Cities' clicked successfully.");
    }
}
