package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class qeaBMS {
    static WebDriver driver = new ChromeDriver();
    static String previousCity = null;
    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static void selectCity(String city){
        WebElement cityInput = driver.findElement(By.xpath("//input[@placeholder='Search for your city']"));
        //String location="Pune";
        cityInput.sendKeys(city, Keys.ENTER);

        // Short wait to allow UI to update after entering the city
        try {
            Thread.sleep(1000); // Replace with WebDriverWait if needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ✅ Check for "No results found." error message
        try {
            WebElement errorMsg = driver.findElement(By.xpath("//div[text()='No results found.']"));
            if (errorMsg.isDisplayed()) {
                System.out.println("Error: "+errorMsg.getText());
                Assert.fail("City selection failed — invalid city name: " + city);
                return;
            }
        } catch (Exception e) {
            // No error message, assume valid city and proceed
        }

        wait.until(ExpectedConditions.urlContains(city.toLowerCase()));
        //System.out.println("City changed successfully");
        String region = "//span[text()='" + city + "']";
        WebElement newCity = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(region)));
        String selectedCity=newCity.getText();
        System.out.println("City shown on page: " + selectedCity);
        Assert.assertEquals(selectedCity, city, "City selection assertion failed!");
        previousCity = selectedCity;
    }
    public static void changeLocation(String newcity){
        System.out.println("After changing the city:");
        String region = "//span[text()='" + previousCity + "']";
        WebElement newCity = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(region)));
        newCity.click();
        //String newCityName= newCity.getText();
        selectCity(newcity);
    }
    public static void signIn() throws InterruptedException {
        WebElement signInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div/div/div/div[2]/div[2]/div[1]")));
        if (signInBtn.isDisplayed()) {
            System.out.println("Sign In button is displayed");
            signInBtn.click(); // Optional: click if displayed
        } else {
            System.out.println("Sign In button is not displayed");
        }
        WebElement mobileNumberBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userMobileNumber")));
        mobileNumberBox.click();
        if (mobileNumberBox.isDisplayed()) {
            System.out.println("Mobile Number text box is displayed");
            mobileNumberBox.click(); // Optional: click if displayed
        } else {
            System.out.println("Mobile Number text box is not displayed");
        }
        mobileNumberBox.sendKeys("8431123713");
        WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[4]/div/div/div[2]/div[2]/div[2]/div")));
        if (continueBtn.isDisplayed()) {
            System.out.println("Continue button is displayed after input of mobile number");
            continueBtn.click(); // Optional: click if displayed
        } else {
            System.out.println("Continue button is not displayed after input of mobile number");
        }
        WebElement otp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='tel']")));
        otp.sendKeys("123456");
        WebElement otpContinueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[4]/div/div/div[2]/div/div[3]/div/div")));
        otpContinueBtn.click();
        WebElement backBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='sc-1ydq0aj-0 bIaakI']")
        ));
        backBtn.click();
        WebElement crossBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("sc-1ydq0aj-6 gnsbYm")));
        crossBtn.click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//        WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='sc-1ydq0aj-6 gnsbYm']")));
//        cancelBtn.click();
    }
    public static void invalidSignIn(){
        WebElement signInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div/div/div/div[2]/div[2]/div[1]")));
        signInBtn.click();
        WebElement mobileNumberBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userMobileNumber")));
        mobileNumberBox.click();
        mobileNumberBox.sendKeys("843112371");
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[4]/div/div/div[2]/div[2]/div[1]/div[2]")));
        System.out.println("Wrong Mobile Number: "+errorMsg.getText());
    }
    public static void lookForAMovie(){
        //WebElement searchBox = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div/div/div/div[1]/div[2]/div/span[2]"));
        //span[contains(text(),'Search for Movies')]
        WebElement searchBox = driver.findElement(By.xpath("//span[contains(text(),'Search for Movies')]"));
        searchBox.click();
        WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search for Movies, Events, Plays & more']"));
        search.click();
        WebElement topSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='sc-f42fb7-2 ieglPM'])[1]")));
        String title = topSearch.getText();
        topSearch.click();
        //search.sendKeys("Coolie");
    }

    public static void UIElements() throws InterruptedException {
        //p[text()='View All Cities']
        //driver.findElement(By.xpath("//input[@placeholder='Search for your city']"));
        WebElement allCities = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='View All Cities']")));
        if(allCities.isDisplayed()) System.out.println("All cities option is available");
        allCities.click();
        Thread.sleep(2000);
        System.out.println("Other cities:");
        List<WebElement> other = driver.findElements(By.xpath("//ul[@class='sc-yuf6si-1 idrZHM']//li"));
        System.out.println("The number of cities other than the popular ones: "+other.size());
        for(int i=0;i<10;i++){
            String city = other.get(i).getText();
            System.out.println(city);
        }
        WebElement hide = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Hide all cities']")));
        if(hide.isDisplayed()) System.out.println("Hide cities option is available");
        hide.click();
        selectCityByIcon("Bengaluru");
    }

    public static void giftcard(){
        WebElement gift = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Gift Cards'])[1]")));
        gift.click();
        WebElement card = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Best Partner BookMyShow Gift Card']")));
//        WebElement card = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='sc-eykeme-0 eUCNaf']")));
//        card.click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='recipientEmail']")));
//        email.sendKeys("abc@gmail.com");
//        WebElement mobile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='recipientMobile']")));
//        mobile.sendKeys("9999999999");



//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//            // Click on Gift Cards link
//            WebElement gift = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Gift Cards'])[1]")));
//            gift.click();
//
//            // Wait for the spinner to disappear (replace with actual selector)
//            // Optional: if you find the spinner's class name or ID
//            try {
//                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".styles__LoaderContainer-sc-1yd65qe-0"))); // Replace with actual spinner locator
//            } catch (TimeoutException e) {
//                System.out.println("Spinner did not disappear in time.");
//            }
//
//            // Wait for actual Gift Card heading/content to be visible
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'BOOKMYSHOW GIFT CARDS')]")));
    }

