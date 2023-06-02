package testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import pageObjects.ExplicitWait;
import pageObjects.Login;
import pageObjects.createReports;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.testng.annotations.*;

public class LoginTest extends BaseTest{

	WebDriver driver;



	String credentials;


	@BeforeClass
	@Parameters("inputParam")
	void openFlipkart(String inputCredentials) {

		/*Scanner sc=new Scanner(System.in);
		System.out.println("Enter wrong credentials");*/
		credentials=inputCredentials;

	}

	@Test
	void loginNegativeTestCase() throws IOException {

		int c=0;
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--remote-allow-origins=*");
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");

		driver=new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();


		test=extent.createTest("Login Negative Test Case","Login using incorrect username");
		try {
			Login loginWindow=new Login(driver);

			String expectedError=loginWindow.logintToFlipkart(credentials);

			Assert.assertEquals("Please enter valid Email ID/Mobile number", expectedError);


			test.log(Status.PASS, "Login");

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
				File destFile=new File(".\\test-output\\Test 5.png");
				FileUtils.copyFile(sourceFile, destFile);
				test.addScreenCaptureFromPath(".\\test-output\\Test 5.png", "ScreenShot for failed test case");

			}
			driver.quit();
			if(c>0)
				Assert.fail();


		}
	}

}
