package com.comcast.crm.orgTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.BaseTest.BaseClass;
import com.comcast.crm.Objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.Objectrepositaryutility.HomePage;
import com.comcast.crm.Objectrepositaryutility.OraganizationInfoPage;
import com.comcast.crm.Objectrepositaryutility.OrganizationsPage;
import com.comcast.crm.generic.fileutility.excelUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.javaUtility;
import com.comcast.crm.listenerUtility.ListImpClass;
@Listeners(com.comcast.crm.listenerUtility.ListImpClass.class)
public class CreateOrganizationAllTest extends BaseClass{
	@Test
	public void createOrg() throws EncryptedDocumentException, IOException {
		UtilityClassObject.getTest().log(Status.INFO, "READ DATA FROM EXCEL");
		int n=ju.getRandomNumber();
		String orgName=eu.getDataFromExcel("org", 8, 2)+n;
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org");
		HomePage h=new HomePage(driver);
		h.getOrgLink().click();
		UtilityClassObject.getTest().log(Status.INFO, "navigate to createorg");
		OrganizationsPage o=new OrganizationsPage(driver);
		o.getCreateNewOrg().click();
		UtilityClassObject.getTest().log(Status.INFO, "create org");
		CreatingNewOrganizationPage c=new CreatingNewOrganizationPage(driver);
		c.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO ,  orgName + "create a new org");
		//verify HEADER MSG and orgname info
		OraganizationInfoPage op=new OraganizationInfoPage(driver);
		String s=op.getHeaderMSG().getText();
		if(s.contains(orgName)) {
			System.out.println(orgName +"is created==pass");
		}else
			System.out.println(orgName +"is not created==fail");
	} 
//	@Test()
//	public void createOrg() throws EncryptedDocumentException, IOException {
//		excelUtility eu=new excelUtility();
//		javaUtility ju=new javaUtility();
//		int n=ju.getRandomNumber();
//		String orgName=eu.getDataFromExcel("org", 8, 2)+n;
//		HomePage h=new HomePage(driver);
//		h.getOrgLink().click();
//		OrganizationsPage o=new OrganizationsPage(driver);
//		o.getCreateNewOrg().click();
//		CreatingNewOrganizationPage c=new CreatingNewOrganizationPage(driver);
//		c.createOrg(orgName);
//		//verify HEADER MSG and orgname info
//		OraganizationInfoPage op=new OraganizationInfoPage(driver);
//		String s=op.getHeaderMSG().getText();
//		if(s.contains(orgName)) {
//			System.out.println(orgName +"is created==pass");
//		}else
//			System.out.println(orgName +"is not created==fail");
//	}
	@Test(groups="smoke")
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
	@Test(groups="regression")
	public void createOrgwithIndustry() throws EncryptedDocumentException, IOException {
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
		//verify the Dropdostry and Type
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
