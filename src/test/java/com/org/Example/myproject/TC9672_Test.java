package com.org.Example.myproject;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.utils.Data_loading;



public class TC9672_Test {

	 Data_loading guitils = new Data_loading();
	 String userName1 = guitils.getUserName("RequestorUsername");
	 String password1 = guitils.getPassword("RequestorPassword");
	 String Responder = guitils.getDATA("TPResponder");
	 String userName2 = guitils.getUserName("ResponderUsername");
	 String password2 = guitils.getPassword("RequestorPassword");
	Date d = new Date(System.currentTimeMillis());
	String Reqname	="AutoTest"+d;

	String firstwindow;
	String secondwindow;
	WebElement tblAccounts;
	List <WebElement> RowsOfTable;
	WebElement ColOfTable;	
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
	public void TwoActorWorkFlow() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName1);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password1);
		driver.findElement(By.id("Login")).click();
		switchtoLightining();
		driver.findElement(By.linkText("App Launcher")).click();
		driver.findElement(By.linkText("ICIX")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Requests')]")).click();
		Thread.sleep(2000);
		Set<String> window=driver.getWindowHandles();
		Iterator<String> iter=window.iterator();
		firstwindow=iter.next();
		driver.findElement(By.xpath("//div[contains(@title,'New')]")).click();
		driver.switchTo().frame(driver.findElement(By.id("vfFrameId")));
		Thread.sleep(2000);
		driver.findElement(By.id("requestName")).clear();
		driver.findElement(By.id("requestName")).sendKeys(Reqname);
		driver.findElement(By.id("tradingPartnerDropDown")).clear();
		driver.findElement(By.id("tradingPartnerDropDown")).sendKeys(Responder);
		driver.findElement(By.cssSelector("h3.ng-binding")).click();

		driver.findElement(By.cssSelector("button.slds-button.slds-button--neutral")).click();
		driver.findElement(By.xpath("//a[contains(@ng-click,'populateDocTemplate(d.name);')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
		driver.findElement(By.xpath("//div[3]/div/label/span[2]")).click();
		driver.findElement(By.cssSelector("div.slds-modal__footer.slds-modal__footer--directional > button.slds-button.slds-button--brand")).click();
		driver.findElement(By.xpath("//button[3]")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='date']")).click();
		driver.findElement(By.xpath("//div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(.,'12')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button.slds-button.slds-button--neutral")).click();
		driver.findElement(By.xpath("//button[@ng-click='CancelAttachDialog()']")).click();

		driver.findElement(By.id("comments")).sendKeys("Test");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[contains(.,'Send')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@ng-click='redirectToRequestListPage();']")).click();
		Thread.sleep(10000);
		driver.navigate().refresh();
		driver.findElement(By.linkText(Reqname)).click();
		Thread.sleep(10000);

		if(System.getProperty("os.name").toLowerCase().contains("win")){
			driver.findElement(By.xpath("//a[contains(@alt,'App Launcher')]")).sendKeys(Keys.CONTROL + "t");

		}
		else if(System.getProperty("os.name").toLowerCase().contains("mac")){
			driver.findElement(By.xpath("//a[contains(@alt,'App Launcher')]")).sendKeys(Keys.COMMAND + "t");
		}
		driver.get(baseUrl);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName2);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password2);
		driver.findElement(By.id("Login")).click();
		switchtoLightining();
		driver.findElement(By.linkText("App Launcher")).click();
		driver.findElement(By.linkText("ICIX")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Requests')]")).click();

		driver.findElement(By.xpath("//span[@class='triggerLinkText selectedListView uiOutputText']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find list']")).sendKeys("All");
		driver.findElement(By.xpath("//input[@placeholder='Find list']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Find list']")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath("//a[contains(@role,'option')]")).click();

		Thread.sleep(7000);
		while(true){
			String Total_requests=driver.findElement(By.xpath("//span[@class='uiOutputText forceListViewStatusInfo']")).getText();
			if (Total_requests.indexOf("+") > -1 ) {
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scrollContent = document.evaluate('html/body/div[6]/div[1]/section/div[1]/div[2]/div/div/div[2]/div[1]/div/div[2]/div/div[2]/div', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;scrollContent.scrollTop = scrollContent.scrollHeight;");
			} else {                                             
				break;
			}
		}    

		tblAccounts= driver.findElement(By.xpath("html/body/div[6]/div[1]/section/div[1]/div[2]/div/div/div[2]/div[1]/div/div[2]/div/div[2]/div/div/table/tbody"));		
		RowsOfTable=tblAccounts.findElements(By.tagName("tr"));		
		driver.findElement(By.linkText(Reqname)).click();
		Thread.sleep(9000);	   

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