//    public static void selectAMovie() throws InterruptedException {
//        WebElement movie = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='sc-7o7nez-0 lkwOqB']")));
//        movie.click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        WebElement book = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Book tickets']")));
//        book.click();
//
//    }

//    public static void selectAMovie() {
//        try {
//            // Select the movie element
//            WebElement movie = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("(//div[@class='sc-7o7nez-0 lkwOqB'])[2]")));
//            String movieTitle = movie.getText();
//            System.out.println("Attempting to open movie: " + movieTitle);
//            movie.click();
//
//            // Wait briefly for the new page to load
//            Thread.sleep(2000);
//
//            // Detect error page
//            if (driver.getPageSource().contains("Sorry for bug-ging")) {
//                System.out.println("❌ Error page encountered after selecting movie: " + movieTitle);
//                return;
//            }
//
//            // Wait for and click "Book tickets"
//            WebElement book = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("//span[text()='Book tickets']/ancestor::button")));
//            book.click();
//
//            System.out.println("✅ Successfully clicked on 'Book tickets' for: " + movieTitle);
//
//        } catch (Exception e) {
//            System.out.println("❌ Exception in selectAMovie(): " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

    public static void selectAMovie() {
        int maxMoviesToTry = 10;

        for (int i = 1; i <= maxMoviesToTry; i++) {
            try {
                System.out.println("🎬 Trying movie #" + i);

                // Click the i-th movie
                WebElement movie = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("(//div[@class='sc-7o7nez-0 lkwOqB'])[" + i + "]")));
                String movieName = movie.getText();
                System.out.println("➡ Clicking movie: " + movieName);
                movie.click();

                Thread.sleep(2000); // wait for page to load

                // Check for broken/bugged page
                if (driver.getPageSource().contains("Sorry for bug-ging")) {
                    System.out.println("❌ Broken movie page. Skipping...");
                    driver.navigate().back();
                    wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("(//div[@class='sc-7o7nez-0 lkwOqB'])[1]")));
                    continue;
                }

                // Click "Book Tickets"
                WebElement bookButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[text()='Book tickets']/ancestor::button")));
                bookButton.click();
                System.out.println("✅ Clicked 'Book Tickets'.");

                // Handle "Continue" if A-rated movie prompt appears
                try {
                    WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[text()='Continue']")));
                    continueBtn.click();
                    System.out.println("👉 Clicked 'Continue' (A-rated).");
                } catch (TimeoutException te) {
                    System.out.println("ℹ️ No 'Continue' popup found.");
                }

                // Click the parent <div> of the <span> with 2D
                try {
                    WebElement format2DParentDiv = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("(//span[text()='2D']/parent::div)[1]")));
                    format2DParentDiv.click();
                    System.out.println("🎥 Clicked the 2D format (parent div).");
                } catch (TimeoutException fe) {
                    System.out.println("ℹ️ Format selection not shown.");
                }
                Thread.sleep(5000);
                // ✅ Now fetch date, theatre, and timings
                try {
                    // Today's date block
                    WebElement todayDateBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@class='sc-6bpksa-0 HCNei']")));

                    List<WebElement> dateParts = todayDateBlock.findElements(By.xpath("./div"));
                    if (dateParts.size() >= 3) {
                        String day = dateParts.get(0).getText();
                        String date = dateParts.get(1).getText();
                        String month = dateParts.get(2).getText();
                        System.out.println("📅 Today: " + day + ", " + date + " " + month);
                    } else {
                        System.out.println("⚠️ Incomplete date info.");
                    }

                    // Theatre name
                    WebElement theatre = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("(//span[@class='sc-1qdowf4-0 kVfEkA'])[1]")));
                    System.out.println("🎭 Theatre: " + theatre.getText());

                    // First 3 show timings
                    for (int j = 1; j <= 3; j++) {
                        try {
                            WebElement showTime = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                    By.xpath("(//div[@class='sc-1vhizuf-2 jIiAgZ'])[" + j + "]")));
                            System.out.println("🕒 Show Time " + j + ": " + showTime.getText());
                        } catch (TimeoutException ste) {
                            System.out.println("⚠️ Show Time " + j + " not found.");
                        }
                    }

                } catch (Exception e) {
                    System.out.println("❌ Error fetching date/theatre/timings: " + e.getMessage());
                }

                // 🎉 Booking stage reached — exit loop
                break;

            } catch (Exception e) {
                System.out.println("❌ Error during movie #" + i + ": " + e.getMessage());
                e.printStackTrace();

                try {
                    driver.navigate().back();
                    wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("(//div[@class='sc-7o7nez-0 lkwOqB'])[1]")));
                } catch (Exception backErr) {
                    System.out.println("⚠️ Error navigating back.");
                    backErr.printStackTrace();
                }
            }
        }
    }





