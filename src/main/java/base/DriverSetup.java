package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class DriverSetup {

    // Thread-safe WebDriver for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> browserName = new ThreadLocal<>();
    public static WebDriver getDriver() {
        return driver.get();
    }
    public static String getBrowserName() {
        return browserName.get();
    }
    @Parameters("browser")
    @BeforeMethod
    //@BeforeClass
    public void setup(@Optional("chrome") String browser) {
        WebDriver drv;

        switch (browser.toLowerCase()) {
            case "firefox":
                drv = new FirefoxDriver();
                break;
            case "edge":
                drv = new EdgeDriver();
                break;
            case "chrome":
            default:
                drv = new ChromeDriver();
                break;
        }

        drv.manage().window().maximize();
        drv.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        drv.get("https://in.bookmyshow.com"); 

        driver.set(drv);  
        browserName.set(browser);
        System.out.println(browser + " browser launched.");
    }

    @AfterMethod
    //@AfterClass
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            browserName.remove();        
        }
    }
}
