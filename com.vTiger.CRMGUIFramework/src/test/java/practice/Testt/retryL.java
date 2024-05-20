package practice.Testt;

import org.testng.Assert;
import org.testng.annotations.Test;

public class retryL {
 @Test(retryAnalyzer = com.comcast.crm.listenerUtility.RetryListenerImp.class)
 public void activateSim() {
		System.out.println("execute createInvoiceTest");
		
		Assert.assertEquals("", "LogIn");
	System.out.println("step-1");
	System.out.println("step-2");
	System.out.println("step-3");
	System.out.println("step-4");
	}
 }

