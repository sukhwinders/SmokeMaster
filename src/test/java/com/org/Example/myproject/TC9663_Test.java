package com.org.Example.myproject;

import java.util.regex.Pattern;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.utils.Data_loading;



public class TC9663_Test {
	WebDriver driver;
	String baseUrl;


	Data_loading guitils = new Data_loading();
	String userName1 = guitils.getUserName("RequestorUsername");
	String password1 = guitils.getPassword("RequestorPassword");
	String partner_name = guitils.getDATA("Partner_name");





	@BeforeClass
	public void beforeClass() {
		baseUrl = "https://login.salesforce.com";      
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.navigate().to(baseUrl);  
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}


	@Test
	public void Edit_TradingPartner() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName1);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password1);
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
		switchtoLightining();
		driver.findElement(By.linkText("App Launcher")).click();
		driver.findElement(By.linkText("ICIX")).click();
		driver.findElement(By.xpath("//a[contains(.,'Trading Partner Relationships')]")).click();
		driver.findElement(By.linkText(partner_name)).click();
		driver.findElement(By.xpath("//a[contains(.,'Edit')]")).click();
		driver.switchTo().frame(driver.findElement(By.id("vfFrameId")));
		new Select(driver.findElement(By.id("ddl_UURelationship_Status"))).selectByVisibleText("Pending");
		new Select(driver.findElement(By.xpath("//select[@id='ddl_UURelationship_Type']"))).selectByVisibleText("Cold Storage");
		driver.findElement(By.id("btn_UPRelationship_Save")).click();
		driver.findElement(By.xpath("//message-dialog/div[2]/div/div/div[3]/button")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(8000);
		driver.findElement(By.cssSelector("img.profileTrigger")).click();
		driver.findElement(By.linkText("Log Out")).click();
		Thread.sleep(8000);


	}
	public void switchtoLightining() throws InterruptedException  { 
		  
		  
			if(driver.findElements(By.xpath("//span[@id='userNavLabel']")).size() >0 ){
				
			         driver.findElement(By.id("userNavLabel")).click();
			          driver.findElement(By.xpath("//a[@title='Switch to Lightning Experience']")).click();
			          String parentWindow= driver.getWindowHandle();
			          Set<String> allWindows = driver.getWindowHandles();
			          for(String curWindow : allWindows){
			              driver.switchTo().window(curWindow);
			          //perform operation on popup
			              driver.findElement(By.xpath("//div[@style='line-height:12px; margin-top: 12px']")).click();
			              driver.findElement(By.id("simpleDialog0button0")).click();
			           // switch back to parent window
			       driver.switchTo().window(parentWindow);
			       Thread.sleep(8000);
			       driver.navigate().refresh();
			          }
			     }
			     else if(driver.findElements(By.xpath("//span[@id='userNavLabel']")).size() < 0 ){
			    	
			    	 driver.findElement(By.linkText("App Launcher")).click();
			     }}	

}