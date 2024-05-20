package practice.Testt;

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
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;



@Listeners(com.comcast.crm.listenerUtility.ListImpClass.class)
public class List extends BaseClass{
	
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
}
