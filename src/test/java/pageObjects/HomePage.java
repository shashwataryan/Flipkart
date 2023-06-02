package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver d)
	{
		driver=d;
		PageFactory.initElements(driver, this);

	}


	@FindBy(xpath="//div[text()='Fashion']") WebElement fashionmenu;
	@FindBy(xpath="//a[text()=\"Men's Bottom Wear\"]") WebElement fashionSubmenu;
	@FindBy(xpath="//a[text()='All']") WebElement fashionSubmenuSelect;
	@FindBy(xpath="//div[text()='Mobiles']") WebElement mobiles;
	@FindBy(xpath="//input[@title='Search for products, brands and more']") WebElement searchProduct;
	@FindBy(xpath="//div[@class='_2B099V']") WebElement shoeProduct;
	@FindBy(xpath="//span[@class='_10Ermr']/span") WebElement searchedProduct;

	void fashionItemSelect()
	{
		ExplicitWait wait=new ExplicitWait(driver);
		Actions action=new Actions(driver);
		action.moveToElement(fashionmenu).build().perform();
		wait.waitForElementToBeVisible(fashionSubmenu);
		action.moveToElement(fashionSubmenu).build().perform();
		wait.waitForElementToBeVisible(fashionSubmenuSelect);
		action.moveToElement(fashionSubmenuSelect).build().perform();

	}

	public void clickOnMobilesMenu()
	{

		ExplicitWait wait=new ExplicitWait(driver);
		wait.waitForElementToBeVisible(mobiles);
		mobiles.click();
	}

	public String searchForShoeProduct(String product)
	{
		searchProduct.click();
		searchProduct.sendKeys(product);
		searchProduct.sendKeys(Keys.ENTER);
		ExplicitWait wait=new ExplicitWait(driver);
		wait.waitForElementToBeVisible(shoeProduct);
		shoeProduct.click();
		return searchedProduct.getText();
	}

	public void SearchForMobileProduct(String mobile)
	{
		searchProduct.click();
		searchProduct.sendKeys(mobile);
		searchProduct.sendKeys(Keys.ENTER);
	}
}
