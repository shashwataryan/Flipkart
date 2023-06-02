package testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pageObjects.FooterSocialMedia;
import pageObjects.Login;
import pageObjects.createReports;

public class Footer {

	WebDriver driver;

	public ExtentTest test;
	@Test
	void checkSocialSites() throws IOException
	{
		int c=0;
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--remote-allow-origins=*");
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();


		test=BaseTest.extent.createTest("Flipkart Social Media Sites","Check for Flipkart Social Media Sites");
		try {
			Login closeLoginWindow=new Login(driver);
			FooterSocialMedia flipkartSocialMedia=new FooterSocialMedia(driver);

			closeLoginWindow.closeLogin();
			List<WebElement>socialSites=flipkartSocialMedia.clickOnFlipkartSocialMediaSites();
			int counter=0; //to point a particular site

			//Clicks and asserts one site at a time
			for(WebElement sites: socialSites)
			{
				counter++;
				Actions action=new Actions(driver);
				action.keyDown(Keys.CONTROL).moveToElement(sites).click().perform(); //To open the site in new tab

				//Thread.sleep has been used for the parent window to load completely and store its id
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				Set<String>windowHandles=driver.getWindowHandles();
				Iterator<String>it=windowHandles.iterator();
				String parentWindow=it.next();
				String childWindow=it.next();
				driver.switchTo().window(childWindow);

				String url=driver.getCurrentUrl();
				if(counter==1)
					Assert.assertEquals(url, "https://www.facebook.com/flipkart");
				if(counter==2)
					Assert.assertEquals(url, "https://twitter.com/flipkart");
				if(counter==3)
					Assert.assertEquals(url, "https://www.youtube.com/flipkart");
				driver.close();
				driver.switchTo().window(parentWindow);
				windowHandles.clear(); //clear all the windowhandles currently present in the list

				test.log(Status.PASS, "Social Sites in footer");

			}
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
				File destFile=new File(".\\test-output\\Test 4.png");
				FileUtils.copyFile(sourceFile, destFile);
				test.addScreenCaptureFromPath(".\\test-output\\Test 4.png", "ScreenShot for failed test case");

			}
			driver.quit();
			if(c>0)
				Assert.fail();

		}
	}  


}
