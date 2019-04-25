package testmeapp.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Drivers {
	static public WebDriver getDriver(String browser)
	{
		if(browser.contentEquals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
			return new ChromeDriver();
			
		}
		else if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver.exe");
			return new FirefoxDriver();
		}
		else if(browser.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IEDriverserver.exe");
			return new InternetExplorerDriver();
		}
		else
		{
			return null;
		}
	}

}


