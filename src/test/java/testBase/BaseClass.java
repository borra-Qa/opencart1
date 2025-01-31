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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public WebDriver driver;
public Logger logger;
public Properties p;
	
@SuppressWarnings("deprecation")
@BeforeClass(groups= {"Sanity","Regression","Master","DataDriven"})
@Parameters({"os","browser"})
public void setup(String os,String br) throws IOException {
		
	logger=LogManager.getLogger(this.getClass());
	FileReader file=new FileReader("./src//test//resources//config.properties");
	p=new Properties();
	p.load(file);
	
	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	{
		DesiredCapabilities capabilities=new DesiredCapabilities();
		
		if(os.equalsIgnoreCase("windows"))
		{
			capabilities.setPlatform(Platform.WIN11);
		}
		else if(os.equalsIgnoreCase("linux"))
		{
			capabilities.setPlatform(Platform.LINUX);
		}else if(os.equalsIgnoreCase("Mac"))
		{
			capabilities.setPlatform(Platform.MAC);
		}
		else
		{
			System.out.println("No matching....");
		return;
		}
		switch(br.toLowerCase())
		{
		case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
		case "chrome":capabilities.setBrowserName("chrome");break;
		case "firefox":capabilities.setBrowserName("firefox");break;
		default:System.out.println("No matching.....");return;
		}
		
		 driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
	}
	
	if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	{
	switch(br.toLowerCase())
	{
	case "edge":driver=new EdgeDriver();break;
	case "chrome":driver=new ChromeDriver();break;
	case "firefox":driver=new FirefoxDriver();break;
	default:System.out.println("Invalid Browser...");return;
	}
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().deleteAllCookies();
	driver.get("https://tutorialsninja.com/demo/");
			
		}
		
		@AfterClass(groups= {"Sanity","Regression","Master","DataDriven"})
		public void tearDown() {
			driver.quit();
		}
		@SuppressWarnings("deprecation")
		public String randomeString() {
			String generatedString=RandomStringUtils.randomAlphabetic(5);
			return generatedString;
		}
		
		public String randomNumber() {
			@SuppressWarnings("deprecation")
			String generatedNumber=RandomStringUtils.randomNumeric(10);
			return generatedNumber;
			
		}
		
		public String randomAlphaNumber() {
			@SuppressWarnings("deprecation")
			String generatedAlphaNumeric=RandomStringUtils.randomAlphanumeric(6);
			return generatedAlphaNumeric;
			
		}
		
		public String captureScreen(String tname) throws IOException
		{
		 String timestamp=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		 
		 TakesScreenshot takesscreenshot=(TakesScreenshot) driver;
		 File sourcefile=takesscreenshot.getScreenshotAs(OutputType.FILE);
		 
		 String path=System.getProperty("user.dir")+"\\screenshots.\\"+tname+"_"+timestamp+".png";
		 File targetfile=new File(path);
		 sourcefile.renameTo(targetfile);
			return path;
			
		}
		
	}
