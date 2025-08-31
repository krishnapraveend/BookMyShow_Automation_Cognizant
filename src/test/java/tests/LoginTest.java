package tests;

import base.DriverSetup;
import org.testng.annotations.*;
import pages.CityPage;
import pages.LoginPage;

public class LoginTest extends DriverSetup {

    private CityPage city;
    private LoginPage login;

    @BeforeMethod
    public void setupPagesAndSelectCity() {
        // Driver is already initialized by DriverSetup's @BeforeMethod
        city = new CityPage();
        login = new LoginPage();

        // Select city before every test
        city.selectCity();
    }

    @Test
    public void testLoginWithValidMobile() {
        login.validSignIn();
    }

    @Test
    public void testLoginWithInvalidMobile() {
        login.signInInvalid();
    }

    @Test
    public void testLoginUIElements() {
        login.verifySignInUI();
    }
}
