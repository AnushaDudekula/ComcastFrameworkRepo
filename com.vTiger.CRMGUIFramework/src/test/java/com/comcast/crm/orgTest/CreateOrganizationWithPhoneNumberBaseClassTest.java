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
import org.testng.annotations.Test;

import com.comcast.crm.BaseTest.BaseClass;
import com.comcast.crm.Objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.Objectrepositaryutility.HomePage;
import com.comcast.crm.Objectrepositaryutility.OraganizationInfoPage;
import com.comcast.crm.Objectrepositaryutility.OrganizationsPage;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.fileutility.excelUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;

public class CreateOrganizationWithPhoneNumberBaseClassTest extends BaseClass {
	@Test
	public void CreateOrgWithPhoneNoTest() throws IOException {
		excelUtility eu=new excelUtility();
		javaUtility ju=new javaUtility();
		int n=ju.getRandomNumber();
		String orgName=eu.getDataFromExcel("org", 5, 2)+n;
		String phoneNO=eu.getDataFromExcel("org", 5, 3);
		//Navigation Of Organisation
		HomePage h=new HomePage(driver);
		h.getOrgLink().click();
		OrganizationsPage o=new OrganizationsPage(driver);
		o.getCreateNewOrg().click();
		CreatingNewOrganizationPage c=new CreatingNewOrganizationPage(driver);
		c.createOrgWithPhoneNumber(orgName,phoneNO);
		//verify PhoneNumber and orgname info
		OraganizationInfoPage op=new OraganizationInfoPage(driver);
		String s=op.getPhoNo().getText();
		if(s.equals(phoneNO)) {
			System.out.println(phoneNO+  "  information is created==PASS");
		}
		else {
			System.out.println(phoneNO +   "  information is not created=FAIL");
		}
		
	}
}
