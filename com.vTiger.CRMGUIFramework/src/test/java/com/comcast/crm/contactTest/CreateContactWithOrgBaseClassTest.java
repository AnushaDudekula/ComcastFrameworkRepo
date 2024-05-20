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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.comcast.crm.BaseTest.BaseClass;
import com.comcast.crm.Objectrepositaryutility.ContactInfoPage;
import com.comcast.crm.Objectrepositaryutility.ContactPage;
import com.comcast.crm.Objectrepositaryutility.CreatingNewContactPage;
import com.comcast.crm.Objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.Objectrepositaryutility.HomePage;
import com.comcast.crm.Objectrepositaryutility.OrganizationsPage;
import com.comcast.crm.Objectrepositaryutility.newWindowPage;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.fileutility.excelUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;

public class CreateContactWithOrgBaseClassTest extends BaseClass{
	@Test
	public void createContactWithOrg() throws EncryptedDocumentException, IOException, InterruptedException { 
		int n=ju.getRandomNumber();
		String LastName=eu.getDataFromExcel("Contact", 1, 2)+n;
		String orgName=eu.getDataFromExcel("Contact", 7, 2)+n;
		//Navigation Of Organisation
		HomePage h=new HomePage(driver);
		h.getOrgLink().click();
		OrganizationsPage o=new OrganizationsPage(driver);
		o.getCreateNewOrg().click();
		CreatingNewOrganizationPage c=new CreatingNewOrganizationPage(driver);
		c.createOrg(orgName);
		Thread.sleep(2000);
		h.getContactLink().click();
		ContactPage c1=new ContactPage(driver);
		c1.getCreateNewContact().click();
		CreatingNewContactPage cp=new CreatingNewContactPage(driver);
		cp.getLastName().sendKeys(LastName);
		cp.getOrg().click();
		String mainId=wd.switchToTabURl(driver,"module=Accounts");
		newWindowPage wp=new newWindowPage(driver);
		wp.getSearchbox().sendKeys(orgName);
		wp.getSearch().click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		driver.switchTo().window(mainId);
		cp.getSave().click();
   	//verify HEADER MSG and orgname info
		ContactInfoPage ci=new ContactInfoPage(driver);
		String s1=ci.getHearderText().getText();
		if(s1.contains(LastName)) {
			System.out.println(LastName +"is created==pass");
		}else
			System.out.println(LastName +"is not created==fail");
		
		String s2=ci.getOrgName().getText();
		if(s2.contains(orgName)) {
			System.out.println(orgName +  "information is verified==PASS");
		}
		else {
			System.out.println(orgName +   "information is not verified=FAIL");
		}
	}

}