//    public static void moviesFilter() throws InterruptedException {
//        WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Movies']")));
//        filter.click();
//        WebElement filter2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()='English'])[1]")));
//        filter2.click();
//        System.out.println(driver.getCurrentUrl());
//        WebElement movie = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='sc-7o7nez-0 elfplV'])[1]")));
//        String movieName = movie.getText();
//        movie.click();
//        WebElement book = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-phase='postRelease']")));
//        book.click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Continue']")));
//        continueBtn.click();
//        WebElement format = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='2D']")));
//        format.click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//
//        WebElement theatre = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.sc-1qdowf4-0.kVfEkA")));
//        String theatrename = theatre.getText();
//        System.out.println(theatrename);
//    }

    public static void moviesFilter() {
        try {
            System.out.println("Clicking 'Movies' tab...");
            WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Movies']")));
            filter.click();

            System.out.println("Filtering by 'English' language...");
            WebElement filter2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()='English'])[1]")));
            filter2.click();

            System.out.println("Current URL: " + driver.getCurrentUrl());

            System.out.println("Selecting first available movie...");
            WebElement movie = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='sc-7o7nez-0 elfplV'])[1]")));
            String movieName = movie.getText();
            movie.click();

            // Check for broken/error page
            Thread.sleep(2000); // short wait for page transition
            if (driver.getPageSource().contains("Sorry for bug-ging")) {
                System.out.println("❌ Error page encountered after selecting movie: " + movieName);
                return;
            }

            System.out.println("Clicking 'Book' button...");
            WebElement book = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-phase='postRelease']")));
            book.click();

            System.out.println("Clicking 'Continue' button...");
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Continue']")));
            continueBtn.click();

            System.out.println("Selecting '2D' format...");
            WebElement format = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2D']")));
            format.click();

            System.out.println("Getting theatre name...");
            WebElement theatre = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.sc-1qdowf4-0.kVfEkA")));
            String theatrename = theatre.getText();
            System.out.println("✅ Theatre Name: " + theatrename);

        } catch (Exception e) {
            System.out.println("❌ Exception occurred in moviesFilter(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void selectCityByIcon(String city){
        WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search for your city']")));
        List<WebElement> availableCities = driver.findElements(By.xpath("//p[@class='sc-1jg5yz-4 hbJJIz']"));
        boolean flag = false;
        for(WebElement element: availableCities){
            String cityName = element.getText();
            if(cityName.toLowerCase().equals(city.toLowerCase())){
                element.click();
                flag=true;
                break;
            }
        }
        if(flag==false){
            System.out.println("City Not Found");
        }
        else{
            try {
                String region = "//span[text()='" + city + "']";
                WebElement selectedCity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(region)));
                String actual = selectedCity.getText();
                Assert.assertEquals(actual, city, "Validation Failed - Selected city is not same");
                System.out.println(actual);
            } catch (Exception e) {
                System.out.println("City was clicked but not verified on screen. Possibly due to navigation.");
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        driver.get("https://in.bookmyshow.com/");
        //selectCity("Pune");
        //selectCity("Robertsonpet");
        //signIn();
        //invalidSignIn();
        //lookForAMovie();
        //changeLocation("Bengaluru");
        UIElements();
        //selectAMovie();
        //moviesFilter();
        //booking();
        //giftcard();
        //selectCityByIcon("Chandigarh");
    }
}

