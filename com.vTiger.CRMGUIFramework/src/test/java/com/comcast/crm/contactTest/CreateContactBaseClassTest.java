package com.comcast.crm.contactTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
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

public class CreateContactBaseClassTest extends BaseClass{

	@Test
	public void CreateContact() throws EncryptedDocumentException, IOException {
		int n=ju.getRandomNumber();
		String LastName=eu.getDataFromExcel("Contact", 1, 2)+n;
		//LogIn
		//Navigation Of Organisation
		HomePage h = new HomePage(driver);
		h.getContactLink().click();
		ContactPage c=new ContactPage(driver);
		c.getCreateNewContact().click();
		CreatingNewContactPage cp=new CreatingNewContactPage(driver);
		cp.CreateContact(LastName);
		//verify HEADER MSG and orgname info
		ContactInfoPage ci=new ContactInfoPage(driver);
		String s1=ci.getLastName().getText();
		if(s1.equals(LastName)) {
			System.out.println(LastName+  "information is verified==PASS");
		}
		else {
			System.out.println(LastName +   "information is not verified=FAIL");
		}

	}

}
