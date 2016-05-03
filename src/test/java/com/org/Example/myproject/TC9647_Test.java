package com.org.Example.myproject;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utils.Data_loading;

public class TC9647_Test {
	  String firstwindow;
	  WebDriver driver;
	  String baseUrl; 
	
	 
	 
	 Data_loading guitils = new Data_loading();
	 String userName1 = guitils.getUserName("RequestorUsername");
	 String password1 = guitils.getPassword("RequestorPassword");
	 String Icixid    = guitils.getDATA("icixid");
	 
  @Test
  public void Auccount_verification() throws Exception {
	
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys(userName1);
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys(password1);
	    driver.findElement(By.id("Login")).click();
	    Thread.sleep(8000);
	    
	    switchtoLightining();
	   
	    driver.findElement(By.linkText("App Launcher")).click();
	    driver.findElement(By.linkText("ICIX")).click();

	    driver.findElement(By.cssSelector("div.list > ul > li > a")).click();
	    Thread.sleep(3000);
	    //New button
	    
	    driver.findElement(By.xpath("//div[@class='topRightHeaderRegion']/div/div/ul/li[1]/a")).click();
	    driver.switchTo().frame(driver.findElement(By.id("vfFrameId")));
	    
	    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
	 
	    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys(Icixid);
	    driver.findElement(By.cssSelector("button.slds-button.slds-button--brand")).click();
	    driver.switchTo().defaultContent();
	    Thread.sleep(8000);
	    driver.findElement(By.cssSelector("img.profileTrigger")).click();
	    driver.findElement(By.linkText("Log Out")).click(); 
	  
  }
  @BeforeClass
  public void beforeClass() {
	  baseUrl = "https://login.salesforce.com";      
      driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.navigate().to(baseUrl);  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
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
