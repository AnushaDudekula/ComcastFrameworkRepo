package practice.Testt;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.excelUtility;

public class GetProductInfoTest {
	@Test(dataProvider="getData")
	public void getProductInfoTest(String BrandName,String productName) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://amazon.in");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(BrandName,Keys.ENTER);
	    WebElement wb=driver.findElement(By.xpath("//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div/div/a/span/span[2]/span[2]"));
		Actions act=new Actions(driver);
		act.scrollToElement(wb).perform();
        String price= wb.getText();
	    System.out.println(price);
		driver.close();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		excelUtility eu=new excelUtility();
		int count=eu.getRowCount("amezon");
		System.out.println(count);
		Object[][] obj=new Object[count][2];
		for(int i=0;i<count;i++) {
		obj[i][0]=eu.getDataFromExcel("amezon", i+1,0 );
		obj[i][1]=eu.getDataFromExcel("amezon", i+1,1 );
		}
		return obj;
	}
	
	
	
}
//Apple iPhone 13 (128GB) - Starlight