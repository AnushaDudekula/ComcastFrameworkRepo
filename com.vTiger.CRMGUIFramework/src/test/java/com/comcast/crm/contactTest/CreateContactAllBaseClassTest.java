package com.comcast.crm.contactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.BaseTest.BaseClass;
import com.comcast.crm.Objectrepositaryutility.ContactInfoPage;
import com.comcast.crm.Objectrepositaryutility.ContactPage;
import com.comcast.crm.Objectrepositaryutility.CreatingNewContactPage;
import com.comcast.crm.Objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.Objectrepositaryutility.HomePage;
import com.comcast.crm.Objectrepositaryutility.OrganizationsPage;
import com.comcast.crm.Objectrepositaryutility.newWindowPage;

public class CreateContactAllBaseClassTest extends BaseClass {

	@Test(groups="smoke")
	public void CreateContact() throws EncryptedDocumentException, IOException {
		int n = ju.getRandomNumber();
		String LastName = eu.getDataFromExcel("Contact", 1, 2) + n;
		// LogIn
		// Navigation Of Organisation
		HomePage h = new HomePage(driver);
		h.getContactLink().click();
		ContactPage c = new ContactPage(driver);
		c.getCreateNewContact().click();
		CreatingNewContactPage cp = new CreatingNewContactPage(driver);
		cp.CreateContact(LastName);
		// verify HEADER MSG and orgname info
		ContactInfoPage ci = new ContactInfoPage(driver);
		String s1 = ci.getLastName().getText();
		if (s1.equals(LastName)) {
			System.out.println(LastName + "information is verified==PASS");
		} else {
			System.out.println(LastName + "information is not verified=FAIL");
		}

	}

	@Test
	public void createContactWithTime() throws EncryptedDocumentException, IOException {
		ContactInfoPage ci = new ContactInfoPage(driver);
		int n = ju.getRandomNumber();
		String LastName = eu.getDataFromExcel("Contact", 1, 2) + n;
		String actdate = ju.getSystemDate_yyyy_mm_dd();
		String dateRequries = ju.getRequiredDate(30);
		HomePage h = new HomePage(driver);
		h.getContactLink().click();
		ContactPage c = new ContactPage(driver);
		c.getCreateNewContact().click();
		CreatingNewContactPage cp = new CreatingNewContactPage(driver);
		cp.getLastName().sendKeys(LastName);
		cp.getStartTime().clear();
		cp.getStartTime().sendKeys(actdate);
		cp.getEndTime().clear();
		cp.getEndTime().sendKeys(dateRequries);
		cp.getSave().click(); // verify HEADER MSG and orgname info ContactInfoPage
		ci = new ContactInfoPage(driver);
		String s1 = ci.getInfoStartTime().getText();
		if (s1.equals(actdate)) {
			System.out.println(actdate + "information is verified==PASS");
		} else {
			System.out.println(actdate + "information is not verified=FAIL");
		}

		String s2 = ci.getInfoEndTime().getText();
		if (s2.equals(dateRequries)) {
			System.out.println(dateRequries + "information is verified==PASS");
		} else {
			System.out.println(dateRequries + "information is not verified=FAIL");
		}

	}
	@Test(groups= {"smoke","regression"})
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