package com.org.Example.myproject;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utils.Data_loading;



public class TC9676_Test {
	Date d = new Date(System.currentTimeMillis());
	String 	Document		="Testdocument"+d;
	
	
	Data_loading guitils = new Data_loading();
	String userName1     = guitils.getUserName("RequestorUsername");
	String password1     = guitils.getPassword("RequestorPassword");
	String DocuResponder = guitils.getDATA("DOcumentreciver");
	String comment       = guitils.getDATA("Comments");  
	
	WebDriver driver;
	String baseUrl; 
	@BeforeClass
	 public void beforeClass() {
		  baseUrl = "https://login.salesforce.com";      
	     driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
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
	    driver.findElement(By.xpath("//a[@id='browseLink']")).click();
	    Thread.sleep(5000);
        if(System.getProperty("os.name").toLowerCase().contains("win")){
	   Runtime.getRuntime().exec("..\\SmokeAutomation\\file upload.exe");
	    			   
	 	}
	 	else if(System.getProperty("os.name").toLowerCase().contains("mac")){
	 	// set the file name in clipboard
	 		StringSelection ss = new StringSelection("SmokeAutomation\\upload dociment.docx");
	 		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,null);
	 		Thread.sleep(3000);
	 		
	 		     //Sleep time to detect the window dialog box
	 					
	 			// Perform native keystrokes for CTRL+V and ENTER keys
	 			try {
	 			Robot robot = new Robot();
	 			robot.delay(1500);
	 			
	 			robot.keyPress(KeyEvent.VK_ENTER);
	 			robot.keyRelease(KeyEvent.VK_ENTER);

	 			robot.keyPress(KeyEvent.VK_CONTROL);
	 		    robot.keyPress(KeyEvent.VK_V);
	 		        
	 		    robot.keyRelease(KeyEvent.VK_V);
	 		    robot.keyRelease(KeyEvent.VK_CONTROL);
	 		    
	 		    robot.delay(1000);
	 		    robot.keyPress(KeyEvent.VK_ENTER);
	 		    robot.keyRelease(KeyEvent.VK_ENTER);
	 		    robot.delay(1500);
	 		    	
	 			} catch (Exception exp){
	 				exp.printStackTrace();
	 			}
		     	}
        
        Thread.sleep(3000);
        driver.switchTo().frame(driver.findElement(By.id("vfFrameId")));
	    driver.findElement(By.xpath("//input[contains(@class,'slds-input slds-show')]")).click();
	    driver.findElement(By.xpath("//a[contains(.,'Product Spec')]")).click();
	    //driver.findElement(By.xpath("//span[@class='slds-checkbox--faux']")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
	    
	    driver.findElement(By.id("uploadButton")).click();
	    Thread.sleep(6000);
	    driver.findElement(By.id("btn_ShowMore1")).click();
	    Thread.sleep(6000);
	    
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
