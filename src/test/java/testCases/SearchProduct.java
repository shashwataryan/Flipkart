package testCases;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;
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
import pageObjects.ProductPage;
import pageObjects.createReports;

public class SearchProduct extends BaseTest {

	WebDriver driver;
	String product;




	@BeforeClass
	@Parameters("inputParam")
	void openFlipkart(String inputProduct) {
		//Scanner sc=new Scanner(System.in);
		//System.out.println("Enter the product");
		product=inputProduct;

	}

	@Test
	void selectShoes() throws IOException 
	{
		int c=0;
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--remote-allow-origins=*");
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();


		test=extent.createTest("Search Product","Search shoe Product");
		try {
			Login closeLoginWindow=new Login(driver);
			HomePage shoeProduct=new HomePage(driver);
			ProductPage expectedProductAdded=new ProductPage(driver);
			Cart actualProductAdded=new Cart(driver);

			closeLoginWindow.closeLogin();
			String actualSearchedProduct=shoeProduct.searchForShoeProduct(product);
			Assert.assertEquals(actualSearchedProduct, product);
			Set<String> windowHandles=driver.getWindowHandles();
			Iterator<String>it=windowHandles.iterator();
			it.next();
			String childWindow= it.next();
			driver.switchTo().window(childWindow);

			expectedProductAdded.selectShoeSize();
			String expectedProduct=expectedProductAdded.addProductToCart();
			String actualProduct=actualProductAdded.productInCart();
			//To bring text in one line
			expectedProduct=expectedProduct.replaceAll("\n", ""); 

			Assert.assertTrue(expectedProduct.contains(actualProduct));

			test.log(Status.PASS, "User Searched Product");

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
				File destFile=new File(".\\test-output\\Test 7.png");
				FileUtils.copyFile(sourceFile, destFile);
				test.addScreenCaptureFromPath(".\\test-output\\Test 7.png", "ScreenShot for failed test case");

			}
			driver.quit();
			if(c>0)
				Assert.fail();
		}
	}

}


