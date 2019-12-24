package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

//this class will be a test foundation for all test classes
//we will put here only before and after parts
//In this way before and after methods will be the same
//Every test class will extend testbase class
public abstract class TestBase {
// * ExtentReports itself does not build any reports, but allows reporters to access information, which in
// * turn build the said reports. An example of building an HTML report and adding information to ExtentX:
// * ExtentHtmlReporter html = new ExtentHtmlReporter("Extent.html");
// * ExtentXReporter extentx = new ExtentXReporter("localhost");

    protected static ExtentReports extentReports;
    //    The ExtentHtmlReporter creates a rich standalone HTML file. It allows several
    protected static ExtentHtmlReporter extentHtmlReporter;
    //    Defines a test. You can add logs, snapshots, assign author and categories to a test and its children.
    protected static ExtentTest extentTest;
    public Actions actions;
    protected WebDriver driver;


    //         <parameter name="test" value="regression"></parameter>
    @BeforeTest
    @Parameters({"test", "env_url"})
    public void beforeTest(@Optional String test, @Optional String env_url) {
        //location of report
        //it's gonna be next to target folder, test-output folder
        String reportName = "report";
        if (test != null) {
            reportName = test;
        }
        String filePath = System.getProperty("user.dir") + "/test-output/" + reportName + ".html";
        extentReports = new ExtentReports();
        extentHtmlReporter = new ExtentHtmlReporter(filePath);
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.config().setReportName("Vytrack Test Results");
        //system information
        String env = ConfigurationReader.getProperties("url");
        if (env_url != null) {
            env = env_url;
        }
        extentReports.setSystemInfo("Environment", env);
        extentReports.setSystemInfo("Browser", ConfigurationReader.getProperties("browser"));
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
    }


    @AfterTest
    public void afterTest() {
//         Writes test information from the started reporters to their output view
        extentReports.flush();
    }

    //        <parameter name="env_url" value="https://qa3.vytrack.com/"></parameter>
    @BeforeMethod
    @Parameters("env_url")
    public void setup(@Optional String env_url) {
        actions = new Actions(Driver.get());
        driver=Driver.get();
        String url = ConfigurationReader.getProperties("url");
        //if name parameter was set, then use it
        //if it's null that means it was not set
        if (env_url != null) {
            url = env_url;
        }
        Driver.get().get(url);
        Driver.get().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Driver.get().manage().window().maximize();
    }

    //ITestResult class describes the result of a test. (in TestNG)
    @AfterMethod
    public void teardown(ITestResult result) throws InterruptedException,IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail(result.getName());
            extentTest.fail(result.getThrowable());
            try {
                BrowserUtils.getScreenshot(result.getName());// - takes screenshot and returns location of that screenshot
                //this method throws IOException (which is checked exception)
                //any checked exception must be handled
                extentTest.addScreenCaptureFromPath(BrowserUtils.getScreenshot(result.getName()));
            } catch (IOException e) {
                //print error info
                e.printStackTrace();
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test case was skipped : " + result.getName());
        }
        BrowserUtils.waitFor(4);
        Driver.closeDriver();
    }


}