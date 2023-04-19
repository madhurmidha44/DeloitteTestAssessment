package com.testframework.test;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.testframework.corelibrary.miscalleneous.FileMethods;
import com.testframework.corelibrary.miscalleneous.Email;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class TestBase{

	//Selenium web driver object to interact with UI
	public RemoteWebDriver driver;

	//Unique output report name (format: Exec_report_yyyyMMddHHmmss.html) to identify the report. Example: Exec_report_202304120600.html
	private static String reportName="Exec_report_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".html";

	//Declared static output report object so that parallel running test cases data can be output to the report. Also saving the report in the 'Reports' folder of the project
	private static String reportPath=System.getProperty("user.dir") + "/Reports/"+reportName;
	public static ExtentReports extent=new ExtentReports(reportPath, true);

	//ExtentTest object to write data for each test case in the output report
    public ExtentTest test;

	//Declaring object of test data properties file
	Properties properties;

	//Below method will run before every test case
	@BeforeMethod(alwaysRun = true)
    public void beforeTest(Method method) throws Exception {
		//get the properties file object by specifing its path
		properties= FileMethods.getPropertiesObject(System.getProperty("user.dir")+"/src/test/java/com/testframework/testdata/uiData.properties");

		//Gets the specified browser from the properties file and then open the respective browser
		if(properties.getProperty("browser").equals("chrome")){
			//Below method opens Chrome browser for every starting test case
			openChromeBrowser(method);
		}
		else {
			//Below method opens Firefox browser. Note, currently body of this method is empty as purpose is to demo test framework
			openFirefoxBrowser(method);
		}

		//Initialise extentTest object for the starting test case so that its details are logged in the report
		test = extent.startTest(method.getName());
    }


	//Below method will run after every test case
    @AfterMethod(alwaysRun = true)
    public void afterTest(ITestResult result) throws Exception {
	//If test case passes, then its status is logged in the output report
	if (result.getStatus() == ITestResult.SUCCESS) 
    	{
	    test.log(LogStatus.PASS, "Passed");
	}
	//If test case is failed then screenshot along with error message for the failed step are added to the test case in the report
	else {
		TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    String dest = System.getProperty("user.dir") + "/Reports/"
		    + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + "screenShotName" + ".png";
	    File destination = new File(dest);
	    Files.copy(source, destination);
	    test.log(LogStatus.FAIL,
		    result.getThrowable().getMessage() + " " + result.getThrowable().getStackTrace() + test.addScreenCapture(dest));
	}

	//Close ExtentTest object for the finished test case
	extent.endTest(test);
	extent.flush();

	//Close the browser for the finished test case
	driver.quit();
    }

	//Below method runs after the all the test cases are finished for execution
    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws Exception {
		//Closes extent report object
    	extent.close();
		//email report to the recipient
		//Email.emailReport(reportPath);
    }

	//Below method opens chrome browser
	private void openChromeBrowser(Method method){
		//Provide path of chromedriver.exe to system property 'webdriver.chrome.driver'. It is stored in Home directory of the project
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+properties.getProperty("chromeDriverPath"));

		//Initialising ChromeOptions object to open browser with various settings
		ChromeOptions options = new ChromeOptions();
	    /* various options to open browser in headless mode etc
	    options.addArguments("--incognito");
	    options.addArguments("--headless");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximixed");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");*/

		//Chrome browser is launched here with the options specified
		driver = new ChromeDriver(options);

		//Maximize the size of Chrome browser
		driver.manage().window().maximize();

		//Set the implicit time out for the browser window so that it can wait for the specified time if an element is not found
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	//Below method opens Firefox browser. Note, currently body of this method is empty as purpose is to demo test framework
	private void openFirefoxBrowser(Method method){
	}
   

}
