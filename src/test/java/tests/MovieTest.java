package tests;

import base.DriverSetup;
import org.testng.annotations.*;
import pages.CityPage;
import pages.MoviePage;

public class MovieTest extends DriverSetup {

    private CityPage city;
    private MoviePage movie;

    @BeforeMethod
    public void setupPagesAndSelectCity() {
        // Driver is already initialized by DriverSetup's @BeforeMethod
        city = new CityPage();
        movie = new MoviePage();

        // Select city before every test
        city.selectCity();
    }

    //@Test
    public void testSelectRunningMovie() {
        movie.openMoviesTab();
        movie.movie_selectAnyRunningMovieAndPrintTitle();
    }

    @Test
    public void testMovie8SelectRunningMovie() {
        movie.movie8_selectAnyRunningMovieAndPrintTitle();
    }

    @Test
    public void testUpcomingAndNowShowing() {
        movie.upcomingAndNowShowing();
    }

    @Test
    public void testTopMenuNavigation() throws InterruptedException {
        movie.validateTopMenuNavigation();
    }
}
