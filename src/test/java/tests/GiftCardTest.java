package tests;

import base.ConfigLoader;
import base.DriverSetup;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CityPage;
import pages.GiftCardPage;
import reports.ExtentTestNGListener;

@Listeners(ExtentTestNGListener.class)
public class GiftCardTest extends DriverSetup {

    private CityPage city;
    private GiftCardPage gift;

    @BeforeMethod
    public void setupPagesAndSelectCity() {
        // Driver is already initialized by DriverSetup's @BeforeMethod
        city = new CityPage();
        gift = new GiftCardPage();

        // Select city before every test
        city.selectCity();
    }

    @Test
    public void testGiftCardSection() {
        gift.openGiftCardsAndValidateCheckBalanceVisible();
    }

    @Test
    public void testInvalidGiftVoucher() {
        gift.checkInvalidVoucherAndGetError();
    }
}
