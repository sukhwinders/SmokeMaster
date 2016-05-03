package com.org.Example.myproject;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utils.Data_loading;

public class TC9659_Test {
	WebDriver driver;
	String baseUrl; 
    Date d = new Date(System.currentTimeMillis());
	String 	Tags		="Test"+d;
	String randomNumbers = RandomStringUtils.randomNumeric(8);
	String UPCproduct = "1111" + randomNumbers; 
	String firstwindow;
	
	
	 Data_loading guitils = new Data_loading();
	 String userName1     = guitils.getUserName("RequestorUsername");
	 String password1     = guitils.getPassword("RequestorPassword");
	 String strTPName = guitils.getUserName("TradingPartnerName");
	 
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
  public void Tagstest() throws Exception {
	  driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys(userName1);
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys(password1);
	    driver.findElement(By.id("Login")).click();
	    switchtoLightining();
	    driver.findElement(By.linkText("App Launcher")).click();
	    driver.findElement(By.linkText("ICIX")).click();
	    driver.findElement(By.xpath("//a[contains(text(),'ICIX Products')]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//a[@class='forceActionLink']")).click();
	    driver.switchTo().frame(driver.findElement(By.id("vfFrameId")));
	    
	    driver.findElement(By.id("txtIdValue0")).clear();
	    driver.findElement(By.id("txtIdValue0")).sendKeys(UPCproduct);
	    Thread.sleep(10000);
	    driver.findElement(By.id("btnProductSearch")).click();
	    driver.findElement(By.id("btnCreateProduct")).click();
	  //input[@id='ProductName']
	    
	    driver.findElement(By.xpath("//input[@id='ProductName']")).sendKeys("TEST Product");
	  
	    driver.findElement(By.xpath("//button[contains(@ng-click,'vm.AddNewProduct()')]")).click();
	    driver.findElement(By.xpath("//input[@id='txt_UPProductRelationship_Name']")).sendKeys("TEST Product");
	    driver.findElement(By.xpath("//input[@id='txt_UPTardingPartner_Name']")).sendKeys(strTPName);
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//h3[@class='ng-binding']")).click();
	  
	 
	    new Select(driver.findElement(By.id("ddl_UPRelationship_Type"))).selectByVisibleText("Buy");
	    String strTypeDrp="//select[@id='ddl_UPRelationship_Status']";
	    
	    if(System.getProperty("os.name").toLowerCase().contains("win")){
	    	
	    	driver.switchTo().activeElement().equals(driver.findElement(By.xpath(strTypeDrp)));
	        driver.findElement(By.xpath(strTypeDrp)).sendKeys(Keys.ARROW_DOWN);
	      		   
	 	}
	 	else if(System.getProperty("os.name").toLowerCase().contains("mac")){
	 		 driver.switchTo().activeElement().equals(driver.findElement(By.xpath(strTypeDrp)));
	 	      driver.findElement(By.xpath(strTypeDrp)).sendKeys(Keys.chord(Keys.ARROW_DOWN));
		     	}
	    Thread.sleep(2000);
	  
	    driver.findElement(By.xpath("//a[@id='btn_UPRelationship_Tag_Add']")).click();
	    Thread.sleep(4000);
	    
	    
	    driver.findElement(By.xpath("//input[@id='txt_UPRelationship_Tag_New']")).sendKeys(Tags);
	    driver.findElement(By.xpath("//textarea[@id='txt_UPRelationship_Comment'] ")).click();
	    
	    Thread.sleep(5000);   
	    
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
