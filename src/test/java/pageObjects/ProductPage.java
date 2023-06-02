package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	WebDriver driver;
	public ProductPage(WebDriver d)
	{
		driver=d;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//button[text()='Add to cart']") WebElement addToCart;
	@FindBy(xpath="//div[@class='aMaAEs']/div") WebElement expectedElement;
	@FindBy(xpath="//div[@class='_36FSn5']") WebElement wishList;
	@FindBy(xpath="//button[text()='BUY NOW']") WebElement buyNow;
	@FindBy(xpath="//a[@class='_1fGeJ5 _2UVyXR _31hAvz']") WebElement shoeSize;

	public void selectShoeSize()
	{
		shoeSize.click();
	}
	public String addProductToCart()
	{
		ExplicitWait wait=new ExplicitWait(driver);
		wait.waitForElementToBeVisible(addToCart);
		addToCart.click();
		return expectedElement.getText();
	}

	public String buyProductNow()
	{
		buyNow.click();
		return expectedElement.getText();
	}

	public String addToWishlist()
	{
		wishList.click();
		return expectedElement.getText();

	}
}
