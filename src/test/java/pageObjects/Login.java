package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

	WebDriver driver;
	public Login(WebDriver d)
	{
		driver=d;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[@class='_1_3w1N']]") WebElement login;
	@FindBy(xpath="//input[@class='_2IX_2- VJZDxU']") WebElement emailMobile;
	@FindBy(xpath="//input[@class='_2IX_2- _3mctLh VJZDxU']") WebElement password;
	@FindBy(xpath="(//button[@type='submit'])[2]") WebElement loginButton;
	@FindBy (xpath="//button[text()='Request OTP']") WebElement otpButton;
	@FindBy(xpath="//button[@class='_2KpZ6l _2doB4z']") WebElement closeLoginWindow;
	@FindBy(xpath="//span[@class='_2YULOR']") WebElement errorMessage;

	public String logintToFlipkart(String mobileemail)
	{
		ExplicitWait wait=new ExplicitWait(driver);
		wait.waitForElementToBeVisible(otpButton);
		emailMobile.sendKeys(mobileemail);
		otpButton.click();
		return errorMessage.getText();
	}
	public void closeLogin()
	{
		closeLoginWindow.click();
	}
}
