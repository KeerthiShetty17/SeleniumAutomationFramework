// java
package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReportManager;
import utils.Log;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;
    
    
    @BeforeSuite
    public void setupReport() {
    	extent = ExtentReportManager.getReportInstance();
    }
    
    @AfterSuite
    public void teardownReport() {
    	extent.flush();
    }

    
    @BeforeMethod
    public void setUp() {
        // ensure WebDriverManager is on the classpath (see note below)
    	Log.info("Setting up WebDriverManager for ChromeDriver");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=\\*"); // escaped as required in docs/console logs
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        Log.info("Navigating to the login page");
        driver.get("https://admin-demo.nopcommerce.com/login");
    }

   @AfterMethod
   public void tearDown(ITestResult result) {
		// Capture screenshot on failure
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = ExtentReportManager.captureScreenshot(driver, "Login Failure");
			test.fail("test failed..check screenshot: " ,MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			
		}
	   
	    
       if (driver != null) {
    	   Log.info("Closing the browser");
           driver.quit();
      }
  }
}
