package pageObjects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MobileProductList {

	WebDriver driver;

	public MobileProductList(WebDriver d)
	{
		driver=d;
		PageFactory.initElements(driver, this);

	}


	@FindBy(xpath="//div[@class='_3LWZlK']") List<WebElement> mobileRatings;
	@FindBy(xpath="//label[@class='_6Up2sF']") List<WebElement> addToCompare;
	@FindBy(xpath="//span[text()='COMPARE']") WebElement  compareButton;
	@FindBy(xpath="//div[@class='_2d4KYb']/span[1]") List<WebElement> deliveryDate;
	@FindBy(xpath="//a[@class='_3L_M2k']") List<WebElement> mobileNamestoCompare;
	@FindBy(xpath="//div[@class='_4rR01T']") List<WebElement> mobileNames;

	public void selectMobile(String userMobile)
	{
		//To select mobiles from list
		ExplicitWait wait=new ExplicitWait(driver);
		wait.waitForAllElementsInList(mobileNames);
		for(WebElement mobile:mobileNames)
		{
			if(mobile.getText().toLowerCase().contains(userMobile))
			{
				mobile.click();
				break;
			}
		}
	}

	public Double mobileRatingsCompare()
	{
		Double max=0.0,rating;
		driver.navigate().refresh(); //To load the complete list of mobile present in the DOM
		ExplicitWait wait=new ExplicitWait(driver);
		wait.waitForAllElementsInList(mobileRatings);
		for(WebElement ratings:mobileRatings)
		{
			rating=Double.parseDouble(ratings.getText());

			if(rating>max)
				max=rating;
		}
		return max;
	}

	public String compareDeliveryDate()
	{
		int numberOfMobiles=0;
		int counter=0,index=0;
		String year;
		LocalDate min=LocalDate.MAX; //Stores maximum available date in localdate as an initial value of comparison
		ExplicitWait wait=new ExplicitWait(driver);
		wait.waitForAllElementsInList(mobileNames);

		//adds first 3 mobiles to compare from
		for(WebElement compare:addToCompare)
		{
			if(numberOfMobiles!=3)
			{
				compare.click();
				numberOfMobiles++;
			}
		}
		compareButton.click();
		wait.waitForAllElementsInList(deliveryDate);

		for(WebElement deliver:deliveryDate)
		{

			String date=deliver.getText(); // to get the delivery date along with some extra string
			// to retrieve the date and month from the given string
			String Date1[]=date.split(","); 
			date=Date1[0];
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu"); //To format date in particular format
			LocalDate ob= LocalDate.now(); //To get current time using system clock and default time zone
			year=Integer.toString(ob.getYear()); //To obtain current year
			date=date+" "+year; //Add year to the date
			LocalDate lt = LocalDate.parse(date, formatter); //Parse date according to the above format
			//Find minimum date
			if(min.compareTo(lt)<0)
			{
				min=lt;
				index=counter;
			}
			counter++;
		}
		return mobileNamestoCompare.get(index).getText();
	}
}