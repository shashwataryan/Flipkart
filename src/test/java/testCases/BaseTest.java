package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

class BaseTest{

	WebDriver driver;
	public static ExtentReports extent =new ExtentReports();//initiating here is very important
	public static ExtentHtmlReporter htmlReporter;
	public ExtentTest test;
	@BeforeSuite
	public void beforeSuiteSetup() {
		String filepath = System.getProperty("user.dir");
		htmlReporter = new ExtentHtmlReporter(filepath+"/Report.html");     
		extent.attachReporter(htmlReporter);
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		extent.flush();
	}

}
