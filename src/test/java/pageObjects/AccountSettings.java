// This file has been depricated as user cannot login to flipkart without otp
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSettings {
	WebDriver driver;
	public AccountSettings(WebDriver d)
	{
		driver=d;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//span[text()='Edit']") WebElement editName;
	@FindBy(xpath="//input[@name='firstName']") WebElement firstName;
	@FindBy(xpath="//input[@name='lastName']") WebElement lastName;
	@FindBy(xpath="//button[text()='SAVE']") WebElement saveName;
	@FindBy(xpath="//div[text()='PAN Card Information']") WebElement clickPanCardInfo;
	@FindBy(xpath="//input[@name='pan']") WebElement panNumber;
	@FindBy(xpath="//input[@name='fullName']") WebElement panName ;

	void editProfileInformation(String firstname,String lastname)
	{
		editName.click();
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		saveName.click();
	}

	void enterPanCardDetails(String pannumber,String panname)
	{
		clickPanCardInfo.click();
		panNumber.sendKeys(pannumber);
		panName.sendKeys(panname);
	}
}

