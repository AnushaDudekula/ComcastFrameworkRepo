package com.comcast.crm.contactTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.fileutility.excelUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;

public class CreateContactWithOrgTest {
	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		FileUtility f=new FileUtility();
		excelUtility eu=new excelUtility();
		javaUtility ju=new javaUtility();
		WebDriverUtility wd=new WebDriverUtility();
		String url=f.getDataFromPropertiesFile("url");
		String browser=f.getDataFromPropertiesFile("browser");
		String username=f.getDataFromPropertiesFile("username");
		String password=f.getDataFromPropertiesFile("password");
		int n=ju.getRandomNumber();
		String LastName=eu.getDataFromExcel("Contact", 1, 2)+n;
		String orgName=eu.getDataFromExcel("Contact", 7, 2)+n;
		WebDriver driver=null;
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(browser.equals("firefox")) {
		driver=new FirefoxDriver();
		}
		else if(browser.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else {
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		wd.waitForPageLoad(driver);
		//LogIn
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		//Navigation Of Organisation
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		//String mainId=driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
//		Set<String> ids=driver.getWindowHandles();
//		for(String id:ids)
//		{
//		if(!(id.equals(mainId))) {
//			driver.switchTo().window(id);
//			String url1=driver.getCurrentUrl();
//			if(url1.contains("module=Accounts")) {
//				break;
//			}
//			
//		}
//		}
		String mainId=wd.switchToTabURl(driver,"module=Accounts");
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		driver.switchTo().window(mainId);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//verify HEADER MSG and orgname info
		String s=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(s.contains(LastName)) {
			System.out.println(LastName +"is created==pass");
		}else
			System.out.println(LastName +"is not created==fail");
		
		String s1=driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']")).getText();
		if(s1.contains(orgName)) {
			System.out.println(orgName +  "information is verified==PASS");
		}
		else {
			System.out.println(orgName +   "information is not verified=FAIL");
		}
		//logout
		driver.quit();
	}

}
