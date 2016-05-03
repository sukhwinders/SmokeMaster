package com.org.Example.myproject;

import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utils.Data_loading;


public class TC9668_Test {
	 WebDriver driver;
	 String baseUrl; 
	 Date d = new Date(System.currentTimeMillis());
	 String Group ="Productgroup"+d;
	
	 Data_loading guitils = new Data_loading();
	 String userName1     = guitils.getUserName("RequestorUsername");
	 String password1     = guitils.getPassword("RequestorPassword");
	 String partner_name  = guitils.getDATA("Partner_name");
	
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
	 public void test() throws Exception  {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName1);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password1);
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
		switchtoLightining();
	    driver.findElement(By.linkText("App Launcher")).click();
	    driver.findElement(By.linkText("ICIX")).click();
	    driver.findElement(By.linkText("Product Groups")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[@title='New']")).click();
	    driver.switchTo().frame(driver.findElement(By.id("vfFrameId")));
	    driver.findElement(By.xpath("//input[@id='txtGroupName']")).clear();
	    driver.findElement(By.xpath("//input[@id='txtGroupName']")).sendKeys(Group);
	   
	    driver.findElement(By.xpath("//span[@class='slds-checkbox--faux'][1]")).click();
	    driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
	    
	    driver.findElement(By.cssSelector("div.slds-x-small-buttons--horizontal > button.slds-button.slds-button--brand")).click();
	    driver.navigate().refresh();
	    driver.switchTo().defaultContent();
	    driver.findElement(By.linkText("App Launcher")).click();
	    driver.findElement(By.linkText("ICIX")).click();
	    driver.findElement(By.linkText("Product Groups")).click();
	    Thread.sleep(3000);
	    driver.navigate().refresh();
	    driver.findElement(By.linkText(Group)).click();
	   Thread.sleep(3000);
	   
	   driver.findElement(By.cssSelector("div[title=\"Edit\"]")).click();
	   driver.switchTo().frame(driver.findElement(By.id("vfFrameId")));
       driver.findElement(By.xpath("//span[@class='slds-checkbox--faux'][1]")).click();
       driver.findElement(By.id("btnSave")).click();
       driver.findElement(By.cssSelector("div.slds-x-small-buttons--horizontal > button.slds-button.slds-button--brand")).click();
	   
	   	   
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