package com.comcast.crm.BaseTest;


import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.Objectrepositaryutility.HomePage;
import com.comcast.crm.Objectrepositaryutility.LoginPage;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.fileutility.excelUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;
//@Listeners(com.comcast.crm.listenerUtility.ListImpClass.class)
public class BaseClass {
	public  WebDriver driver;
	public static WebDriver sdriver;
	public  DataBaseUtility d=new DataBaseUtility();
	public  FileUtility f=new FileUtility();
	public  excelUtility eu=new excelUtility();
	public javaUtility ju=new javaUtility();
	public  WebDriverUtility wd=new WebDriverUtility();
	//public ExtentTest test;
	HomePage h;
	@BeforeSuite(alwaysRun=true)
	public void configBS() throws SQLException {
		System.out.println("Conect to database");
		d.getConnection();
	}
	//@Parameters("browser")
	@BeforeClass(alwaysRun=true)
	public void configBC(/*String browser*/) throws IOException {
		System.out.println("launching Browser");
	String browser=f.getDataFromPropertiesFile("browser");
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
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
		driver.manage().window().maximize();
		wd.waitForPageLoad(driver);
		h=new HomePage(driver);
	}
	@BeforeMethod(alwaysRun=true)
	public void configBM() throws IOException {
		System.out.println("LOGIN");
		LoginPage l=new LoginPage(driver);
		String url=f.getDataFromPropertiesFile("url");
		String username=f.getDataFromPropertiesFile("username");
		String password=f.getDataFromPropertiesFile("password");
		l.Login(url,username,password);
	}
	@AfterMethod(alwaysRun=true)
    public void configAM() {
		System.out.println("logOUT");
		h.LogOut();
		
	}
	@AfterClass(alwaysRun=true)
    public void configAC() {
		System.out.println("close the Browser");
		driver.quit();
	}
	@AfterSuite(alwaysRun=true)
    public void configAS() throws SQLException {
		System.out.println("close Db,report backUp");
		d.closeDBConnection();
	}
}
