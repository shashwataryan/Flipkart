// This file has been depricated as user cannot login to flipkart without otp

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccount {
	WebDriver driver;
	public MyAccount(WebDriver d)
	{
		driver=d;
		PageFactory.initElements(driver, this);
	}
	ExplicitWait ob=new ExplicitWait(driver);
	@FindBy(xpath="//div[text()='My Account']") WebElement myAccount;
	@FindBy(xpath="//div[text()='My Profile']") WebElement myProfile;
	@FindBy(xpath="//div[text()='Wishlist']") WebElement wishList;

	void hoverOverMyAccount()
	{
		Actions action=new Actions(driver);
		action.moveToElement(myAccount).build().perform();
	}

	void clickMyProfile()
	{
		ob.waitForElementToBeVisible(myProfile);
		myProfile.click();
	}

	void clickOnWishlist()
	{
		ob.waitForElementToBeVisible(wishList);
		wishList.click();
	}
}
