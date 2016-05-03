package com.org.Example.myproject;

import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.utils.Data_loading;


public class TC9661_Test {
	 WebDriver driver;
	 String baseUrl; 
    Date d = new Date(System.currentTimeMillis());
	String 	Product		="TestProduct"+d;
	String randomNumbers = RandomStringUtils.randomNumeric(8);
	String UPCproduct = "1111" + randomNumbers; 
	String firstwindow;
	
	 Data_loading guitils = new Data_loading();
	 String userName1 = guitils.getUserName("Nonpartner");
	 String password1 = guitils.getPassword("RequestorPassword");
	 String userName2 = guitils.getUserName("ResponderUsername");
	 String password2 = guitils.getPassword("RequestorPassword");
	 String strTPName = guitils.getPassword("product_partner");
  @Test
  public void publicProduct () throws Exception {
	  
	  driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys(userName2);
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys(password2);
	    driver.findElement(By.id("Login")).click();
	    switchtoLightining();
	    driver.findElement(By.linkText("App Launcher")).click();
	    driver.findElement(By.linkText("ICIX")).click();
	    driver.findElement(By.xpath("//a[contains(text(),'ICIX Products')]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//a[@class='forceActionLink']")).click();
	    Thread.sleep(5000);
	    driver.switchTo().frame(driver.findElement(By.id("vfFrameId")));
	    
	    driver.findElement(By.id("txtIdValue0")).clear();
	    driver.findElement(By.id("txtIdValue0")).sendKeys(UPCproduct);
	    Thread.sleep(9000);
	    driver.findElement(By.id("btnProductSearch")).click();
	    driver.findElement(By.id("btnCreateProduct")).click();
	    driver.findElement(By.xpath("//input[@id='ProductName']")).sendKeys(Product);
		  
	    driver.findElement(By.xpath("//button[contains(@ng-click,'vm.AddNewProduct()')]")).click();
	    driver.findElement(By.xpath("//input[@id='txt_UPProductRelationship_Name']")).sendKeys(Product);
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
	    
	    driver.findElement(By.id("btn_UPRelationship_Next")).click();
	    driver.findElement(By.id("rdPermissionOption1")).click();
	    driver.findElement(By.xpath("//fieldset/div/label[2]")).click();
	    driver.findElement(By.id("btn_UPRelationshipPermission_Save")).click();
	    driver.findElement(By.xpath("//message-dialog/div[2]/div/div/div[3]/button")).click();
	    driver.switchTo().defaultContent();
	    Thread.sleep(5000);
	    
	    
	    
	    if(System.getProperty("os.name").toLowerCase().contains("win")){
			   driver.findElement(By.xpath("//a[contains(@alt,'App Launcher')]")).sendKeys(Keys.CONTROL + "t");
			   
	 	}
	 	else if(System.getProperty("os.name").toLowerCase().contains("mac")){
	 		driver.findElement(By.xpath("//a[contains(@alt,'App Launcher')]")).sendKeys(Keys.COMMAND + "t");
		     	}
	driver.get(baseUrl);
	driver.findElement(By.id("username")).clear();
	driver.findElement(By.id("username")).sendKeys(userName1);
	driver.findElement(By.id("password")).clear();
	driver.findElement(By.id("password")).sendKeys(password1);
	driver.findElement(By.id("Login")).click();
	switchtoLightining();
	driver.findElement(By.linkText("App Launcher")).click();
	driver.findElement(By.linkText("ICIX")).click();
	driver.findElement(By.xpath("//a[contains(.,'ICIX Products')]")).click();
	driver.findElement(By.xpath("//a[@class='forceActionLink']")).click();
	driver.switchTo().frame(driver.findElement(By.id("vfFrameId")));
	driver.findElement(By.id("txtIdValue0")).clear();
	driver.findElement(By.id("txtIdValue0")).sendKeys(UPCproduct);
	Thread.sleep(5000);
	driver.findElement(By.id("btnProductSearch")).click();
	Thread.sleep(6000);
	Assert.assertTrue(driver.findElement(By.xpath("//button[contains(.,'Request to Connect')]")).isDisplayed(), "product is not avilable");
    
  }
  
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
  public void switchtoLightining() throws InterruptedException  { 
		

		if(driver.findElements(By.xpath("//span[@id='userNavLabel']")).size() >0 ){
			
			driver.findElement(By.id("userNavLabel")).click();
			driver.findElement(By.xpath("//a[@title='Switch to Lightning Experience']")).click();
			String parentWindow= driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			for(String curWindow : allWindows){
				driver.switchTo().window(curWindow);
				//perform operation on popup
				if(driver.findElements(By.xpath("//div[@style='line-height:12px; margin-top: 12px']")).size() >0){
				driver.findElement(By.xpath("//div[@style='line-height:12px; margin-top: 12px']")).click();
				driver.findElement(By.id("simpleDialog0button0")).click();
				}
				else if(driver.findElements(By.xpath("//div[@style='line-height:12px; margin-top: 12px']")).size() <0){
					
				}
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

 
