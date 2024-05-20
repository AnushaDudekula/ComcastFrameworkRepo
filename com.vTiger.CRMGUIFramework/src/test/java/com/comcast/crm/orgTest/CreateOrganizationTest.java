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

import com.comcast.crm.Objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.Objectrepositaryutility.HomePage;
import com.comcast.crm.Objectrepositaryutility.LoginPage;
import com.comcast.crm.Objectrepositaryutility.OraganizationInfoPage;
import com.comcast.crm.Objectrepositaryutility.OrganizationsPage;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.fileutility.excelUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;

public class CreateOrganizationTest {
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
	String orgName=eu.getDataFromExcel("org", 8, 2)+n;
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
	LoginPage l=new LoginPage(driver);
	l.Login(url,username, password);
	HomePage h=new HomePage(driver);
	h.getOrgLink().click();
	OrganizationsPage o=new OrganizationsPage(driver);
	o.getCreateNewOrg().click();
	//Navigation Of Organisation
	CreatingNewOrganizationPage c=new CreatingNewOrganizationPage(driver);
	c.createOrg(orgName);
	//verify HEADER MSG and orgname info
	OraganizationInfoPage op=new OraganizationInfoPage(driver);
	String s=op.getHeaderMSG().getText();
	if(s.contains(orgName)) {
		System.out.println(orgName +"is created==pass");
	}else
		System.out.println(orgName +"is not created==fail");
	String s1=op.getOrganizationName().getText();
	if(s1.equals(orgName)) {
		System.out.println(orgName+  "is created==PASS");
	}
	else {
		System.out.println(orgName +   "is not created=FAIL");
	}
	h.getOrgLink().click();
	o.getSearch().sendKeys(orgName);
	wd.select(o.getSearchDropDown(), "Organization Name");
	o.getSearchButton().click();
	driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]//a[text()='del']")).click();
	wd.switchtoAlertAndAccept(driver);
	//logout
	//h.LogOut();
	//driver.quit();
}
}
