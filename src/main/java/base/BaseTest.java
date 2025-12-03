package base;

import drivers.DriverManager;
import drivers.DriverManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import reports.ExtentReportManager;

import java.lang.reflect.Method;


public class BaseTest {

    protected final Logger LOG = LogManager.getLogger(getClass());
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;

    @BeforeSuite
    public void beforeSuite() {
        LOG.info("Before Suite executed");
        ExtentReportManager.initializeExtentReports();
    }

    //khoi tao driver
    @BeforeClass
    public void beforeClass() {
        LOG.info("Before class executed");
        DriverManager driverManager = DriverManagerFactory.getDriverManager("chrome");
        driver = driverManager.createDriver();
        driver.manage().window().maximize();

    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        LOG.info("Before Method executed");
        ExtentReportManager.createTest(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        LOG.info("After Method executed");
        if(result.getStatus() == ITestResult.FAILURE) {
            ExtentReportManager.captureScreenshot(driver, result.getMethod().getMethodName());
        }
    }

    @AfterClass
    public void afterClass() {
        LOG.info("After class executed");
        driver.quit();
    }

    @AfterSuite
    public void afterSuite() {
        ExtentReportManager.flushReports();
        LOG.info("Test Suite completed");
    }
}
