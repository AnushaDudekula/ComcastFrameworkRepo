package practice.Testt;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.BaseTest.BaseClass;
@Listeners(com.comcast.crm.listenerUtility.ListImpClass.class)
public class InvoiceTest extends BaseClass {
	@Test
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
//		String actTitle=driver.getTitle();
//		SoftAssert s=new SoftAssert();
//		s.assertEquals(actTitle, "LogIn");
	System.out.println("step-1");
	System.out.println("step-2");
	System.out.println("step-3");
	System.out.println("step-4");
//	s.assertAll();
	}
	@Test
	public void createInvoiceContactTest() {
		System.out.println("execute createInvoiceContactTest");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
}
