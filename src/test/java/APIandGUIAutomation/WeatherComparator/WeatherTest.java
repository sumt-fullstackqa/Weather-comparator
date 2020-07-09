package APIandGUIAutomation.WeatherComparator;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.Test;
import pages.WeatherPage;
import testbase.Testbase;


public class WeatherTest extends Testbase {
	
	WeatherPage p1;

	@Test(priority = 1)
	public void basepagenavigation() throws IOException {

		// 1. open link 
		driver.get(prop.getProperty("url"));

		// creating object of that class and invoking method of it
		p1 = new WeatherPage(driver);
		
        p1.clickweathersection();
		
	}
	@Test(priority = 2)
	
	public void locateYourCityOnMapTest() {
		
		p1.pinyourcity(prop.getProperty("city"));
		p1.validatecityinfo();
		
	}
	@Test(priority = 3)
	
   public void validateweatherinfo() {
	   
	  p1.cityweatherdetails();
   }
	}