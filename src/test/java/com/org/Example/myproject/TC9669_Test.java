package com.org.Example.myproject;

import java.util.Date;
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


public class TC9669_Test {
	Date d = new Date(System.currentTimeMillis());
	String Question       ="Testquestion"+d;
	String container_Name ="Testcontainer"+d;
	String Layout_Name    ="Testlayout"+d;
	String Tab_Name       ="Testtab"+d;
	String Section_Name   ="Testsection"+d;
	
	 Data_loading guitils = new Data_loading();
	 String userName1     = guitils.getUserName("RequestorUsername");
	 String password1     = guitils.getPassword("RequestorPassword");
	 
	 
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
	  public void createNew_Question() throws Exception {
	    
		   driver.findElement(By.id("username")).clear();
		    driver.findElement(By.id("username")).sendKeys(userName1);
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys(password1);
		    driver.findElement(By.id("Login")).click();
		    
		    switchtoLightining();
		    driver.findElement(By.linkText("App Launcher")).click();
		    driver.findElement(By.linkText("ICIX")).click();
		    driver.findElement(By.xpath("//a[contains(.,'FormList')]")).click();
		    driver.switchTo().frame(0);
		    // script for container template
		    driver.findElement(By.name("j_id0:form:j_id7")).click();
		    driver.findElement(By.id("j_id0:form:containerBlock:containerNew:inputContainerName")).clear();
		    driver.findElement(By.id("j_id0:form:containerBlock:containerNew:inputContainerName")).sendKeys(container_Name);
		    new Select(driver.findElement(By.id("j_id0:form:containerBlock:containerNew:inputContainerContainerType"))).selectByVisibleText("Single Form");
		    new Select(driver.findElement(By.id("j_id0:form:containerBlock:containerNew:inputContainerType"))).selectByVisibleText("Form");
		    driver.findElement(By.id("j_id0:form:containerBlock:containerNew:inputContainerBestPracticeForm")).click();
		    new Select(driver.findElement(By.id("j_id0:form:containerBlock:containerNew:inputContainerLibrary"))).selectByVisibleText("Existing");
		    driver.findElement(By.id("j_id0:form:containerBlock:createContainer")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.id("j_id0:form:buttonSave")).click();
		    Thread.sleep(3000);
		    //script for Layout template
		    driver.findElement(By.id("j_id0:form:tabLayout_lbl")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.id("j_id0:form:layoutBlock:layoutNew:inputLayoutName")).clear();
		    Thread.sleep(3000);
		    driver.findElement(By.id("j_id0:form:layoutBlock:layoutNew:inputLayoutName")).sendKeys(Layout_Name);
		    Thread.sleep(3000);
		    new Select(driver.findElement(By.id("j_id0:form:layoutBlock:layoutNew:inputLayoutSharing"))).selectByVisibleText("Public");
		    Thread.sleep(3000);
		    driver.findElement(By.id("j_id0:form:layoutBlock:layoutNew:inputLayoutIsValid")).click();
		    Thread.sleep(3000);
		    new Select(driver.findElement(By.id("j_id0:form:layoutBlock:layoutNew:inputLayoutUiType"))).selectByVisibleText("desktop");
		    Thread.sleep(3000);
		    driver.findElement(By.id("j_id0:form:layoutBlock:createLayout")).click();
		    Thread.sleep(6000);
		    driver.findElement(By.id("j_id0:form:buttonSave")).click();
		    Thread.sleep(9000);
		    //script for Tsbs template
		    driver.findElement(By.id("j_id0:form:tabTabs_lbl")).click();
		    driver.findElement(By.id("j_id0:form:createTab")).click();
		    driver.findElement(By.id("j_id0:form:tabBlock:tabSection:tabTable:0:j_id46")).clear();
		    driver.findElement(By.id("j_id0:form:tabBlock:tabSection:tabTable:0:j_id46")).sendKeys(Tab_Name);
		    driver.findElement(By.id("j_id0:form:tabBlock:tabSection:tabTable:0:j_id57")).click();
		    Thread.sleep(6000);
		    driver.findElement(By.id("j_id0:form:buttonSave")).click();
		    Thread.sleep(8000);
		    driver.findElement(By.xpath(".//*[@id='j_id0:form:tabBlock:tabSection:tabTable:0:selectTab']")).click();
		    Thread.sleep(9000);
		    //Script for Sections template
		    driver.findElement(By.id("j_id0:form:tabSections_lbl")).click();
		    driver.findElement(By.xpath("//input[@id='j_id0:form:createSection']")).click();
		    driver.findElement(By.xpath("//input[contains(@id,'id73')]")).sendKeys(Section_Name);
		    new Select(driver.findElement(By.xpath("//select[contains(@id,'id88')]"))).selectByVisibleText("Row"); 
		    driver.findElement(By.xpath("//input[@value='SAVE']")).click();
		    Thread.sleep(6000);
	    //Script for Linked in Questions
	    driver.findElement(By.id("j_id0:form:tabLinkedQuestions_lbl")).click();
	    driver.findElement(By.id("j_id0:form:newElementWithQuestion:newQuestionBlock:inputQuestionName")).sendKeys("test");
	    driver.findElement(By.xpath("//img[@title='Create New']")).click();
	    driver.findElement(By.xpath("//textarea[@id='textAreaQuestionText']")).sendKeys(Question);
	    new Select(driver.findElement(By.xpath("//select[@id='selectAnswerTypeCreate']"))).selectByVisibleText("radio");
	    driver.findElement(By.xpath("//input[@value='Save']")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[@id='input_searchLibraryQuestion']")).sendKeys(Question);
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//img[@title='Filter List']")).click();

	    Thread.sleep(3000);
	    
	    /*String CR_Question=driver.findElement(By.xpath("//td[contains(@class,'dataCell')][2]")).getText();
	    Assert.assertEquals(Question,CR_Question,"Anstype is not matched");*/
	    
	    
	    driver.findElement(By.xpath("//*[@id='divLibraryQuestionList']/table/tbody/tr/td[1]/a")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.id("j_id0:form:newElementWithQuestion:addNewLinkedQuestion")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.id("j_id0:form:buttonSave")).click();
	    Thread.sleep(6000);
	    driver.findElement(By.id("j_id0:form:buttonPublish")).click();
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
