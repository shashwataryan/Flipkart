package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Cart {
	WebDriver driver;
	public Cart(WebDriver d)
	{
		driver=d;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[@class='_2Kn22P gBNbID']") WebElement cartList;
	@FindBy(xpath="//span[text()='Place Order']") WebElement placeOrder;


	public String productInCart()
	{

		ExplicitWait wait=new ExplicitWait(driver);
		wait.waitForElementToBeVisible(cartList);
		String productPresentInCart=cartList.getText();
		return productPresentInCart;
	}
	public void placeProductOrder()
	{
		placeOrder.click();
	}
}
