package testCases;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pageObjects.*;

public class CompareMobileRatings extends BaseTest{

	WebDriver driver;
	Double rating;
	String brand;


	@BeforeClass
	@Parameters({"inputParam","inputParam"})
	
	void openFlipkart(String inputBrand,Double inputRating)
	{
		/*Scanner sc=new Scanner(System.in);
		System.out.println("Enter the mobile brand");*/
		brand=inputBrand;
		//System.out.println("Enter the minimum rating");
		rating=inputRating;
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");

	}

	@Test
	void MobileRatings() throws IOException {

		int c=0;
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();

		test=extent.createTest("Compare Mobile Rating","Compare Mobile Ratings or user entered brand"); 
		try {
			Login closeLoginWindow=new Login(driver);
			HomePage openMobiles=new HomePage(driver);
			MobileProductList compareRatings=new MobileProductList(driver);

			closeLoginWindow.closeLogin();
			openMobiles.clickOnMobilesMenu();
			openMobiles.SearchForMobileProduct(brand);
			Double max= compareRatings.mobileRatingsCompare();
			System.out.println(max);

			Assert.assertTrue(max>rating);

			test.log(Status.PASS, "Maximum Ratings");
		}
		catch(AssertionError e)
		{
			c++;
		}
		catch(Exception e1)
		{
			c++;
		}
		finally {
			if(c>0)
			{
				test.log(Status.FAIL,"User Searched Product failed");
				TakesScreenshot screenshot=(TakesScreenshot)driver;
				File sourceFile=screenshot.getScreenshotAs(OutputType.FILE);
				File destFile=new File(".\\test-output\\Test 3.png");
				FileUtils.copyFile(sourceFile, destFile);
				test.addScreenCaptureFromPath(".\\test-output\\Test 3.png", "ScreenShot for failed test case");

			}
			driver.quit();
			if(c>0)
				Assert.fail();

		}
	} 

}