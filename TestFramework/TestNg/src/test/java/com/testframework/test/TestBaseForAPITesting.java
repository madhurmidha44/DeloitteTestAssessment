package com.testframework.test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.testframework.corelibrary.miscalleneous.FileMethods;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestBaseForAPITesting {
	//Declaring object of test data properties file
	Properties properties;
	//Unique output report name (format: Exec_report_yyyyMMddHHmmss.html) to identify the report. Example: Exec_report_202304120600.html
	private static String reportName="Exec_report_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".html";

	//Declared static output report object so that parallel running test cases data can be output to the report. Also saving the report in the 'Reports' folder of the project
	private static String reportPath=System.getProperty("user.dir") + "/Reports/"+reportName;
	public static ExtentReports extent=new ExtentReports(reportPath, true);

	//ExtentTest object to write data for each test case in the output report
    public ExtentTest test;

	//Below method will run before every test case
	@BeforeMethod(alwaysRun = true)
    public void beforeTest(Method method) throws Exception {
		//get the properties file object by specifing its path
		properties= FileMethods.getPropertiesObject(System.getProperty("user.dir")+"/src/test/java/com/testframework/testdata/apiData.properties");

		//Initialise extentTest object for the starting test case so that its details are logged in the report
		test = extent.startTest(method.getName());
    }


    @AfterMethod(alwaysRun = true)
    public void afterTest(ITestResult result) throws Exception {
		//If test case passes, then its status is logged in the output report
		if (result.getStatus() == ITestResult.SUCCESS)
			{
			test.log(LogStatus.PASS, "Passed");
		}

		//If test case passes, test case is marked as failed and error message is logged in the report
		else {
			test.log(LogStatus.FAIL,
				result.getThrowable().getMessage() + " " + result.getThrowable().getStackTrace());
		}
		//Close ExtentTest object for the finished test case
		extent.endTest(test);
		extent.flush();
    }

	//Below method runs after the all the test cases are finished for execution
    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws Exception {
		//Closes extent report object
    	extent.close();
		//email report to the recipient
		//Email.emailReport(reportPath);
    }
   

}
