package pages;

import base.DriverSetup;
import base.ConfigLoader;
import locators.LocatorRepository;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.WaitUtils;

public class GiftCardPage {
    private final WebDriver driver;

    public GiftCardPage() {
        this.driver = DriverSetup.getDriver();
        PageFactory.initElements(driver, this);
        System.out.println("GiftCardPage initialized with WebDriver instance.");
    }

    /** Opens Gift Cards section and validates 'Check Balance' icon visibility */
    public void openGiftCardsAndValidateCheckBalanceVisible() {
    	System.out.println("Attempting to open Gift Cards section.");
        WaitUtils.clickable(LocatorRepository.get("gift"), 10).click();
        System.out.println("'Gift' option clicked. Waiting for 'Check Balance' icon to appear.");
        
        WebElement balance = WaitUtils.clickable(LocatorRepository.get("balance"), 10);
        Assert.assertNotNull(balance, "Check Gift Card Balance icon not present");
        System.out.println("'Check Gift Card Balance' icon is visible and validated.");
    }

    /** Enters an invalid voucher code and retrieves the error message reliably */
    public void checkInvalidVoucherAndGetError() {
    	System.out.println("Attempting to open Gift Cards section.");
        WaitUtils.clickable(LocatorRepository.get("gift"), 10).click();
    	System.out.println("Clicking on 'Check Balance' icon to validate voucher.");
        WaitUtils.clickable(LocatorRepository.get("balance"), 10).click();

        WebElement code = WaitUtils.visible(LocatorRepository.get("code"), 10);
        System.out.println("Gift voucher input field is visible. Entering invalid voucher code.");
        
        code.clear();
        code.sendKeys(ConfigLoader.get("coupon"));
        System.out.println("Invalid voucher code entered.");

        WebElement error = WaitUtils.visible(LocatorRepository.get("errorGiftVoucher"), 10);
        String errorMsg = error.getText();
        System.out.println("Error message retrieved: " + errorMsg);
        Assert.assertNotNull(errorMsg, "No error message was seen");
    }
}
