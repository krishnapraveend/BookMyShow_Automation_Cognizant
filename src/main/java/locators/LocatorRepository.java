package locators;

import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

public final class LocatorRepository {
    private static final Map<String, By> L = new HashMap<>();

    static {
        // City Selection
        L.put("cityInput", By.xpath("//input[@placeholder='Search for your city']"));
        L.put("errorMsg", By.xpath("//div[text()='No results found.']"));
        L.put("region", By.xpath("//span[text()='Bengaluru']")); 

        // Sign In
        L.put("signInBtn", By.xpath("//div[text()='Sign in']"));
        L.put("mobileNumberBox", By.id("userMobileNumber"));
        L.put("continueBtn", By.xpath("//div[text()='Continue']"));
        L.put("otp", By.xpath("//input[@type='tel']"));
        L.put("otpContinueBtn", By.xpath("/html/body/div[1]/div/div[4]/div/div/div[2]/div/div[3]/div/div"));
        L.put("backBtn", By.xpath("//div[@class='sc-1ydq0aj-0 bIaakI']"));
        L.put("crossBtn", By.xpath("//div[@style='cursor: pointer;']")); 

        // Invalid Sign In
        L.put("invalidMobileNumberBox", By.id("userMobileNumber"));
        L.put("invalidErrorMsg", By.xpath("//div[@class='sc-z1ldnh-12 Qsrzn']"));

        // Movie Search
        L.put("searchBox", By.xpath("//span[contains(text(),'Search for Movies')]"));
        L.put("searchInput", By.xpath("//input[@placeholder='Search for Movies, Events, Plays & more']"));
        L.put("topSearch", By.xpath("(//span[@class='sc-f42fb7-2 ieglPM'])[1]"));

        // UI Elements
        L.put("allCities", By.xpath("//p[text()='View All Cities']"));
        L.put("hideCities", By.xpath("//p[text()='Hide all cities']"));

        // Gift Card
        L.put("gift", By.xpath("(//a[text()='Gift Cards'])[1]"));
        L.put("balance", By.xpath("//div[text()='Check Gift Card Balance']"));
        L.put("code", By.xpath("//input[@id='gift-voucher']"));
        L.put("errorGiftVoucher", By.xpath("//div[@id='error-gift-voucher']"));

        // Movies Filter
        L.put("moviesTab", By.xpath("//a[text()='Movies']"));
        L.put("englishFilter", By.xpath("(//div[text()='English'])[1]"));
        L.put("movie", By.xpath("(//div[@class='sc-7o7nez-0 elfplV'])[1]"));
        L.put("book", By.xpath("//button[@data-phase='postRelease']"));
        L.put("continueMovie", By.xpath("//div[text()='Continue']"));
        L.put("format", By.xpath("//span[text()='2D']"));
        L.put("theatre", By.cssSelector("span.sc-1qdowf4-0.kVfEkA"));

        // Select a Movie
        L.put("movieSelection", By.xpath("(//div[@class='sc-7o7nez-0 lkwOqB'])"));

        // Select City by Icon
        L.put("cityIconInput", By.xpath("//input[@placeholder='Search for your city']"));
        L.put("availableCities", By.xpath("//p[@class='sc-1jg5yz-4 hbJJIz']"));

        // Movie 8
        L.put("movie8", By.xpath("(//div[@class='sc-7o7nez-0 lkwOqB'])"));

        // Movies 10
        L.put("upcoming", By.xpath("//div[@data-content='Coming Soon']"));
        L.put("nowShowing", By.xpath("//div[@data-content='Now Showing']"));

        // Menu Options
        L.put("menuOption", By.xpath("//a[text()='Movies']"));
        
        //other cities
        L.put("otherCities", By.xpath("//ul[@class='sc-yuf6si-1 idrZHM']//li"));
    }

    private LocatorRepository() {}

    public static By get(String key) {
        return L.get(key);
    }
}
