package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckOut {
	WebDriver driver;
	public CheckOut(WebDriver d)
	{
		driver=d;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[@class='_2Kn22P']") WebElement orderName;
	@FindBy(xpath="//div[@class='_2-ut7f _1WpvJ7']") WebElement orderValue;
	@FindBy(xpath="//div[text()='CONTINUE']") WebElement continueButton;
	@FindBy(xpath="//span[@class='_1aULyb']") List<WebElement> checkoutOptions;

	void orderDetailsAtCheckout()
	{
		String orderNameInCart=orderName.getText();
		ProductPage ob=new ProductPage(driver);
		Assert.assertEquals(orderNameInCart, ob.buyProductNow());
		continueButton.click();
	}

	public List<WebElement> optionsAtCheckout()
	{
		return checkoutOptions;
	}
}
