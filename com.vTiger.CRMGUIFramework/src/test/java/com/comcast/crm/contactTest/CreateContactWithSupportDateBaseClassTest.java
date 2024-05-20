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

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.BaseTest.BaseClass;
import com.comcast.crm.Objectrepositaryutility.ContactInfoPage;
import com.comcast.crm.Objectrepositaryutility.ContactPage;
import com.comcast.crm.Objectrepositaryutility.CreatingNewContactPage;
import com.comcast.crm.Objectrepositaryutility.HomePage;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.fileutility.excelUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;

public class CreateContactWithSupportDateBaseClassTest extends BaseClass{
	@Test
	public void  createContactWithTime() throws EncryptedDocumentException, IOException {

		excelUtility eu=new excelUtility();
		javaUtility ju=new javaUtility();
		int n=ju.getRandomNumber();
		String LastName=eu.getDataFromExcel("Contact", 1, 2)+n;
		String actdate=ju.getSystemDate_yyyy_mm_dd();
		String dateRequries=ju.getRequiredDate(30);
		HomePage h = new HomePage(driver);
		h.getContactLink().click();
		ContactPage c=new ContactPage(driver);
		c.getCreateNewContact().click();
		CreatingNewContactPage cp=new CreatingNewContactPage(driver);
		cp.getLastName().sendKeys(LastName);
		cp.getStartTime().clear();
		cp.getStartTime().sendKeys(actdate);
		cp.getEndTime().clear();
		cp.getEndTime().sendKeys(dateRequries);
		cp.getSave().click();
		//verify HEADER MSG and orgname info
		ContactInfoPage ci=new ContactInfoPage(driver);
		String s1=ci.getInfoStartTime().getText();
		if(s1.equals(actdate)) {
			System.out.println(actdate+  "information is verified==PASS");
		}
		else {
			System.out.println(actdate +   "information is not verified=FAIL");
		}

		String s2=ci.getInfoEndTime().getText();
		if(s2.equals(dateRequries)) {
			System.out.println(dateRequries+  "information is verified==PASS");
		}
		else {
			System.out.println(dateRequries +   "information is not verified=FAIL");
		}

	}

}
