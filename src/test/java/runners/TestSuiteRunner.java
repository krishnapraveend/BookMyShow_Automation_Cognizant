package runners;

import org.testng.TestNG;

import java.util.Collections;

public class TestSuiteRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();

        // Pass browser dynamically via JVM argument or default to Chrome
        String browser = (args.length > 0) ? args[0] : "chrome";
        System.setProperty("browser", browser);
        System.out.println("Running tests on browser: " + browser);

        testng.setTestSuites(Collections.singletonList("testng.xml"));
        testng.run();
    }
}
