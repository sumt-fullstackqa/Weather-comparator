/**
 * @author sumit.mishra
 *
 */



package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;



 
public class Testbase {
	
	public static  WebDriver driver ;
	public Properties prop;
	
	public int RESPONSE_STATUS_CODE_200=200;
	public int RESPONSE_STATUS_CODE_500=500;
	public int RESPONSE_STATUS_CODE_400=400;
	public int RESPONSE_STATUS_CODE_401=401;
	public int RESPONSE_STATUS_CODE_201=201;
	public int RESPONSE_STATUS_CODE_204=204;
	
	//below line will create object of the Logger class
	public static Logger logger = Logger.getLogger("Testbase.class");
	
	
	public static void writeLogs(String msg)
	{
		logger.info(msg);
	}
	
	public static void writeErrorLogs(Throwable t) {
		
		logger.error(t.getMessage());
	}
	
	@BeforeClass
	public WebDriver initializeDriver() throws IOException
	{
		
		//chrome
		
		prop= new Properties();
		FileInputStream fis= new FileInputStream("src/main/resources/configfile/config.properties");
		
		prop.load(fis);
		
		String browserName=prop.getProperty("browser");

		if (browserName.equals("chrome"))
		
		{
			//write code for chhrome initilization
			
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

			driver=new ChromeDriver();	
		}
		else if (browserName.equals("firefox"))
		{
			//write code for firefox initilization
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if (browserName.equals("IE"))
		{
			//write code for IE initilization 
			System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/MicrosoftWebDriver.exe");
			driver=new InternetExplorerDriver();
			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		writeLogs("driver is initialized");
		return driver;
		
	}
	
	public String getScreenshotpath(String testcasename, WebDriver driver) throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot) driver;
	File source =ts.getScreenshotAs(OutputType.FILE);
	
	String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testcasename+".png";
	
	FileUtils.copyFile(source,new File(destinationFile));
		
	return destinationFile;
	
	}
	public static WebElement waitForElementToBeVisible(WebElement webelement, WebDriver driver, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(webelement));
		return element;

	}
	public static List<WebElement> waitForElementsToBeVisible(List<WebElement> webelements, WebDriver driver, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElements(webelements));
		return elements;
	}
	

	public static WebElement waitForElementToBeClickable(WebElement webelement, WebDriver driver, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webelement));
		return element;

	}

	public static void sendKeys(WebDriver driver, WebElement element, int timeout, String value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);

	}

	public static void ClickOn(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();

	}
	
	public static void JSClickOn(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",element );
	
	}
	
	
	public void mousehover(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement( element).build().perform();
	}
	
	public void dropDownList(WebElement itemsizeDropdown, String value) {
		Select dropdownlist = new Select(itemsizeDropdown);
		dropdownlist.selectByVisibleText(value);
	}
	
	public void refresh(WebDriver driver) {
		
		driver.navigate().refresh();
	}

	
	@AfterClass
	
	public void teardown() {
		driver.close();
	}
	
}



