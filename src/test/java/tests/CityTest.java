package tests;

import base.DriverSetup;
import org.testng.annotations.Test;
import pages.CityPage;

public class CityTest extends DriverSetup {

    @Test
    public void testSearchAndSelectCity() {
        CityPage city = new CityPage();
        city.selectCity();
    }

    @Test
    public void testChangeLocation() {
        CityPage city = new CityPage();
        city.changeLocation();
    }

    @Test
    public void testSelectCityByIcon() {
        CityPage city = new CityPage();
        city.selectCityByIcon("Bengaluru");
    }

    @Test
    public void testViewAllCities() {
        CityPage city = new CityPage();
        city.viewAllCitiesToggleAndValidate();
    }
}
