package testCases;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

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

import pageObjects.Cart;
import pageObjects.ExplicitWait;
import pageObjects.HomePage;
import pageObjects.Login;
import pageObjects.MobileProductList;
import pageObjects.ProductPage;


public class AddToCart extends BaseTest {

	WebDriver driver;
	String mobile;

	@BeforeClass
	@Parameters("inputParam")
	void openFlipkart(String mobileInput) {

		//Scanner sc = new Scanner(System.in);
		//System.out.println("Enter the mobile");
		mobile = mobileInput;

	}

	@Test
	void CartProduct() throws IOException {
		int c=0;
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();

		test = extent.createTest("Add product to cart","Add user entered mobile product to cart");
		try {
			HomePage openMobiles = new HomePage(driver);
			Login closeLoginWindow = new Login(driver);
			MobileProductList userselectMobile = new MobileProductList(driver);
			ProductPage expectedProductAdded = new ProductPage(driver);
			Cart actualProductAdded = new Cart(driver);

			closeLoginWindow.closeLogin();
			openMobiles.clickOnMobilesMenu();
			openMobiles.SearchForMobileProduct(mobile);
			userselectMobile.selectMobile(mobile.toLowerCase());// find user selected mobile from list

			Set<String> windowHandles = driver.getWindowHandles();
			Iterator<String> it = windowHandles.iterator();
			it.next();
			String childWindow = it.next();
			driver.switchTo().window(childWindow);
			String expectedProduct = expectedProductAdded.addProductToCart();
			String actualProduct = actualProductAdded.productInCart();

			Assert.assertTrue(expectedProduct.contains(actualProduct));
			test.log(Status.PASS, "Product added to cart");

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
				File destFile=new File(".\\test-output\\Test 1.png");
				FileUtils.copyFile(sourceFile, destFile);
				test.addScreenCaptureFromPath(".\\test-output\\Test 1.png", "ScreenShot for failed test case");

			}
			driver.quit();
			if(c>0)
				Assert.fail();

		}
	}

}
