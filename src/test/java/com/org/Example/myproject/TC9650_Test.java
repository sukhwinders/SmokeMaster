package com.org.Example.myproject;



import java.util.regex.Pattern;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.utils.Data_loading;



public class TC9650_Test {
Data_loading guitils = new Data_loading();
String userName1     = guitils.getUserName("RequestorUsername");
String password1     = guitils.getPassword("RequestorPassword");
String Address       = guitils.getDATA("Address");
String City          = guitils.getDATA("City");
String State         = guitils.getDATA("State");
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
public void testSearchByIcixId() throws Exception {
	driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(userName1);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(password1);
    driver.findElement(By.id("Login")).click();
    Thread.sleep(5000);
   
    switchtoLightining();
  driver.findElement(By.linkText("App Launcher")).click();
  driver.findElement(By.linkText("ICIX")).click();

  driver.findElement(By.cssSelector("div.list > ul > li > a")).click();
  Thread.sleep(3000);
  //New button
  
  driver.findElement(By.xpath("//div[@class='topRightHeaderRegion']/div/div/ul/li[1]/a")).click();
  driver.switchTo().frame(driver.findElement(By.id("vfFrameId")));

  driver.findElement(By.xpath("//input[@placeholder='Street']")).clear();
  driver.findElement(By.xpath("//input[@placeholder='Street']")).sendKeys(Address);
  driver.findElement(By.xpath("//input[@placeholder='City']")).sendKeys(City);
  driver.findElement(By.xpath("//input[contains(@ng-model,'avm.newPartner.address.state')]")).sendKeys(State);
  driver.findElement(By.cssSelector("button.slds-button.slds-button--brand")).click();
  String Stret=driver.findElement(By.xpath("//p[@class='slds-text-body--regular ng-binding']")).getText();
  Assert.assertEquals(Stret,Address,"Name is not matched");

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


