package com.org.Example.myproject;


import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.utils.Data_loading;


public class TC9666_Test {
  
  Date d = new Date(System.currentTimeMillis());
	String TP_Group	="Test Group "+d;
	String firstwindow;
	String secondwindow;
	
	Data_loading guitils = new Data_loading();
	 String userName1 = guitils.getUserName("RequestorUsername");
	 String password1 = guitils.getPassword("RequestorPassword");
	
	
	WebDriver driver;
	 String baseUrl; 
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
  public void SetAndSendCompilince () throws Exception {
    
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(userName1);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(password1);
    driver.findElement(By.id("Login")).click();
    switchtoLightining();
    driver.findElement(By.linkText("App Launcher")).click();
    driver.findElement(By.linkText("ICIX")).click();
    driver.findElement(By.linkText("Trading Partner Groups")).click();
     
    Thread.sleep(2000);
    driver.findElement(By.xpath("//div[@title='New']")).click();
    driver.switchTo().frame(0);
    driver.findElement(By.id("txtGroupName")).clear();
    driver.findElement(By.id("txtGroupName")).sendKeys(TP_Group);
    
    driver.findElement(By.xpath("//span[@class='slds-checkbox--faux'][1]")).click();
    driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
    Thread.sleep(2000);
    
    driver.findElement(By.cssSelector("div.slds-x-small-buttons--horizontal > button.slds-button.slds-button--brand")).click();
    driver.navigate().refresh();
    driver.switchTo().defaultContent();
    driver.findElement(By.cssSelector("div.r5")).click();
    driver.findElement(By.linkText("ICIX")).click();
    driver.findElement(By.linkText("Trading Partner Groups")).click();
    driver.findElement(By.linkText(TP_Group)).click();
    
    driver.findElement(By.cssSelector("div[title=\"Set Requirements\"]")).click();
    
    driver.switchTo().frame(1);
    
    Thread.sleep(3000);
       if(System.getProperty("os.name").toLowerCase().contains("win")){
    	  
    	driver.switchTo().activeElement().equals(driver.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[2]/div[2]/section/div/div/div/select")));
        driver.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[2]/div[2]/section/div/div/div/select")).sendKeys(Keys.ARROW_DOWN);
      		   
 	}
 	else if(System.getProperty("os.name").toLowerCase().contains("mac")){
 		
 		 driver.switchTo().activeElement().equals(driver.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[2]/div[2]/section/div/div/div/select")));
 	      driver.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[2]/div[2]/section/div/div/div/select")).sendKeys(Keys.chord(Keys.ARROW_DOWN));
	     	}
    Thread.sleep(6000);
    driver.findElement(By.xpath("//input[@name='dateid']")).click();
    new Select(driver.findElement(By.id("DocType0"))).selectByVisibleText("Bioterrorism Act Statement of Compliance");
    Thread.sleep(2000);
    driver.findElement(By.linkText("+ Add Another compliance criteria")).click();
    
    driver.findElement(By.xpath("//input[@name='dateid']")).click();
    driver.findElement(By.xpath("//button[contains(.,'Next Month')]")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[2]/div[4]/section[1]/div/div/slds-datepicker/div/div[2]/table/tbody/tr[1]/td[6]/span")).click();
   
    
    driver.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[3]/div[6]/a/span")).click();
    
    driver.findElement(By.xpath("html/body/div[1]/div/div[3]/button[3]")).click();
    driver.findElement(By.cssSelector("div.slds-x-small-buttons--horizontal > button.slds-button.slds-button--brand")).click();
    Thread.sleep(3000);
  
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

