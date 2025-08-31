package reports;

import base.DriverSetup;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Praveen Krishna");
            extent.setSystemInfo("Environment", "QA");
            
            String browser = DriverSetup.getBrowserName();
            if (DriverSetup.getDriver() != null) {
                //String browser = DriverSetup.getDriver().getClass().getSimpleName();
                extent.setSystemInfo("Browser", browser);
            }
        }
        return extent;
    }
}
