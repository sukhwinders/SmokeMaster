package com.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Data_loading {

	String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    String DATE_FORMAT_DATE = "yyyy-MM-dd";
    String DATE_FORMAT_TODAY_DATE = "M/d/yyyy";
    String DATE_FORMAT_TODAY_DATE_TIME = "M/dd/yyyy hh:mm a";
    String DATE_FORMAT_TODAY_DATE_TIME2 = "M/d/yyyy hh:mm a";
    private WebDriver driver;

    public void GeneralUtils(WebDriver driver) {
    	  this.driver = driver;
	}

	public String now() {
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	return sdf.format(cal.getTime());
	//qa
    }

    public Date nowSFDCformat() throws Exception {
        DateFormat formatter = null;
        Date convertedDate = null;
    	String currentDate = now().substring(0, 10);
    	String convertedDate2;
//        String currentDate = "2015-02-19 12:00:00";
    	
//    	Calendar cal = Calendar.getInstance();
//    	formatter = new SimpleDateFormat(DATE_FORMAT_NOW);
    	formatter = new SimpleDateFormat(DATE_FORMAT_DATE);
    	
//    	formatter = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		convertedDate = formatter.parse(currentDate);
//		System.out.println(convertedDate);
		return convertedDate;
    }

    public Date tomorrowSFDCformat() throws Exception {
        DateFormat formatter = null;
        Date convertedDate = null;
    	
    	formatter = new SimpleDateFormat(DATE_FORMAT_DATE);
	    Calendar cal = Calendar.getInstance();        
	    cal.add(Calendar.DATE, 1);     
	    String tommorrow= (String)(formatter.format(cal.getTime()));
		convertedDate = formatter.parse(tommorrow);
//		System.out.println(convertedDate);
		return convertedDate;		
	}

    public Date todayPlusNumberOfDaysSFDCformat(int numberOfDays) throws Exception {
        DateFormat formatter = null;
        Date convertedDate = null;
    	
    	formatter = new SimpleDateFormat(DATE_FORMAT_DATE);
	    Calendar cal = Calendar.getInstance();        
	    cal.add(Calendar.DATE, numberOfDays);  // number of days to add      
	    String tommorrow= (String)(formatter.format(cal.getTime()));
		convertedDate = formatter.parse(tommorrow);
		return convertedDate;		
	}

    
    public String today_date() {
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TODAY_DATE);
	return sdf.format(cal.getTime());
    }

    public String today_date_time() {
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TODAY_DATE_TIME);
	return sdf.format(cal.getTime());
    }
    
    public Date today_date_time2() throws ParseException{
//    	Calendar cal = Calendar.getInstance();
//    	Date currentDate = cal.getTime();
    	Date curDate = new Date();
    	DateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TODAY_DATE_TIME2, Locale.US);
    	String currentDateTimeStr =  sdf.format(curDate);
    	Date currentDateTime = sdf.parse(currentDateTimeStr);

    	return currentDateTime;
    }

    public String getTestRunStartTime() {
	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH.mm");
	Calendar cal = Calendar.getInstance();
	String dateTime = dateFormat.format(cal.getTime());

	return dateTime;
    }

    public String getTime() {
	DateFormat dateFormat = new SimpleDateFormat("HH.mm");
	Calendar cal = Calendar.getInstance();
	String dateTime = dateFormat.format(cal.getTime());

	return dateTime;

    }
    
   
    	    public void switchtoLightining()throws Exception{
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
    	    	
    	    	
    	    	
    	    	
    	    	
//    	     
     	
    	    
    	    
    	    public  String getUserName (String username) {
    	     Properties prop = new Properties();
    	     InputStream input = null;      
    	    try {
    	///      input = new FileInputStream("/Users/jeff.radom/git/AutomationForSalesforce/AutomationforSalesforce/src/test/java/config/config.properties");
    	     input = new FileInputStream(getProjectDirectory() + "Config.properties");

    	  // load a properties file
    	  prop.load(input);

    	  // get the property value and print it out
    	  
    	 } catch (IOException ex) {
    	  ex.printStackTrace();
    	 } finally {

    	  if (input != null) {
    	   try {
    	    input.close();
    	   } catch (IOException e) {
    	    e.printStackTrace();
    	   }
    	  }
    	 }
    	 return prop.getProperty(username);
    	    }
    	    public String getDATA (String Data) {
       	     Properties prop = new Properties();
       	     InputStream input = null;      
       	    try {
       	///      input = new FileInputStream("/Users/jeff.radom/git/AutomationForSalesforce/AutomationforSalesforce/src/test/java/config/config.properties");
       	     input = new FileInputStream(getProjectDirectory() + "Config.properties");

       	  // load a properties file
       	  prop.load(input);

       	  // get the property value and print it out
       	  
       	 } catch (IOException ex) {
       	  ex.printStackTrace();
       	 } finally {

       	  if (input != null) {
       	   try {
       	    input.close();
       	   } catch (IOException e) {
       	    e.printStackTrace();
       	   }
       	  }
       	 }
       	 return prop.getProperty(Data);
       	    }

    	    public  String getPassword(String password) {
    	     Properties prop = new Properties();
    	     InputStream input = null;      
    	    try {
    	///      input = new FileInputStream("/Users/jeff.radom/git/AutomationForSalesforce/AutomationforSalesforce/src/test/java/config/config.properties");
    	     input = new FileInputStream(getProjectDirectory() + "Config.properties");

    	  // load a properties file
    	  prop.load(input);

    	  // get the property value and print it out
    	  
    	 } catch (IOException ex) {
    	  ex.printStackTrace();
    	 } finally {

    	  if (input != null) {
    	   try {
    	    input.close();
    	   } catch (IOException e) {
    	    e.printStackTrace();
    	   }
    	  }
    	 }
    	 return prop.getProperty(password);
    	    }

    	    public static String getProjectDirectory() {
    	        String projectDir = System.getProperty("user.dir") + "/src/test/java/ConfigData/";
    	        //if (projectDir.contains("/AutomationSalesforce/"))
    	        //{
    	        //    projectDir = projectDir + "src/test/java/Config/";
    	       // }
    	        System.out.println("Using base directory as: " + projectDir);
    	     return projectDir; 
    	     }

    	   
    	    
    	  //Added By sdei for fetching relationship type and status
    	    public String getRelationshipType (String Rtype) {
    	     Properties prop = new Properties();
    	     InputStream input = null;      
    	    try {
    	///      input = new FileInputStream("/Users/jeff.radom/git/AutomationForSalesforce/AutomationforSalesforce/src/test/java/config/config.properties");
    	     input = new FileInputStream(getProjectDirectory() + "Config.properties");

    	  // load a properties file
    	  prop.load(input);

    	  // get the property value and print it out
    	  
    	 } catch (IOException ex) {
    	  ex.printStackTrace();
    	 } finally {

    	  if (input != null) {
    	   try {
    	    input.close();
    	   } catch (IOException e) {
    	    e.printStackTrace();
    	   }
    	  }
    	 }
    	 return prop.getProperty(Rtype);
    	    }
    	    
    	  //Added By sdei for fetching relationship type and status
    	    public String getRelationshipStatus (String Rstatus) {
    	     Properties prop = new Properties();
    	     InputStream input = null;      
    	    try {
    	///      input = new FileInputStream("/Users/jeff.radom/git/AutomationForSalesforce/AutomationforSalesforce/src/test/java/config/config.properties");
    	     input = new FileInputStream(getProjectDirectory() + "Config.properties");

    	  // load a properties file
    	  prop.load(input);

    	  // get the property value and print it out
    	  
    	 } catch (IOException ex) {
    	  ex.printStackTrace();
    	 } finally {

    	  if (input != null) {
    	   try {
    	    input.close();
    	   } catch (IOException e) {
    	    e.printStackTrace();
    	   }
    	  }
    	 }
    	 return prop.getProperty(Rstatus);
    	    }
    	    
    	    public String generaterandomupc(){
       			//randomNumbers = RandomStringUtils.randomNumeric(8);
       			//UPCproduct = "1111" + randomNumbers; 
       			return "1111" + RandomStringUtils.randomNumeric(8);
       		}
       
       
       		

}

