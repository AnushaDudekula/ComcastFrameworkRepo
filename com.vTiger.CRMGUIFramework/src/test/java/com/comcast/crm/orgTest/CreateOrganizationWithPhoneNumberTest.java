package com.comcast.crm.orgTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

public class CreateOrganizationWithPhoneNumberTest {
public static void main(String[] args) throws IOException {
	FileUtility f=new FileUtility();
	excelUtility eu=new excelUtility();
	javaUtility ju=new javaUtility();
	WebDriverUtility wd=new WebDriverUtility();
	String url=f.getDataFromPropertiesFile("url");
	String browser=f.getDataFromPropertiesFile("browser");
	String username=f.getDataFromPropertiesFile("username");
	String password=f.getDataFromPropertiesFile("password");
	int n=ju.getRandomNumber();
	String orgName=eu.getDataFromExcel("org", 5, 2)+n;
	String phoneNO=eu.getDataFromExcel("org", 5, 3);
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
	driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(phoneNO);
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	//verify PhoneNumber and orgname info
	String s1=driver.findElement(By.xpath("//span[@id='dtlview_Phone']")).getText();
	if(s1.equals(phoneNO)) {
		System.out.println(phoneNO+  "  information is created==PASS");
	}
	else {
		System.out.println(phoneNO +   "  information is not created=FAIL");
	}
	
	//logout
	driver.quit();
}
}
