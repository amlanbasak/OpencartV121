package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties systemProperties;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException, Exception
	{
		//Loading config.properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		systemProperties = new Properties();
		systemProperties.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		if(systemProperties.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//OS (Operating System)
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching OS found...");
				return;
			}

			//System.out.println(browser.toLowerCase());
			
			//Browser
			switch (browser.toLowerCase())
			{
				case "chrome": capabilities.setBrowserName("chrome"); break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
				case "firefox": capabilities.setBrowserName("firefox"); break;
				default: System.out.println("No matching browser found..."); return;
			}
			//URL gridURL = URI.create("http://localhost:4444/wd/hub").toURL();
			//driver = new RemoteWebDriver(gridURL, capabilities);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		
		if(systemProperties.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(browser.toLowerCase())
			{
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			default: System.out.println("Invalid Browser..."); return;
			}
		}

		logger.info("####### Browser selected is: " + browser + " #######");
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("https://tutorialsninja.com/demo/index.php");
		driver.get(systemProperties.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(1000);
		driver.quit();
	}	

	public String randomString(int noOfChar)
	{
		String generatedString = RandomStringUtils.randomAlphabetic(noOfChar);
		return generatedString;
	}
	
	public String randomNumber(int noOfDigits)
	{
		String generatedNumber = RandomStringUtils.randomNumeric(noOfDigits);
		return generatedNumber;
	}

	public String randomAlphaNumeric(int noOfChar, char specialChar, int noOfDigits)
	{
		String generatedString = RandomStringUtils.randomAlphabetic(noOfChar);
		String generatedNumber = RandomStringUtils.randomNumeric(noOfDigits);
		//System.out.println(generatedString+specialChar+generatedNumber);
		return (generatedString+specialChar+generatedNumber);
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
	}
	
}
