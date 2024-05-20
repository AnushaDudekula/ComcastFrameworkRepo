package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
    public void waitForElementPresent(WebDriver driver,WebElement element) {
  	 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	 wait.until(ExpectedConditions.visibilityOf(element));
    }
    public String switchToTabURl(WebDriver driver,String partialURL) {
//    	Set<String> allids=driver.getWindowHandles();
//    	Iterator<String> it=allids.iterator();
//    	String id=it.next();
//    	while(it.hasNext()) {
//    	driver.switchTo().window(id);
//    	String actURL=driver.getCurrentUrl();
//    	if(actURL.contains(partialURL)) {
//    		break;
//    	}
//        }
    	String mainId=driver.getWindowHandle();
    	Set<String> ids=driver.getWindowHandles();
		for(String id:ids)
		{
		if(!(id.equals(mainId))) {
			driver.switchTo().window(id);
			String url=driver.getCurrentUrl();
			if(url.contains(partialURL)) {
				break;
			}
			
		}
		}
		return mainId;
        }
    public void switchToTabTitle(WebDriver driver,String partialTitle) {
    	Set<String> allids=driver.getWindowHandles();
    	Iterator<String> it=allids.iterator();
    	String id=it.next();
    	while(it.hasNext()) {
    	driver.switchTo().window(id);
    	String actTitle=driver.getCurrentUrl();
    	if(actTitle.contains(partialTitle)) {
    		break;
    	}
        }
        }
    
    public void switchtoFrame(WebDriver driver,int index) {
    	driver.switchTo().frame(index);
    }
    public void switchtoFrame(WebDriver driver,String nameID) {
    	driver.switchTo().frame(nameID);
    }
    public void switchtoFrame(WebDriver driver,WebElement element) {
    	driver.switchTo().frame(element);
    }
    public void switchtoAlertAndAccept(WebDriver driver) {
    	driver.switchTo().alert().accept();
    }
    public void switchtoAlertAndCancel(WebDriver driver) {
    	driver.switchTo().alert().dismiss();
    }
    public void select(WebElement element,String text) {
    	Select s=new Select(element);
    	s.selectByVisibleText(text);
    }
    public void select(WebElement element,int index) {
    	Select s=new Select(element);
    	s.selectByIndex(index);
    }
   public void mouseMoveOnElement(WebDriver driver,WebElement element) {
	   Actions act=new Actions(driver);
	   act.moveToElement(element).perform();
   }
   public void doubleClick(WebDriver driver,WebElement element) {
	   Actions act=new Actions(driver);
	   act.doubleClick(element).perform();
   }
   public void rightClick(WebDriver driver,WebElement element) {
	   Actions act=new Actions(driver);
	   act.contextClick(element).perform();
   }
}
