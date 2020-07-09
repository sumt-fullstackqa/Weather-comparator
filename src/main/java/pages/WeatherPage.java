package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import testbase.Testbase;

public class WeatherPage extends Testbase {

	WebDriver ldriver;

	@SuppressWarnings("static-access")
	public WeatherPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);

	}

	@FindBy(how = How.XPATH, using = "//a[@class='topnavmore']")
	private WebElement morelinks;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'WEATHER')]")
	private WebElement weathersection;

	@FindBy(how = How.XPATH, using = "//input[@class='searchBox']")
	private WebElement pinyourcitysearchbox;

	@FindBy(how = How.XPATH, using = "//*[@id=\"map_canvas\"]/div[1]/div[4]/div[9]/div")
	private WebElement citywithtemtinfo;

	@FindBy(how = How.XPATH, using = "//*[@id=\"map_canvas\"]/div[1]/div[6]/div/div[1]")
	private WebElement cityfullweatherinfo;

	public void clickweathersection() {

		waitForElementToBeClickable(morelinks, driver, 3000);
		writeLogs("more links 3 dotted section  is available to click");
		ClickOn(driver, morelinks, 3);
		writeLogs("clicked on more links 3 dotted section successfully");
		waitForElementToBeClickable(weathersection, driver, 3000);
		writeLogs("weather section link is available to click");
		ClickOn(driver, weathersection, 3);
		writeLogs("clicked on weather section successfully");

	}

	public void pinyourcity(String cityname) {

		waitForElementToBeVisible(pinyourcitysearchbox, driver, 4000);
		writeLogs("pin your city searchbox is available");
		sendKeys(driver, pinyourcitysearchbox, 5, cityname);
		writeLogs("entered city name in the search field successfully");

	}

	public void validatecityinfo() {

		waitForElementToBeVisible(citywithtemtinfo, driver, 4000);
		writeLogs("city with temperate is available");
		Assert.assertTrue(citywithtemtinfo.isDisplayed());
		writeLogs("city with temperate information is available on the map");
		

	}

	public void cityweatherdetails() {

		waitForElementToBeVisible(citywithtemtinfo, driver, 4000);
		writeLogs("city is available on map");
		ClickOn(driver, citywithtemtinfo, 3);
		writeLogs("clicked on city on map successfully");

		Assert.assertTrue(cityfullweatherinfo.isDisplayed());
		writeLogs("fulll wethear info is available in pop up when clicked on city");

	}
}
