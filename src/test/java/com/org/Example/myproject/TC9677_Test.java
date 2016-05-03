package com.org.Example.myproject;
	import java.awt.Robot;
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

import com.thoughtworks.selenium.webdriven.commands.KeyEvent;
import com.utils.Data_loading;


public class TC9677_Test  {
    Data_loading guitils = new Data_loading();
	String userName1     = guitils.getUserName("RequestorUsername");
	String password1     = guitils.getPassword("RequestorPassword");
	String TPname       = guitils.getDATA("TPName");
	String Responder = guitils.getDATA("Partner_name");
	
	String firstwindow;

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
	  public void send_document() throws Exception {
	   
		  driver.findElement(By.id("username")).clear();
		  driver.findElement(By.id("username")).sendKeys(userName1);
		  driver.findElement(By.id("password")).clear();
		  driver.findElement(By.id("password")).sendKeys(password1);
		  driver.findElement(By.id("Login")).click();
		    Thread.sleep(5000);
		    switchtoLightining();
	    driver.findElement(By.linkText("App Launcher")).click();
	    driver.findElement(By.linkText("ICIX")).click();
	    driver.findElement(By.linkText("Document Library")).click();
	    driver.switchTo().frame(driver.findElement(By.id("vfFrameId")));
	    driver.findElement(By.id("btn_AddDocument")).click();
	    new Select(driver.findElement(By.id("ddTemplate"))).selectByVisibleText("GFSI Certification");
	    driver.findElement(By.id("createButton")).click();
		
	    Set<String> window=driver.getWindowHandles();
		Iterator<String> iter=window.iterator();
		firstwindow=iter.next();
	    
	   /* Set<String> w=driver.getWindowHandles();
		String handle[]=new String[w.size()];
		System.out.println("********** Handles are **************");
		int i=0;
		for(String s:w)
		{
			System.out.println(s);
			handle[i]=s;
			i++;  
		}
		*/
		//Switch driver focus from parent window to child window
		
		driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
		//Switch driver focus from child window to parent window
		driver.switchTo().window(firstwindow);
	    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | form | 30000]]
	    driver.findElement(By.linkText("Incident")).click();
	    driver.findElement(By.id("isPublic")).click();
	    driver.findElement(By.id("documentDialogSaveButton")).click();
	    driver.findElement(By.id("uploadButton")).click();
	    
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





   