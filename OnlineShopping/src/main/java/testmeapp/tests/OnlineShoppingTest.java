package testmeapp.tests;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import testmeapp.utility.Drivers;

public class OnlineShoppingTest {
	
	WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports reports;
	ExtentTest logger;
	
	@BeforeTest
	 public void startBeforeTest()
	 {
		driver=Drivers.getDriver("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-ms");
		String path=System.getProperty("user.dir")+"/extent-reports/"+sdf.format(new Date())+".html";
		htmlreporter=new ExtentHtmlReporter(path);
		reports=new ExtentReports();
		reports.attachReporter(htmlreporter);
		reports.setSystemInfo("username", "Reshma.Ramanjanappa");
		reports.setSystemInfo("host", "localhost");
		reports.setSystemInfo("Environment", "TestEnvironment");
		htmlreporter.config().setReportName("Test Me App Report");
	 }
	
	@AfterMethod
	public void getResultAfterMethod(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(Status.FAIL, "THE TEST IS FAILED");
		}
		
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, "THE TEST IS PASSED");
		}
		
		else if(result.getStatus()==ITestResult.SKIP)
		{
			logger.log(Status.SKIP, "THE TEST IS SKIPPED");
		}
	}
	
	@AfterTest
	public void endReportAfterTest()
	{
		reports.flush();
		driver.close();
	}
	

	
	
	@Test(priority=1)
	public void testRegistration()
	{
		driver.findElement(By.partialLinkText("SignUp")).click();
		driver.findElement(By.id("userName")).sendKeys("ghhyttuykyf");
	   
		driver.findElement(By.id("firstName")).click();
	    
	    Assert.assertEquals(driver.findElement(By.id("err")).getText(), "Available");
	    driver.findElement(By.id("firstName")).sendKeys("fgjydfjdgh");
	
	      driver.findElement(By.id("lastName")).sendKeys("fyjdurfbfggws");
	     driver.findElement(By.id("password")).sendKeys("Passs732234");
	   
	    driver.findElement(By.id("pass_confirmation")).sendKeys("Passs732234");
	    driver.findElement(By.cssSelector("input[value='Female']")).click();
	    driver.findElement(By.id("emailAddress")).sendKeys("fvjffhjughfgf4@gmail.com");
	    driver.findElement(By.id("mobileNumber")).sendKeys("9755011051");
	    
	    driver.findElement(By.cssSelector("img[src='images/calendar.png']")).click();
	    
	    Select bm=new Select(driver.findElement(By.className("ui-datepicker-month")));
	    bm.selectByVisibleText("Dec");
	    
	    Select by=new Select(driver.findElement(By.className("ui-datepicker-year")));
	    by.selectByVisibleText("1997");
	    
	    driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[6]/a")).click();
	    driver.findElement(By.name("address")).sendKeys("MN colony sanjaynagar RMV 3rd stage Bengaluru");
	    
	    Select sa3=new Select(driver.findElement(By.id("securityQuestion")));
	    sa3.selectByIndex(1);
	    driver.findElement(By.id("answer")).sendKeys("789");
	    driver.findElement(By.cssSelector("input[value='Register']")).click();
	    
	    logger=reports.createTest("SignUp_Registration");
		logger.log(Status.INFO, "THE REGISTRATION IS SUCCESSFULLY COMPLETED");
	    
	  // String s= driver.findElement(By.xpath("//form/fieldset/div[8]")).getText();
	   Assert.assertEquals(driver.findElement(By.xpath("//form/fieldset/div[8]")).getText(), "User Registered Succesfully!!! Please login");
	    
	    
	}
	
	@Test(priority=2)
	public void testLogin() 
	{
		
		
		WebDriverWait wait=new WebDriverWait(driver,10);
		driver.findElement(By.id("userName")).sendKeys("knggjhn90");
		driver.findElement(By.id("password")).sendKeys("Passs564569");
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		
		String l=driver.findElement(By.xpath("//header/div[1]/div/div/div[2]/div/ul")).getText();
		Assert.assertTrue(l.contains("Hi"));
		
		logger=reports.createTest("SignIn_LoginPage");
		logger.log(Status.INFO, "THE USER HAS SUCCESSFULLY LOGGED IN");
	
		
		
		
		
	}
	
	@Test(priority=3)
	public void testCart()
	{
	    Assert.assertEquals(driver.getTitle(),"Home");
		Actions act1=new Actions(driver);
		act1.moveToElement(driver.findElement(By.partialLinkText("All Categories"))).perform();
		act1.moveToElement(driver.findElement(By.partialLinkText("Home Appliances"))).click().perform();
		act1.moveToElement(driver.findElement(By.partialLinkText("Floor"))).click().perform();
		
		 Assert.assertEquals(driver.getTitle(), "Search");
		 logger=reports.createTest("Search Cart");
		logger.log(Status.INFO, "ORDERING THE PRODUCT");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.partialLinkText("Add to cart")).click();
		driver.findElement(By.partialLinkText("Cart")).click();
		
		Assert.assertEquals(driver.getTitle(), "View Cart");
		logger=reports.createTest("SHOPPING CART");
		logger.log(Status.INFO, "PRODUCT IS ADDED");
		
		driver.findElement(By.partialLinkText("Checkout")).click();
		Assert.assertEquals(driver.getTitle(), "Cart Checkout");
		logger=reports.createTest("CHECKOUT");
		logger.log(Status.INFO, "THE PRODUCT HAS BEEN CHECKED OUT");
		driver.findElement(By.cssSelector("input[value='Proceed to Pay']")).click();
		Assert.assertEquals(driver.getTitle(), "Redirecting to Payment Gateway");
		//act1.moveToElement(driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input"))).click().perform();
		//act1.moveToElement(driver.findElement(By.partialLinkText("Proceed to Pay"))).click().perform();
		//driver.findElement(By.cssSelector("input[value='Proceed to Pay']")).click();
		
	}
	
	
@Test(priority=4)
	public void testPayment()
	{
	
	Assert.assertEquals(driver.getTitle(),"Redirecting to Payment Gateway");
	logger=reports.createTest("PAYMENT");
	logger.log(Status.INFO, "PAYMENT FOR THE ORDERED PRODUCT");
		WebDriverWait wait=new WebDriverWait(driver,10);
		driver.findElement(By.xpath("//*[@id=\"swit\"]/div[1]/div")).click();
		driver.findElement(By.id("btn")).click();
		driver.findElement(By.name("username")).sendKeys("123456");
		driver.findElement(By.name("password")).sendKeys("Pass@456");
		driver.findElement(By.cssSelector("input[value='LOGIN']")).click();
		driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
		driver.findElement(By.cssSelector("input[value='PayNow']")).click();
		Assert.assertEquals(driver.getTitle(), "Order Details");
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//section/div/div/div/div[2]")).getText(),"Your order has been confirmed");
		logger=reports.createTest("CONFIRMATION");
		logger.log(Status.INFO, "THE PRODUCT HAS BEEN SUCCESSFULLY ORDERED");
		
		
		

		
	}
	

	
}
