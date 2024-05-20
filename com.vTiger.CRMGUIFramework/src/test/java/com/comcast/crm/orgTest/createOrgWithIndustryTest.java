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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.fileutility.excelUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;

public class createOrgWithIndustryTest {
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
		String orgName=eu.getDataFromExcel("org", 3, 2)+n;
		String Industry=eu.getDataFromExcel("org", 3, 3);
		String type=eu.getDataFromExcel("org", 3, 4);
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
		WebElement wb=driver.findElement(By.xpath("//select[@name='industry']"));
		Select sel1=new Select(wb);
		wb.click();
		sel1.selectByValue(Industry);
		WebElement wb1=driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select sel2=new Select(wb1);
		wb1.click();
		sel2.selectByVisibleText(type);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//verify the Dropdown industry and Type
		String s=driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		if(s.equals(Industry)) {
			System.out.println(Industry+  "information is verified==PASS");
		}
		else {
			System.out.println(Industry+   "information is not verified==FAIL");
		}
		
		String s1=driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		if(s1.equals(type)) {
			System.out.println(type+  "information is verified==PASS");
		}
		else {
			System.out.println(type+   "information is not verified==FAIL");
		}
		//logout
		driver.quit();
	}

}
