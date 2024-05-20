package com.comcast.crm.contactTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDateTest {
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
		String LastName=eu.getDataFromExcel("Contact", 1, 2)+n;
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
		String actdate=ju.getSystemDate_yyyy_mm_dd();
		String dateRequries=ju.getRequiredDate(30);
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@id='jscal_field_support_start_date']")).clear();
		driver.findElement(By.xpath("//input[@id='jscal_field_support_start_date']")).sendKeys(actdate);
		driver.findElement(By.xpath("//input[@id='jscal_field_support_end_date']")).clear();
		driver.findElement(By.xpath("//input[@id='jscal_field_support_end_date']")).sendKeys(dateRequries);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//verify HEADER MSG and orgname info
		String s1=driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
		if(s1.equals(actdate)) {
			System.out.println(actdate+  "information is verified==PASS");
		}
		else {
			System.out.println(actdate +   "information is not verified=FAIL");
		}
		String s2=driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
		if(s2.equals(dateRequries)) {
			System.out.println(dateRequries+  "information is verified==PASS");
		}
		else {
			System.out.println(dateRequries +   "information is not verified=FAIL");
		}
		
		//logout
		driver.quit();
	}

}
