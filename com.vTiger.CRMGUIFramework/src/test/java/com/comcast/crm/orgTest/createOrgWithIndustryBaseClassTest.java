package com.comcast.crm.orgTest;

import java.io.File;
import java.io.FileInputStream;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
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

public class createOrgWithIndustryBaseClassTest extends BaseClass {
	
	@Test
	public void createOrgwithIndustry() throws EncryptedDocumentException, IOException {
		
		excelUtility eu=new excelUtility();
		javaUtility ju=new javaUtility();
		int n=ju.getRandomNumber();
		String orgName=eu.getDataFromExcel("org", 3, 2)+n;
		String Industry=eu.getDataFromExcel("org", 3, 3);
		//String type=eu.getDataFromExcel("org", 3, 4);
		//Navigation Of Organisation
		HomePage h=new HomePage(driver);
		h.getOrgLink().click();
		OrganizationsPage o=new OrganizationsPage(driver);
		o.getCreateNewOrg().click();
		CreatingNewOrganizationPage c=new CreatingNewOrganizationPage(driver);
		c.createOrg(orgName, Industry);
		//verify 	 Dropdostry and Type
		OraganizationInfoPage op=new OraganizationInfoPage(driver);
		String s=op.getIndustry().getText();
		if(s.equals(Industry)) {
			System.out.println(Industry+  "information is verified==PASS");
		}
		else {
			System.out.println(Industry+   "information is not verified==FAIL");
		}
		
	}

}
