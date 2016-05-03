package com.org.Example.myproject;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utils.Data_loading;


public class TC9653_Test {
	WebDriver driver;
	 String baseUrl;
     String  sTblAccounts ="html/body/div[6]/div[1]/section/div[1]/div[2]/div/div/div[2]/div[1]/div/div[2]/div/div[2]/div/div/table/tbody";
     Data_loading guitils = new Data_loading();
	 String userName1 = guitils.getUserName("RequestorUsername");
	 String password1 = guitils.getPassword("RequestorPassword");
	 String strTPName = guitils.getPassword("Partner_name");
	
	 WebElement tblAccounts;
		List <WebElement> RowsOfTable;
		WebElement ColOfTable;
	 
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
	
		    tblAccounts= driver.findElement(By.xpath(sTblAccounts));		
			RowsOfTable=tblAccounts.findElements(By.tagName("tr"));		
			
			for (int r=0;r<RowsOfTable.size();r++)
			{
				ColOfTable=RowsOfTable.get(r).findElement(By.tagName("th"));
				String tdText=ColOfTable.getText();
				String textToVerify=strTPName;
			
				
				if(tdText.equals(textToVerify))
				{
					
					RowsOfTable.get(r).findElement(By.cssSelector(".forceIcon")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//div[contains(text(),'Delete')]/parent::a[@class='forceActionLink']/parent::a")).click();
					driver.findElement(By.xpath("//button[contains(@title,'Delete')]")).click();				
					break;
				}
				
			}	
		    Thread.sleep(3000);
			driver.findElement(By.xpath("//div[contains(@title,'New')]")).click();
			driver.switchTo().frame(driver.findElement(By.id("vfFrameId")));
			driver.findElement(By.cssSelector("input[id=\"companyName\"]")).sendKeys(strTPName);
			driver.findElement(By.xpath(".//*[@class='slds-button slds-button--brand']")).click();
			driver.findElement(By.xpath("//button[contains(.,'Connect')]")).click();
			new Select(driver.findElement(By.xpath("//select[contains(@id,'ddl_UURelationship_Status')]"))).selectByVisibleText("Active");
			String strTypeDrp="//select[@id='ddl_UURelationship_Type']";
		    
		    if(System.getProperty("os.name").toLowerCase().contains("win")){
		    	
		    	driver.switchTo().activeElement().equals(driver.findElement(By.xpath(strTypeDrp)));
		        driver.findElement(By.xpath(strTypeDrp)).sendKeys(Keys.ARROW_DOWN);
		      		   
		 	}
		 	else if(System.getProperty("os.name").toLowerCase().contains("mac")){
		 		 driver.switchTo().activeElement().equals(driver.findElement(By.xpath(strTypeDrp)));
		 	      driver.findElement(By.xpath(strTypeDrp)).sendKeys(Keys.chord(Keys.ARROW_DOWN));
			     	}
		    
			driver.findElement(By.xpath(".//*[@id='btn_UPRelationship_Save']")).click();
			System.out.println(driver.findElement(By.xpath("html/body/main/div/div[2]")).getText());	
			driver.switchTo().defaultContent();
			Thread.sleep(8000);
		    driver.findElement(By.cssSelector("img.profileTrigger")).click();
		    driver.findElement(By.linkText("Log Out")).click();
	
	
	
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
