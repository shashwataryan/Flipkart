package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pageObjects.Cart;
import pageObjects.CheckOut;
import pageObjects.HomePage;
import pageObjects.Login;
import pageObjects.MobileProductList;
import pageObjects.ProductPage;
import pageObjects.createReports;

public class PlaceOrder extends BaseTest{

	WebDriver driver;
	String mobile;



	@BeforeClass
	@Parameters("inputParam")
	void openFlipkart(String inputMobile) {
		//Scanner sc=new Scanner(System.in);
		//System.out.println("Enter the mobile");
		mobile=inputMobile;

	}

	@Test
	void placeOrder() throws Exception
	{
		int c=0;
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--remote-allow-origins=*");
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");

		driver=new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();


		test=extent.createTest("Place Order","Place mobile order according to the choice of user");

		try {
			HomePage openMobiles=new HomePage(driver);
			Login closeLoginWindow=new Login(driver);
			MobileProductList userselectMobile=new MobileProductList(driver);
			ProductPage expectedProductAdded=new ProductPage(driver);
			Cart actualProductAdded=new Cart(driver);
			CheckOut checkoutOptions=new CheckOut(driver);

			closeLoginWindow.closeLogin();
			openMobiles.clickOnMobilesMenu();
			openMobiles.SearchForMobileProduct(mobile);
			userselectMobile.selectMobile(mobile.toLowerCase());
			Set<String> windowHandles=driver.getWindowHandles();
			Iterator<String>it=windowHandles.iterator();
			it.next();
			String childWindow= it.next();
			driver.switchTo().window(childWindow);

			String expectedProduct=expectedProductAdded.addProductToCart();	
			String actualProduct=actualProductAdded.productInCart();
			Assert.assertTrue(expectedProduct.contains(actualProduct));
			actualProductAdded.placeProductOrder();
			List<WebElement>optionsAtCheckOut= checkoutOptions.optionsAtCheckout();


			//Iterate over all the sections in checkout page one by one

			int counter=0; // To point a particular section in checkout page
			for(WebElement option:optionsAtCheckOut)
			{
				counter++;
				String text=option.getText();
				if(counter==1)
					Assert.assertEquals("LOGIN OR SIGNUP", text);
				if(counter==2)
					Assert.assertEquals("DELIVERY ADDRESS", text);
				if(counter==3)
					Assert.assertEquals("ORDER SUMMARY", text);
				if(counter==4)
					Assert.assertEquals("PAYMENT OPTIONS", text);
			}
			test.log(Status.PASS, "Placed Order");

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
				File destFile=new File(".\\test-output\\Test 6.png");
				FileUtils.copyFile(sourceFile, destFile);
				test.addScreenCaptureFromPath(".\\test-output\\Test 6.png", "ScreenShot for failed test case");

			}
			driver.quit();
			if(c>0)
				Assert.fail();


		}
	}
}

