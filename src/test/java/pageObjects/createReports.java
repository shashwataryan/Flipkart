// The file has been depricated because extent reports has been used in a different way now
package pageObjects;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;


public class createReports {

	WebDriver driver;
	public createReports(WebDriver d)
	{
		driver=d;
		PageFactory.initElements(driver,this);
	}
	// Static variable single_instance of type Singleton
	private static createReports single_instance = null;

	// Declaring a variable of type String
	public String s;

	// Constructor of this class
	// Here private constructor is used to
	// restricted to this class itself
	private createReports()
	{
		s = "Hello I am a string part of Singleton class";
	}

	// Method
	// Static method to create instance of Singleton class
	public static createReports createReports()
	{
		// To ensure only one instance is created
		if (single_instance == null) {
			single_instance = new createReports();
		}
		return single_instance;
	}
	public static ExtentReports generateReports() 
	{
		ExtentReports extent=new ExtentReports();
		//extent.setReportName("FinalReport.html");
		//extent.loadConfig(new File("extent-config.xml"));
		//ExtentHtmlReporter html=new ExtentHtmlReporter();
		//ExtentSparkReporter spark=new ExtentSparkReporter("ExtentReport.html");
		//extent.attachReporter(html);

		return extent;
	}
}
