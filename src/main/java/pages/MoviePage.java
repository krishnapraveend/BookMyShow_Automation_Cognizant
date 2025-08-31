package pages;

import base.DriverSetup;
import locators.LocatorRepository;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.WaitUtils;

public class MoviePage {
    private final WebDriver driver;

    public MoviePage() {
        this.driver = DriverSetup.getDriver();
        PageFactory.initElements(driver, this);
        System.out.println("MoviePage initialized with WebDriver instance: " + driver.getClass().getSimpleName());
    }

    public void openMoviesTab() {
        System.out.println("Opening 'Movies' tab.");
        WebElement moviesTab = WaitUtils.clickable(LocatorRepository.get("moviesTab"), 10);
        Assert.assertNotNull(moviesTab, "'Movies' tab not found");
        moviesTab.click();
    }

    public void movie_selectAnyRunningMovieAndPrintTitle() {
        System.out.println("Attempting to select one of the first two running movies.");

        for (int i = 1; i <= 2; i++) {
            try {
                By tile = By.xpath("(//div[@class='sc-7o7nez-0 lkwOqB'])[" + i + "]");
                WebElement movie = WaitUtils.clickable(tile, 20); // increased wait
                Assert.assertNotNull(movie, "Movie tile #" + i + " not found");
                System.out.println("Found movie tile #" + i + " with name: " + movie.getText());

                // Try normal click, fallback to JS click
                try {
                    movie.click();
                } catch (ElementNotInteractableException e) {
                    System.out.println("Regular click failed, using JS click for tile #" + i);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", movie);
                }

                Thread.sleep(2000); // wait for page to load

                // Handle bug page
                if (driver.getPageSource().contains("Sorry for bug-ging")) {
                    System.out.println("Bug page detected. Navigating back and trying next movie tile.");
                    driver.navigate().back();
                    WaitUtils.clickable(LocatorRepository.get("movieSelection"), 15);
                    continue;
                }

                WebElement title = WaitUtils.visible(By.xpath("//h1[@class='sc-qswwm9-6 ea-drWB']"), 15);
                Assert.assertNotNull(title, "Movie title not found after clicking tile #" + i);
                System.out.println("Selected movie title: " + title.getText());
                return;

            } catch (Exception e) {
                System.out.println("Exception while trying movie tile #" + i + ": " + e.getMessage());
            }
        }

        Assert.fail("Could not open a valid movie card after trying first two tiles.");
    }

    public void movie8_selectAnyRunningMovieAndPrintTitle() {
        // Reuse main logic to avoid code duplication
        movie_selectAnyRunningMovieAndPrintTitle();
    }

    public void validateTopMenuNavigation() throws InterruptedException {
        System.out.println("Starting top menu navigation validation.");
        String[] menu = {
            "Movies", "Stream", "Events", "Plays", "Sports", "Activities",
            "ListYourShow", "Corporates", "Offers", "Gift Cards"
        };

        for (String m : menu) {
            System.out.println("Clicking menu item: " + m);
            By link = By.xpath("//a[text()='" + m + "']");
            WebElement el = WaitUtils.clickable(link, 10);
            Assert.assertNotNull(el, "Menu item '" + m + "' not found");

            // Try normal click, fallback to JS click
            try {
                el.click();
            } catch (ElementNotInteractableException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            }

            Thread.sleep(1000);
            System.out.println("Navigated to '" + m + "' and returning to previous page.");
            driver.navigate().back();
        }

        System.out.println("Top menu navigation validation completed.");
    }

    public void upcomingAndNowShowing() {
        System.out.println("Navigating to 'Upcoming' and 'Now Showing' sections in Movies.");
        openMoviesTab();

        WebElement upcoming = WaitUtils.clickable(LocatorRepository.get("upcoming"), 10);
        Assert.assertNotNull(upcoming, "'Upcoming' section not found");
        upcoming.click();
        System.out.println("'Upcoming' section clicked.");

        WebElement nowShowing = WaitUtils.clickable(LocatorRepository.get("nowShowing"), 10);
        Assert.assertNotNull(nowShowing, "'Now Showing' section not found");
        nowShowing.click();
        System.out.println("'Now Showing' section clicked.");
    }
}
