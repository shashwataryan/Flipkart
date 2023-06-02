package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterSocialMedia {

	WebDriver driver;
	public FooterSocialMedia(WebDriver d)
	{
		driver=d;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[text()='SOCIAL']/following-sibling::a") List<WebElement> socialSites;

	public List<WebElement> clickOnFlipkartSocialMediaSites()
	{
		return socialSites;
	}
}
